import request from '@/utils/request'

// 查询VIEW列表
export function listView(query) {
  return request({
    url: '/business/view/list',
    method: 'get',
    params: query
  })
}

// 查询VIEW详细
export function getView(userId) {
  return request({
    url: '/business/view/' + userId,
    method: 'get'
  })
}

// 新增VIEW
export function addView(data) {
  return request({
    url: '/business/view',
    method: 'post',
    data: data
  })
}

// 修改VIEW
export function updateView(data) {
  return request({
    url: '/business/view',
    method: 'put',
    data: data
  })
}

// 删除VIEW
export function delView(userId) {
  return request({
    url: '/business/view/' + userId,
    method: 'delete'
  })
}
