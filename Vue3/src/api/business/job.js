import request from '@/utils/request'

// 查询作业信息列表
export function listJob(query) {
  return request({
    url: '/business/job/list',
    method: 'get',
    params: query
  })
}

// 查询作业信息详细
export function getJob(jobId) {
  return request({
    url: '/business/job/' + jobId,
    method: 'get'
  })
}

// 新增作业信息
export function addJob(data) {
  return request({
    url: '/business/job',
    method: 'post',
    data: data
  })
}

// 修改作业信息
export function updateJob(data) {
  return request({
    url: '/business/job',
    method: 'put',
    data: data
  })
}

// 删除作业信息
export function delJob(jobId) {
  return request({
    url: '/business/job/' + jobId,
    method: 'delete'
  })
}
