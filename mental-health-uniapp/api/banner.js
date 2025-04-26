import request from '@/utils/request'

// api地址
const apiUri = {
  home: 'clientApi/page/home',
  banner: 'home/banner'
}

// 页面数据
export function home() {
  return request.get(apiUri.home)
}

// 页面数据
export function banner() {
  return request.get(apiUri.banner)
}