import request from '@/utils/request'

// api地址
const api = {
	friend: 'friend',
	friendMsg: 'friendMsg',
	groupMsg: 'groupMsg',
	messageList:'message',
	captcha: 'captcha/getCode',
}
/**
 * 好友列表
 */
export const friend = (param) => {
	return request.get(api.friend, {
		...param
	})
}
/**
 * 发送好友消息
 */
export const sendFriendMsg = (data) => {
	return request.post(api.friendMsg, data)
}
/**
 * 发送群消息
 */
export const sendGroupMsg = (data) => {
		return request.post(api.groupMsg, data)
}

/**
 * 好友消息列表
 */
export const friendMsgPage = (param) => {
	return request.get(api.friendMsg,params)
}

export const buymemberCourse = (param) => {
	return request.post(api.buymemberCourse, param)
}

/**
 * 好友/群组 首页消息列表
*/
export const messageList = (data) => {
    return request.post(api.messageList, data)
}

/**
 * 群组详情
 */
export const groupDetail = (groupId) => { 
    return request.get(`group/${groupId}`)
}