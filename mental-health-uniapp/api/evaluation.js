import request from '@/utils/request'

// api地址
const api = {
  page: 'evaluation/page',
}


export const page = (param) => {
  return request.get(api.page, param)
}

