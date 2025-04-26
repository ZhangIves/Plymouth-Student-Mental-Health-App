<template>
	<view class="container">
		<!-- 页面头部 -->
		<view class="header">
			<view>
				<u-toast ref="uToast" />
				<view class="img-a">
					<view class="t-b">
						Hello!
						<br />
						Welcome to use
					</view>
				</view>
				<view class="login-view" v-if="!isRegister">
					<view class="t-login">
						<form class="cl">
							<view class="t-a">
								<!-- <text class="txt">username</text> -->
								<input type="number" name="phone" placeholder="Please enter your account" maxlength="11" v-model="account" />
							</view>
							<view class="t-a">
								<!-- <text class="txt">password</text> -->
								<input type="password" name="password" maxlength="18" placeholder="Please enter your password" v-model="password" />
							</view>
							<button v-if="isReset" @tap="handleSubmit()">Login</button>
							<button v-if="!isReset" @click="resetPwd()" style="background-color: #ff557f;"><text>Reset password</text></button>
							<!-- <view class="reg" @tap="reg()">短信登录</view> -->
							<view class="register" v-if="!isRegister" @click="toRegister()">Register</view>
							<view class="register" v-if="isRegister" @click="toRegister()">Login</view>
							<!-- <view class="register" v-if="isReset" @click="toReset()">Forgot password ?</view> -->
							<view class="register" v-if="!isReset" @click="toReset()">Return to login</view>
						</form>
					</view>
				</view>
				<view class="login-view" v-if="isRegister">
					<view class="t-login">
						<form class="cl">
							<view class="t-a">
								<!-- <text class="txt">昵称</text> -->
								<input type="text" name="fullName" placeholder="Please enter your name" maxlength="11" v-model="fullName" />
							</view>
							<view class="t-a">
								<!-- <text class="txt">手机号</text> -->
								<input type="number" name="phone" placeholder="Please enter your account" maxlength="11" v-model="account" />
							</view>
				
							<view class="t-a">
								<!-- <text class="txt">密码</text> -->
								<input type="password" name="password" maxlength="18" placeholder="Please enter your password" v-model="password" />
							</view>
							<view class="t-a">
								<!-- <text class="txt">确认密码</text> -->
								<input type="password" name="password1" placeholder="Please enter your password again" maxlength="18" v-model="password1" />
							</view>
							<view class="login-button" v-if="isRegister" @click="handleSubmit"><text>Register</text></view>
							<view class="register" v-if="isRegister" @click="toRegister()">Login</view>
							<!-- // <view class="register" v-if="!isReset" @click="toReset()">返回登录</view> -->
						</form>
					</view>
				</view>
			</view>

		</view>
		<!-- 账号注册表单 start-->
		<view class="login-form login-view" v-if="false" style="padding: 30rpx 60rpx;">
			<view class="form-item" v-if="isRegister">
				<input class="form-item--input" type="text" autocomplete="off" v-model="name" maxlength="30"
					value="" placeholder="请输入姓名" />
			</view>
			<view class="form-item" v-if="isRegister&&loginType === 'sms'">
				<picker @change="bindPickerChange" :value="index" :range="array">
						<view class="form-item--input">{{array[index]}}</view>
				</picker>

			</view>
			<!-- 手机号 -->
			<view class="form-item">
				<input class="form-item--input" type="text" v-model="account" maxlength="30" clearable="true"
					placeholder="请输入您的手机号" />
			</view>
			<!-- 密码 -->
			<view class="form-item">
				<input class="form-item--input" type="password" autocomplete="off" v-model="password" maxlength="30"
					minlength="1" value="" placeholder="请输入您的密码" />
			</view>
			<!-- 确认密码 -->
			<view class="form-item" v-if="isRegister">
				<input class="form-item--input" type="password" autocomplete="off" v-model="password1" maxlength="30"
					value="" placeholder="请再次输入密码" />
			</view>
			<!-- 按钮 -->
			<view class="login-button" v-if="!isRegister" @click="handleSubmit"><text>立即登录</text></view>
			
			<view class="login-button" v-if="isRegister" @click="handleSubmit"><text>立即注册</text></view>
			<view class="register" v-if="!isRegister" @click="toRegister()">还没有账号？去注册</view>
			<view class="register" v-if="isRegister" @click="toRegister()">已有账号？立即登录</view>
		</view>
	</view>
