import request from '@/utils/request'

// 查询作业上传列表
export function listJobTodo(query) {
  return request({
    url: '/business/jobTodo/list',
    method: 'get',
    params: query
  })
}

// 查询作业上传详细
export function getJobTodo(jobId) {
  return request({
    url: '/business/jobTodo/' + jobId,
    method: 'get'
  })
}

// 新增作业上传
export function addJobTodo(data) {
  return request({
    url: '/business/jobTodo',
    method: 'post',
    data: data
  })
}

// 修改作业上传
export function updateJobTodo(data) {
  return request({
    url: '/business/jobTodo',
    method: 'put',
    data: data
  })
}

// 删除作业上传
export function delJobTodo(jobId) {
  return request({
    url: '/business/jobTodo/' + jobId,
    method: 'delete'
  })
}
