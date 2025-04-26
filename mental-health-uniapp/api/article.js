import request from '@/utils/request'

// api地址
const api = {
  list: 'article/list',
  page:'article/page',
  detail: 'article/detail',
  cate: 'article/cateList',
}


// 文章列表
export const page = (param) => {
  return request.get(api.page, param)
}


// 文章列表
export const list = (param) => {
  return request.get(api.list, param)
}

// 文章详情
export const detail = (articleId) => {
  return request.get(api.detail, articleId)
}

// 文章分类列表
export const cateList = (param) => {
  return request.post(api.cate, param)
}