import request from '@/utils/request'

// api地址
const api = {
  register: 'user/register',
  login: 'user/login',
  info: 'user/info',
  captcha: 'captcha/getCode',
}

// 用户注册
export function register(data) {
  return request.post(api.register, data)
}

// 用户登录
export function login(data) {
  return request.post(api.login, data)
}

// 用户信息
export function info() {
  return request.get(api.info)
}

// 图形验证码
export function captcha() {
  return request.get(api.captcha)
}

