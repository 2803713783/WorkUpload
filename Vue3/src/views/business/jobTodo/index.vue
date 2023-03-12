<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作品简介" prop="worksBrief">
        <el-input
          v-model="queryParams.worksBrief"
          placeholder="请输入作品简介"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作品类型" prop="worksType">
        <el-select
          v-model="queryParams.worksType"
          placeholder="请选择作品类型"
          clearable
        >
          <el-option
            v-for="dict in user_works_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="显示顺序" prop="worksSort">
        <el-input
          v-model="queryParams.worksSort"
          placeholder="请输入显示顺序"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['business:jobTodo:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:jobTodo:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:jobTodo:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['business:jobTodo:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        v-model:showSearch="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="jobTodoList"
      @selection-change="handleSelectionChange"
      border
      style=""
      show-arrow
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="作业" align="center" prop="jobName" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column
        label="作品地址"
        align="center"
        prop="worksSrc"
        show-overflow-tooltip
      />

      <el-table-column label="作品简介" align="center" prop="worksBrief" />
      <el-table-column
        label="作品描述"
        align="center"
        prop="worksDescription"
      />
      <el-table-column label="作品类型" align="center" prop="worksType">
        <template #default="scope">
          <dict-tag :options="user_works_type" :value="scope.row.worksType" />
        </template>
      </el-table-column>
      <el-table-column label="显示顺序" align="center" prop="worksSort" />
      <el-table-column
        label="封面图片"
        align="center"
        prop="worksCover"
        width="100"
      >
        <template #default="scope">
          <image-preview
            :src="scope.row.worksCover"
            :lazy="true"
            fit="cover"
            :preview-teleported="true"
          />
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="200"
        fixed="right"
      >
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:jobTodo:edit']"
            >修改</el-button
          >
          <el-button
            link
            type="primary"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:jobTodo:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改作业上传对话框 -->
    <el-dialog :title="title" v-model="open" width="700px" append-to-body>
      <el-form ref="jobTodoRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="作业选择" prop="jobId">
          <el-select v-model="form.jobId" placeholder="请选择作业">
            <el-option
              v-for="job in jobList"
              :key="job.jobId"
              :label="job.jobName"
              :value="job.jobId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="作品地址" prop="worksSrc">
          <file-upload v-model="form.worksSrc" :webkitdirectoryIf="true" />
        </el-form-item>
        <el-form-item label="作品简介" prop="worksBrief">
          <el-input v-model="form.worksBrief" placeholder="请输入作品简介" />
        </el-form-item>
        <el-form-item label="作品描述" prop="worksDescription">
          <el-input
            v-model="form.worksDescription"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="作品类型" prop="worksType">
          <el-select v-model="form.worksType" placeholder="请选择作品类型">
            <el-option
              v-for="dict in user_works_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="显示顺序" prop="worksSort">
          <el-input v-model="form.worksSort" placeholder="请输入显示顺序" />
        </el-form-item> -->
        <el-form-item label="封面图片" prop="worksCover">
          <image-upload v-model="form.worksCover" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input
            v-model="form.remark"
            type="textarea"
            placeholder="请输入内容"
          />
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

<script setup>
import {
  listJobTodo,
  getJobTodo,
  delJobTodo,
  addJobTodo,
  updateJobTodo,
} from "@/api/business/jobTodo";
import { listJob } from "@/api/business/job";
const { proxy } = getCurrentInstance();
const { user_works_type } = proxy.useDict("user_works_type");

const jobTodoList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const jobList = ref([]);
const jobTotal = ref(0);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userId: null,
    worksSrc: null,
    worksBrief: null,
    worksDescription: null,
    worksType: null,
    worksSort: null,
    worksCover: null,
    status: null,
  },
  jobParams: {
    pageNum: 1,
    pageSize: 10,
  },
  rules: {
    userId: [
      {
        required: true,
        message: "用户ID不能为空",
        trigger: "blur",
      },
    ],
    worksSrc: [
      {
        required: true,
        message: "作品地址不能为空",
        trigger: "blur",
      },
    ],
    worksBrief: [
      {
        required: true,
        message: "作品简介不能为空",
        trigger: "blur",
      },
    ],
    worksDescription: [
      {
        required: true,
        message: "作品描述不能为空",
        trigger: "blur",
      },
    ],
    worksType: [
      {
        required: true,
        message: "作品类型不能为空",
        trigger: "change",
      },
    ],
    worksSort: [
      {
        required: true,
        message: "显示顺序不能为空",
        trigger: "blur",
      },
    ],
    worksCover: [
      {
        required: true,
        message: "封面图片不能为空",
        trigger: "blur",
      },
    ],
    status: [
      {
        required: true,
        message: "状态不能为空",
        trigger: "change",
      },
    ],
  },
});

const { queryParams, form, rules, jobParams } = toRefs(data);

/** 查询作业上传列表 */
function getList() {
  loading.value = true;
  listJobTodo(queryParams.value)
    .then((response) => {
      jobTodoList.value = response.rows;
      total.value = response.total;
      loading.value = false;
    })
    .then(() => {
      jobTodoList.value.forEach((ele, num) => {
        jobList.value.forEach((v) => {
          if (v.jobId == ele.jobId) {
            jobTodoList.value[num].jobName = v.jobName;
          }
        });
      });
    });
}
/** 查询作业信息列表 */
function getJobList() {
  loading.value = true;
  listJob(jobParams.value)
    .then((response) => {
      jobList.value = response.rows;
      total.value = response.total;
      loading.value = false;
    })
    .then(() => {});
}
// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    jobId: null,
    userId: null,
    worksSrc: null,
    worksBrief: null,
    worksDescription: null,
    worksType: null,
    worksSort: null,
    worksCover: null,
    status: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null,
  };
  proxy.resetForm("jobTodoRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map((item) => item.jobId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加作业上传";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _jobId = row.jobId || ids.value;
  getJobTodo(_jobId).then((response) => {
    form.value = response.data;
    open.value = true;
    title.value = "修改作业上传";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["jobTodoRef"].validate((valid) => {
    if (valid) {
      if (form.value.jobId != null) {
        updateJobTodo(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addJobTodo(form.value).then((response) => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _jobIds = row.jobId || ids.value;
  proxy.$modal
    .confirm('是否确认删除作业上传编号为"' + _jobIds + '"的数据项？')
    .then(function () {
      return delJobTodo(_jobIds);
    })
    .then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
    })
    .catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download(
    "business/jobTodo/export",
    {
      ...queryParams.value,
    },
    `jobTodo_${new Date().getTime()}.xlsx`
  );
}

getJobList();
getList();
</script>
