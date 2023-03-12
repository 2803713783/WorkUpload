<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="作业编码" prop="jobCode">
        <el-input v-model="queryParams.jobCode" placeholder="请输入作业编码" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="作业名称" prop="jobName">
        <el-input v-model="queryParams.jobName" placeholder="请输入作业名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="课程的ID" prop="courseId">
        <el-input v-model="queryParams.courseId" placeholder="请输入课程的ID" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="显示顺序" prop="jobSort">
        <el-input v-model="queryParams.jobSort" placeholder="请输入显示顺序" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['business:job:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['business:job:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['business:job:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['business:job:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="jobList" @selection-change="handleSelectionChange" border style="" show-arrow>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="作业ID" align="center" prop="jobId" />
      <el-table-column label="作业编码" align="center" prop="jobCode" />
      <el-table-column label="作业名称" align="center" prop="jobName" />
      <el-table-column label="课程" align="center" prop="courseName" />
      <el-table-column label="作业描述" align="center" prop="jobDescription" />
      <el-table-column label="显示顺序" align="center" prop="jobSort" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['business:job:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['business:job:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改作业信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="jobRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="作业编码" prop="jobCode">
          <el-input v-model="form.jobCode" placeholder="请输入作业编码" />
        </el-form-item>
        <el-form-item label="作业名称" prop="jobName">
          <el-input v-model="form.jobName" placeholder="请输入作业名称" />
        </el-form-item>
        <el-form-item label="课程" prop="courseId">
          <el-select v-model="form.courseId" placeholder="请选择课程">
            <el-option v-for="course in courseList" :key="course.courseId" :label="course.courseName"
              :value="course.courseId"></el-option>
          </el-select>
          <!-- <el-input v-model="form.userId" placeholder="请输入教师的ID" /> -->
        </el-form-item>
        <el-form-item label="作业描述" prop="jobDescription">
          <el-input v-model="form.jobDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="显示顺序" prop="jobSort">
          <el-input v-model="form.jobSort" placeholder="请输入显示顺序" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in sys_normal_disable" :key="dict.value" :label="dict.value">{{ dict.label
            }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Job">
import { listJob, getJob, delJob, addJob, updateJob } from "@/api/business/job"
import useUserStore from '@/store/modules/user'
import {
  listCourse,
} from "@/api/business/course"

const userStore = useUserStore()
const { proxy } = getCurrentInstance()
const { sys_normal_disable } = proxy.useDict("sys_normal_disable")
console.log(proxy.useDict);

const jobList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const courseList = ref([])
const courseTotal = ref(0)
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    jobCode: null,
    jobName: null,
    courseId: null,
    jobDescription: null,
    jobSort: null,
    status: null,
  },
  courseParams: {
  },
  rules: {
    jobCode: [
      { required: true, message: "作业编码不能为空", trigger: "blur" }
    ],
    jobName: [
      { required: true, message: "作业名称不能为空", trigger: "blur" }
    ],
    courseId: [
      { required: true, message: "课程的ID不能为空", trigger: "blur" }
    ],
    jobSort: [
      { required: true, message: "显示顺序不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "状态不能为空", trigger: "change" }
    ],
  }
})

const { queryParams, courseParams, form, rules } = toRefs(data)

/** 查询作业信息列表 */
function getList() {
  loading.value = true
  listJob(queryParams.value).then(response => {
    jobList.value = response.rows
    total.value = response.total
    loading.value = false
  }).then(() => {
    jobList.value.forEach((ele, num) => {
      courseList.value.forEach((v) => {
        if (v.courseId == ele.courseId) {
          jobList.value[num].courseName = v.courseName
        }
      })
    })
    console.log(jobList.value);
  })
}
/** 查询课程信息列表 */
function getCourseList() {
  loading.value = true
  listCourse(courseParams.value).then(response => {
    courseList.value = response.rows
    courseTotal.value = response.total
    loading.value = false
  }).then(() => {

  })

}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    jobId: null,
    jobCode: null,
    jobName: null,
    courseId: null,
    jobDescription: null,
    jobSort: null,
    status: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("jobRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.jobId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加作业信息"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _jobId = row.jobId || ids.value
  getJob(_jobId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改作业信息"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["jobRef"].validate(valid => {
    if (valid) {
      if (form.value.jobId != null) {
        updateJob(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addJob(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _jobIds = row.jobId || ids.value
  proxy.$modal.confirm('是否确认删除作业信息编号为"' + _jobIds + '"的数据项？').then(function () {
    return delJob(_jobIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/job/export', {
    ...queryParams.value
  }, `job_${new Date().getTime()}.xlsx`)
}
getCourseList()
getList()
</script>