</template>

<script>
	import store from '@/store'
	import * as LoginApi from '@/api/login'
	import {
		throttle,
		debounce
	} from '@/utils/util'
	import * as Verify from '@/utils/verify'
	import {
		checkLogin,
		isWechat
	} from '@/utils/app'

	// 倒计时时长(秒)
	const times = 60

	// 表单验证场景
	const GET_CAPTCHA = 10
	const SUBMIT_LOGIN = 20
	const SUBMIT_LOGIN_ACCOUNT = 30
	const SUBMIT_REGISTER = 40

	export default {
		props: {
			// 是否存在第三方用户信息
			isParty: {
				type: Boolean,
				default: () => false
			},
			// 第三方用户信息数据
			partyData: {
				type: Object
			}
		},
		data() {
			return {
				// 是否注册新账号
				isRegister: false,
				isReset:true,
				// 登录方式
				loginType: 'account',
				// 正在加载
				isLoading: false,
				// 图形验证码信息
				captcha: "",
				// 图形验证码uuid
				captchaUuid: "",
				// 账号图形验证码信息
				captchaForAccount: "",
				// 短信验证码发送状态
				smsState: false,
				// 倒计时
				times,
				// 手机号
				mobile: '',
				// 账号
				account: '',
				// 密码
				password: '',
				// 确认密码
				password1: '',
				role:'',
				name: '',
				fullName:'',
				sex: '',
				// 图形验证码
				captchaCode: '',
				// 短信验证码
				smsCode: '',
				index: 0,
				 array: ['游泳教练', '健美教练', '巴西', '日本'],
			}
		},

		/**
		 * 生命周期函数--监听页面加载
		 */
		created() {
			// 获取图形验证码
			// this.getCaptcha();
			// CoachApi.rankInfo().then(res=>{
			// 	this.array = res.data.map(item=>{
			// 		return item.name
			// 	})
			// })
		},

		methods: {
			 bindPickerChange: function(e) {
			            console.log('picker发送选择改变，携带值为', e.detail.value)
			            this.index = e.detail.value
			        },
			// 切换登录方式
			switchLoginType(loginType) {
				this.loginType = loginType;
				this.mobile = "";
				this.account = "";
				this.password = "";
				this.password1 = "";
				this.smsCode = "";
			},
			// 注册新用户
			toRegister() {
				if (!this.isRegister) {
					this.isRegister = true;
				} else {
					this.isRegister = false;
				}
			},
			toReset(){
				if (!this.isReset) {
					this.isReset = true;
				} else {
					this.isReset = false;
				}
			},
			getCaptcha() {
				const app = this
				LoginApi.captcha()
					.then(result => {
						app.captcha = result.data.captcha;
						app.captchaUuid = result.data.uuid;
						app.captchaCode = "";
					})
			},

			// 点击发送短信验证码
			handelSmsCaptcha() {
				const app = this
				if (!app.isLoading && !app.smsState && app.formValidation(GET_CAPTCHA)) {
					app.sendSmsCaptcha();
				}
			},

			// 表单验证
			formValidation(scene) {
				const app = this
				// 验证获取短信验证码
				if (scene === GET_CAPTCHA) {
					if (!app.validteMobile(app.mobile) || !app.validteCaptchaCode(app.captchaCode)) {
						return false
					}
				}
				// 验证提交登录
				if (scene === SUBMIT_LOGIN) {
					if (!app.validteMobile(app.mobile) || !app.validteSmsCode(app.smsCode)) {
						return false
					}
				}
				// 验证账号登录
				if (scene === SUBMIT_LOGIN_ACCOUNT) {
					if (!app.validteAccount(app.account) || !app.validtePassword(app.password) || !app.validteCaptchaCode(
							app.captchaCode)) {
						return false
					}
				}
				// 验证提交注册
				if (scene === SUBMIT_REGISTER) {
					if (!app.validteAccount(app.account) || !app.validtePassword(app.password) || !app.validtePassword1(app
							.password1) || !app.validteCaptchaCode(app.captchaCode)) {
						return false
					}
					if (app.password !== app.password1) {
						this.$toast('两次输入的密码不一致');
						return false;
					}
				}
				return true;
			},

			// 验证手机号
			validteMobile(str) {
				if (Verify.isEmpty(str)) {
					this.$toast('请先输入手机号')
					return false
				}
				if (!Verify.isMobile(str)) {
					this.$toast('请输入正确格式的手机号')
					return false
				}
				return true
			},

			// 验证账号
			validteAccount(str) {
				if (Verify.isEmpty(str)) {
					this.$toast('请先输入您的手机号')
					return false
				}
				if (str.length != 11) {
					this.$toast('请输入正确格式的手机号')
					return false
				}
				return true
			},
			// 验证密码
			validtePassword(str) {
				if (Verify.isEmpty(str)) {
					this.$toast('请先输入您的密码')
					return false
				}
				if (str.length < 6) {
					this.$toast('密码不能少于6位')
					return false
				}
				return true
			},
			// 验证密码
			validtePassword1(str) {
				if (Verify.isEmpty(str)) {
					this.$toast('请再次输入您的密码')
					return false
				}
				return true
			},

			// 验证图形验证码
			validteCaptchaCode(str) {
				// if (Verify.isEmpty(str)) {
				// 	this.$toast('请先输入图形验证码')
				// 	return false
				// }
				return true
			},
			resetPwd(){
				const app = this
				if (app.formValidation(SUBMIT_LOGIN_ACCOUNT)) {
					LoginApi.resetPwd({
						phone: app.account,
						password: app.password,
					}).then(result=>{
						if(result.code === 200){
							uni.showToast({
								title: "重置成功!",
								icon: "success"
							});
						}else{
							this.$toast(result.message)
						}
					})
				}
				
			},

			// 验证短信验证码
			validteSmsCode(str) {
				if (Verify.isEmpty(str)) {
					this.$toast('请先输入短信验证码')
					return false
				}
				return true
			},

			// 请求发送短信验证码接口
			sendSmsCaptcha() {
				const app = this
				app.isLoading = true
				LoginApi.sendSmsCaptcha({
						captchaKey: app.captcha.key,
						captchaCode: app.captchaCode,
						mobile: app.mobile,
						uuid: app.captchaUuid
					})
					.then(result => {
						// 显示发送成功
						if (result.data) {
							app.$toast(result.message)
							// 执行定时器
							app.timer()
						} else {
							app.$error(result.message)
						}
					})
					.finally(() => app.isLoading = false)
			},

			// 执行定时器
			timer() {
				const app = this
				app.smsState = true
				const inter = setInterval(() => {
					app.times = app.times - 1
					if (app.times <= 0) {
						app.smsState = false;
						app.times = times;
						clearInterval(inter);
					}
				}, 1000)
			},

			// 点击提交
			handleSubmit() {
				const app = this
				if (!app.isLoading && !app.isRegister && app.loginType === 'account') {
					if (app.formValidation(SUBMIT_LOGIN_ACCOUNT)) {
						app.submitLogin();
					}
				}
				// 注册新账号
				if (!app.isLoading && app.isRegister) {
					if (app.formValidation(SUBMIT_REGISTER)) {
						app.submitRegister();
					}
				}

				return true
			},

			// 确认注册
			submitRegister() {
				const app = this
				app.isLoading = true
				if(app.loginType === 'sms'){
					store.dispatch('Register2', {
							phone: app.account,
							fullName:app.name,
							password: app.password,
							password1: app.password1,
							type:app.array[app.index]
							// captchaKey: app.captcha.key,
							// captchaCode: app.captchaCode,
							// uuid: app.captchaUuid
						})
						.then(result => {
							// 显示登录信息
							app.$toast(result.message)
							if (result.code === 200) {
								// 注册成功，去认证
								// app.isNeedAuth(result.data);
								app.isLoading = true
								store.dispatch('Login2', {
										phone: app.account,
										password: app.password,
									})
									.then(result => {
										// 显示登录信息
										app.$toast(result.message)
										console.log("login-main-submit:",result)
										if (result.code === 200) {
											// 登录成功，去认证
											app.isNeedAuth(result.data);
										} else {
											app.$error(result.message);
											// app.getCaptcha();
										}
									})
									.finally(() => app.isLoading = false)
							} else {
								app.$error(result.message);
								// app.getCaptcha();
							}
						})
						.finally(() => app.isLoading = false)
					}
					if(app.loginType === 'account'){
						store.dispatch('Register', {
								phone: app.account,
								fullName:app.fullName,
								password: app.password,
								password1: app.password1,
								// captchaKey: app.captcha.key,
								// captchaCode: app.captchaCode,
								// uuid: app.captchaUuid
							})
							.then(result => {
								// 显示登录信息
								app.$toast(result.message)
								if (result.code === 200) {
									// 注册成功，去认证
									// app.isNeedAuth(result.data);
									app.isLoading = true
									store.dispatch('Login', {
											username: app.account,
											password: app.password,
										})
										.then(result => {
											// 显示登录信息
											app.$toast(result.message)
											console.log("login-main-submit:",result)
											if (result.code === 200) {
												// 登录成功，去认证
												app.isNeedAuth(result.data);
											} else {
												app.$error(result.message);
												app.getCaptcha();
											}
										})
										.finally(() => app.isLoading = false)
								} else {
									app.$error(result.message);
									app.getCaptcha();
								}
							})
							.finally(() => app.isLoading = false)
						}
			
			},

			// 确认登录
			submitLogin() {
				const app = this
				app.isLoading = true
				if(app.loginType === 'account'){
					store.dispatch('Login', {
							username: app.account,
							password: app.password,
						})
						.then(result => {
							// 显示登录信息
							app.$toast(result.message)
							console.log("login-main-submit:",result)
							if (result.code === 200) {
								// 登录成功，去认证
								app.isNeedAuth(result.data);
							} else {
								app.$error(result.message);
								// app.getCaptcha();
							}
						})
						.finally(() => app.isLoading = false)
				}
			},

			/**
			 * 去授权认证
			 * */
			isNeedAuth(loginInfo) {
				console.log("loginInfo == ", loginInfo);
				if (isWechat() && loginInfo && loginInfo.appId && loginInfo.domain && !loginInfo.openId) {
					console.log('to auth...')
					// #ifdef H5
					const appId = loginInfo.appId;
					const domain = loginInfo.domain;
					const redirect_uri = encodeURIComponent(domain + "#pages/login/auth");
					const url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=" +
						redirect_uri + "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
					window.location.href = url;
					return true;
					// #endif
				}
				uni.switchTab({
					url: '/pages/index/index',
				});
				// this.$navTo('/pages/index/index');
			},

			/**
			 * 登录成功-跳转回原页面
			 */
			onNavigateBack(delta) {
				console.log("navigate-back:",delta)
				uni.navigateBack({
					delta: Number(delta || 1)
				})
				// uni.navigateTo({
				// 	url:'/pages/user/index'
				// })
			}
		}
	}
