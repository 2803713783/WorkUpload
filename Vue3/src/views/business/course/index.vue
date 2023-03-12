<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程编码" prop="courseCode">
        <el-input v-model="queryParams.courseCode" placeholder="请输入课程编码" clearable @keyup.enter="handleQuery" />
      </el-form-item>

      <el-form-item label="课程名称" prop="courseName">

        <el-input v-model="queryParams.courseName" placeholder="请输入课程名称" clearable @keyup.enter="handleQuery" />

      </el-form-item>

      <el-form-item label="教师的ID" prop="userId">

        <el-input v-model="queryParams.userId" placeholder="请输入教师的ID" clearable @keyup.enter="handleQuery" />

      </el-form-item>

      <el-form-item label="显示顺序" prop="postSort">

        <el-input v-model="queryParams.postSort" placeholder="请输入显示顺序" clearable @keyup.enter="handleQuery" />

      </el-form-item>

      <el-form-item>

        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>

        <el-button icon="Refresh" @click="resetQuery">重置</el-button>

      </el-form-item>

    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['business:course:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['business:course:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['business:course:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['business:course:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange" border style="" show-arrow>
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="课程ID" align="center" prop="courseId" /> -->
      <el-table-column label="课程编码" align="center" prop="courseCode" />

      <el-table-column label="课程名称" align="center" prop="courseName" />

      <el-table-column label="教师" align="center" prop="teachName" />

      <el-table-column label="显示顺序" align="center" prop="postSort" />

      <el-table-column label="状态" align="center" prop="status" />

      <el-table-column label="备注" align="center" prop="remark" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">

        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['business:course:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['business:course:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改课程信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="courseRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程编码" prop="courseCode">
          <el-input v-model="form.courseCode" placeholder="请输入课程编码" />
        </el-form-item>
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="form.courseName" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="教师" prop="userId">
          <el-select v-model="form.userId" placeholder="请选择教师">
            <el-option v-for="user in userList" :key="user.userId" :label="user.nickName"
              :value="user.userId"></el-option>
          </el-select>
          <!-- <el-input v-model="form.userId" placeholder="请输入教师的ID" /> -->
        </el-form-item>
        <el-form-item label="显示顺序" prop="postSort">
          <el-input v-model="form.postSort" placeholder="请输入显示顺序" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in sys_normal_disable" :key="dict.value" :label="dict.value">{{ dict.label }}</el-radio>
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


<script setup name="course">
import {
  listView
} from "@/api/system/userRole"

import {
  listCourse,
  getCourse,
  delCourse,
  addCourse,
  updateCourse,
} from "@/api/business/course"
const { proxy } = getCurrentInstance()

// console.log("myDATA:", getCurrentInstance())
const {
  sys_normal_disable
} = proxy.useDict("sys_normal_disable")


const courseList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const userList = ref([])
const userTotal = ref(0)
const dateRange = ref([])

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    courseCode: null,
    courseName: null,
    userId: null,
    postSort: null,
    status: null,
  },
  userRoleQueryParams: {
    roleName: '教师'
  },
  rules: {
    courseCode: [{
      required: true,
      message: "课程编码不能为空",
      trigger: "blur"
    },],
    courseName: [{
      required: true,
      message: "课程名称不能为空",
      trigger: "blur"
    },],
    userId: [{
      required: true,
      message: "教师的ID不能为空",
      trigger: "blur"
    }],
    postSort: [{
      required: true,
      message: "显示顺序不能为空",
      trigger: "blur"
    },],
    status: [{
      required: true,
      message: "状态不能为空",
      trigger: "change"
    }],
  },
})

const {
  queryParams,
  form,
  rules,
  userRoleQueryParams
} = toRefs(data)
/** 查询用户列表 */
function getUserList() {
  loading.value = true
  listView(proxy.addDateRange(userRoleQueryParams.value, dateRange.value)).then(res => {
    loading.value = false
    userList.value = res.rows
    userTotal.value = res.total
  })
}
/** 查询课程信息列表 */
function getList() {
  loading.value = true
  listCourse(queryParams.value).then((response) => {
    courseList.value = response.rows
    total.value = response.total
    loading.value = false
  }).then(() => {
    courseList.value.forEach((ele, num) => {
      userList.value.forEach((v) => {
        if (v.userId == ele.userId) {
          courseList.value[num].teachName = v.nickName
        }
      })
    })
    console.log(courseList.value);
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
    courseId: null,
    courseCode: null,
    courseName: null,
    userId: null,
    postSort: null,
    status: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null,
  }
  proxy.resetForm("courseRef")
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
  ids.value = selection.map((item) => item.courseId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加课程信息"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _courseId = row.courseId || ids.value
  getCourse(_courseId).then((response) => {
    form.value = response.data
    open.value = true
    title.value = "修改课程信息"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["courseRef"].validate((valid) => {
    if (valid) {
      if (form.value.courseId != null) {
        updateCourse(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addCourse(form.value).then((response) => {
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
  const _courseIds = row.courseId || ids.value
  proxy.$modal
    .confirm('是否确认删除课程信息编号为"' + _courseIds + '"的数据项？')
    .then(function () {
      return delCourse(_courseIds)
    })
    .then(() => {
      getList()
      proxy.$modal.msgSuccess("删除成功")
    })
    .catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download(
    "business/course/export", {
    ...queryParams.value,
  },
    `course_${new Date().getTime()}.xlsx`
  )
}

getUserList()
getList()


</script>
