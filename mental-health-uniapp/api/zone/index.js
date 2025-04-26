import request from '@/utils/request'

// api地址
const api = {
  list: 'zone/list',
  page:'zone/page',
  save:'zone/save',
  detail: 'zone/detail',
  cate: 'zone/type',
  comment: 'zone/comment',
}


export const page = (param) => {
  return request.get(api.page, param)
}

// 发布
export const save = (param, option) => {
  return request.post(api.save, param)
}
export const list = (param) => {
  return request.get(api.list, param)
}

export const detail = (articleId) => {
  return request.get(api.detail, articleId)
}

export const cateList = (param) => {
  return request.get(api.cate, param)
}

// 评论
export const comment = (param, option) => {
  return request.post(api.comment, param)
}