</script>

<style lang="scss" scoped>
	.container {
		// padding: 30rpx 60rpx 100rpx 60rpx;
		min-height: 100vh;
		background-color: #fff;

		.fast-icon {
			margin-bottom: 80rpx;
			font-size: 50rpx;
			cursor: pointer;
		}
	}

	// 页面头部
	.header {
		margin-bottom: 50rpx;

		.title {
			color: #191919;
			font-size: 33rpx;
			height: 88rpx;
			padding: 10rpx;
			cursor: pointer;
			text-align: center;

			.item {
				width: 50%;
				height: 88rpx;
				float: left;
				text-align: center;
			}

			.active {
				border-bottom: #ff3800 8rpx solid;
				padding-bottom: 10rpx;
				text-align: center;
			}
		}
	}

	// 输入框元素
	.form-item {
		display: flex;
		padding: 18rpx;
		border-bottom: 2rpx solid #cccccc;
		margin-bottom: 25rpx;
		height: 110rpx;

		.pre-mobile {
			line-height: 75rpx;
			color: #888888;
		}

		&--input {
			font-size: 26rpx;
			letter-spacing: 1rpx;
			flex: 1;
			height: 100%;
		}

		&--parts {
			min-width: 100rpx;
			height: 100%;
		}

		// 图形验证码
		.captcha {
			height: 100%;

			.image {
				display: block;
				width: 192rpx;
				height: 80rpx;
			}
		}

		// 短信验证码
		.captcha-sms {
			font-size: 22rpx;
			line-height: 50rpx;
			padding-right: 20rpx;

			.activate {
				color: #cea26a;
				border: #ccc solid 1px;
				padding: 18rpx;
				border-radius: 8rpx;
			}

			.un-activate {
				color: #9e9e9e;
			}
		}
	}


	// 登录按钮
	.login-button {
		width: 100%;
		height: 86rpx;
		margin-top: 70rpx;
		background: #0055ff;
		font-weight: 600;
		text-align: center;
		line-height: 86rpx;
		color: #fff;
		border-radius: 80rpx;
		box-shadow: 0px 10px 20px 0px rgba(0, 0, 0, 0.1);
		letter-spacing: 5rpx;
	}

	// 去注册
	.register {
		margin-top: 40rpx;
		text-align: right;
	}

	// 微信授权登录
	.wechat-auth {
		display: flex;
		justify-content: center;
		margin-top: 40rpx;

		.icon {
			width: 38rpx;
			height: 38rpx;
			margin-right: 15rpx;
		}

		.title {
			font-size: 28rpx;
			color: #666666;
		}
	}
