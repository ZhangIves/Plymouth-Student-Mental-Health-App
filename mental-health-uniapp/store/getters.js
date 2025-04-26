const getters = {
	token: state => state.user.token,
	userId: state => state.user.userId,
	platform: state => state.app.platform,
	userInfo: state => state.user.userInfo,
	msgNum: state => {
		return state.user.msgNum
	},
	invitationNum: state => {
		return state.user.invitationNum
	},
}

export default getters