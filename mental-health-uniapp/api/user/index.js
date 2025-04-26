import request from '@/utils/request'

// api地址
const api = {
  userInfo: 'user/info',
  save: 'user/saveInfo'
}

// 当前登录的用户信息
export const info = (param, option) => {
  const options = {
    isPrompt: true, 
    load: true,
    ...option
  }
  return request.get(api.userInfo, param, options)
}



// 保存信息
export const save = (param, option) => {
  return request.post(api.save, param)
}