</style>

<style>
.txt {
	font-size: 32rpx;
	font-weight: bold;
	color: #333333;
}
.img-a {
	width: 100%;
	height: 450rpx;
	background-image: url(https://zhoukaiwen.com/img/loginImg/head.png);
	background-size: 100%;
}
.reg {
	font-size: 28rpx;
	color: #fff;
	height: 90rpx;
	line-height: 90rpx;
	border-radius: 50rpx;
	font-weight: bold;
	background: #f5f6fa;
	color: #000000;
	text-align: center;
	margin-top: 30rpx;
}

.login-view {
	width: 100%;
	position: relative;
	margin-top: -120rpx;
	background-color: #ffffff;
	border-radius: 8% 8% 0% 0;
}

.t-login {
	width: 600rpx;
	margin: 0 auto;
	font-size: 28rpx;
	padding-top: 80rpx;
}

.t-login button {
	font-size: 28rpx;
	background: #2796f2;
	color: #fff;
	height: 90rpx;
	line-height: 90rpx;
	border-radius: 50rpx;
	font-weight: bold;
}

.t-login input {
	height: 90rpx;
	line-height: 90rpx;
	margin-bottom: 50rpx;
	border-bottom: 1px solid #e9e9e9;
	font-size: 28rpx;
}

.t-login .t-a {
	position: relative;
}

.t-b {
	text-align: left;
	font-size: 42rpx;
	color: #ffffff;
	padding: 130rpx 0 0 70rpx;
	font-weight: bold;
	line-height: 70rpx;
}

.t-login .t-c {
	position: absolute;
	right: 22rpx;
	top: 22rpx;
	background: #5677fc;
	color: #fff;
	font-size: 24rpx;
	border-radius: 50rpx;
	height: 50rpx;
	line-height: 50rpx;
	padding: 0 25rpx;
}

.t-login .t-d {
	text-align: center;
	color: #999;
	margin: 80rpx 0;
}

.t-login .t-e {
	text-align: center;
	width: 250rpx;
	margin: 80rpx auto 0;
}

.t-login .t-g {
	float: left;
	width: 50%;
}

.t-login .t-e image {
	width: 50rpx;
	height: 50rpx;
}

.t-login .t-f {
	text-align: center;
	margin: 150rpx 0 0 0;
	color: #666;
}

.t-login .t-f text {
	margin-left: 20rpx;
	color: #aaaaaa;
	font-size: 27rpx;
}

.t-login .uni-input-placeholder {
	color: #aeaeae;
}

.cl {
	zoom: 1;
}

.cl:after {
	clear: both;
	display: block;
	visibility: hidden;
	height: 0;
	content: '\20';
}
</style>
