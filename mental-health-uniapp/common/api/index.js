// const ht2tp = uni.$u.h2ttp
import request from '@/utils/request'
const api = {

    /**
     * 登录
     */
    login(params) {
        return request.post('/user/login', params)
    },

    /**
     * 找回密码
     */
    retrieve(params) {
        return request.post('login/retrieve', params)
    },

    /**
     * 退出
     */
    logout() {
        return request.get('logout')
    },
    /**
     * 注册
     */
    register(params) {
        return request.post('register', params)
    },

    /**
     * 上传
     */
    upload(filePath) {
  //       const params = {
  //           name: 'file',
  //           filePath: filePath,
  //           fileType: 'image'
  //       }
  //       return request.upload('upload', params)
		// // 文件上传大小, 6M
		const maxSize = 1024 * 1024 * 6;
		// 执行上传
		return new Promise((resolve, reject) => {
		  request.urlFileUpload({ files, maxSize })
		    .then(result => {
		      const fileIds = result.map(item => {
		          return item.data;
		      })
		      resolve(fileIds, result)
		    })
		    .catch(err => reject(err))
		})
    },

    /**
     * 好友列表
     */
    friendPage(params) {
        return request.get('friend', params)
    },

    /**
     * 发送好友消息
     */
    sendFriendMsg(params) {
        return request.post('friendMsg', params)
    },

    /**
     * 发送群消息
     */
    sendGroupMsg(params) {
        return request.post('groupMsg', params)
    },

    /**
     * 好友消息列表
     */
    friendMsgPage(params) {
        return request.get('friendMsg', params)
    },

    /**
     * 群组消息列表
     */
    groupMsgPage(params) {
        return request.get('groupMsg', params)
    },

    /**
     * 好友详情
     */
    friendDetail(friendId) {
        return request.get(`friend/${friendId}`)
    },

    /**
     * 好友更新
     */
    friendUpdate(params) {
        return request.put('friend', params)
    },

    /**
     * 好友删除
     */
    friendDelete(id) {
        return request.delete(`friend/${id}`)
    },

    /**
     * 用户详情
     */
    userDetail(userId) {
        return request.get(`user/${userId}`)
    },

    /**
     * 用户列表
     */
    userList(params) {
        return request.get(`user`, params)
    },

    /**
     * 用户更新
     */
    userUpdate(params) {
        return request.post('user/saveInfo', params)
    },

    /**
     * 用户更新密码
     */
    userPwd(params) {
        return request.put('user/pwd', params)
    },

    /**
     * 好友/群组 首页消息列表
     */
    messageList(params) {
        return request.post('message', params)
    },

    /**
     * 好友邀请
     */
    invitation(params) {
        return request.post('invitation', params)
    },

    /**
     * 邀请列表
     */
    invitationList(params) {
        return request.get('invitation', params)
    },

    /**
     * 再次邀请
     */
    repeatInvitation(params) {
        return request.put('invitation', params)
    },

    /**
     * 邀请-通过/拒绝
     */
    handleInvitation(params) {
        return request.put('invitation/handle', params)
    },

    /**
     * 分组新增
     */
    groupAdd(params) {
        return request.post('group', params)
    },

    /**
     * 分组列表
     */
    groupPage(params) {
        return request.get('group', params)
    },

    /**
     * 群组详情
     */
    groupDetail(groupId) {
        return request.get(`group/${groupId}`)
    },

    /**
     * 拉人入群
     */
    groupUserAdd(params) {
        return request.post(`group/pull`, params)
    },

    /**
     * 加入群组
     */
    groupJoin(params) {
        return request.post(`group/join`, params)
    },

    /**
     * 群组消息撤销
     */
    groupMsgHandle(params) {
        return request.put('group/msgHandle', params)
    },

    /**
     * 群组消息撤销
     */
    friendMsgHandle(params) {
        return request.put('friendMsg/msgHandle', params)
    },
}

export default api;
