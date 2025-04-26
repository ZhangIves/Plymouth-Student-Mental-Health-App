<template>
  <view class="mine">

    <view class="container">
      <view class="avatar">
        <u-row
            justify="space-between"
            gutter="-20"
        >
          <u-col span="3" @click="chooseImage">
<!--            <u-upload
                :fileList="avatarList"
                @afterRead="afterRead"
                name="file"
                multiple
                :maxCount="1"
                width="150"
                height="150"
            > -->
              <image :src="avatar"
                     mode="aspectFill" style="width: 60px;height: 60px;border-radius: 50%;"></image>
            <!-- </u-upload> -->
          </u-col>
          <u-col span="9">
            <u-col span="12">
              <u--text  type="primary" :text="user.username" bold></u--text>
            </u-col>
            <u-col span="12">
              <u--text  type="success" :text="user.fullName"></u--text>
            </u-col>
          </u-col>
        </u-row>
      </view>
      <view class="main">
        <u-cell-group :border="false">
<!--          <u-cell
              size="large"
              title="My business card"
              icon="integral"
              isLink
              @click="toCard"
          ></u-cell> -->
          <u-cell
              size="large"
              title="My Profile"
              icon="email"
              isLink
              @click="toInfo"
          ></u-cell>
          <u-cell
              size="large"
              title="Change Password"
              icon="info-circle"
              isLink
              @click="toUpdatePwd"
          ></u-cell>
        </u-cell-group>

        <view class="row" v-if="token">
          <u-button text="Exit" type="error" @click="logout"></u-button>
        </view>
		<view class="row" v-else>
		  <u-button text="Login" type="success" @click="logout"></u-button>
		</view>
      </view>
    </view>
    <u-popup :show="show" mode="center" @close="close" bgColor="transparent">
      <view>
        <w-qrcode :options="options"></w-qrcode>
      </view>
    </u-popup>
    <!--修改密码-->
    <u-modal
        :asyncClose="true"
        title="Change Password"
        :show="modalShow"
        closeOnClickOverlay
        showCancelButton
        @close="() => modalShow = false"
    >
      <view class="slot-content" style="width: 100%">
        <u-alert title="Please enter the old and new passwords before" type="info" :show-icon="true"></u-alert>
        <u--input
            style="margin-top: 10px"
            placeholder="Please enter the old password"
            border="surround"
            v-model="updatePwd.oldPwd"
        ></u--input>
        <u--input
            style="margin-top: 10px"
            placeholder="Please enter a new password"
            border="surround"
            v-model="updatePwd.newPwd"
        ></u--input>
        <u--input
            style="margin-top: 10px"
            placeholder="Please confirm the new password"
            border="surround"
            v-model="updatePwd.comfirmPwd"
        ></u--input>
      </view>
      <u-button
          slot="confirmButton"
          text="Reset"
          type="success"
          shape="circle"
          @click="reset"
      ></u-button>
    </u-modal>
  </view>
</template>

<script>
import {
  mapActions,
  mapGetters
} from "vuex"
import * as UploadApi from '@/api/upload.js'
export default {

  data() {
    return {
      // 二维码展示标识
      show: false,
      // 修改密码模态框
      modalShow:false,
      // 头像列表
      avatarList:[] ,
      // 头像
      avatar: "",
	  flagA:false,
      // 二维码参数
      options: {
        code: 'https://qm.qq.com/cgi-bin/qm/qr?k=LKqML292dD2WvwQfAJXBUmvgbiB_TZWF&noverify=0',// 生成二维码的值
        border: {
          color: ['#F27121', '#8A2387', '#1b82d2'], //边框颜色支持渐变色 最多10种颜色 如果默认黑色此属性不需要传
          opacity: 0.6, //边框透明度 默认为1不透明 0~1
          lineWidth: 6, //边框宽度
          degree: 15 //边框圆角度数 默认5
        },
        size: 460,// 460代表生成的二维码的宽高均为460rpx
      },
      // 用户信息
      user:{
        // 用户名
        username:'',
      },
      // 更改密码对象
      updatePwd:{
        // 老密码
        oldPwd:'',
        // 新密码
        newPwd:'',
        // 确认密码
        comfirmPwd:'',
      },
      // 邀请数
      invitationNum: this.$invitationNum
    }
  },
  computed: {
    ...mapGetters(['userInfo','token'])
  },
  methods: {
    ...mapActions(["mLogout"]),
    async afterRead(file, lists, name) {
      // const result = await this.$api.upload(file.file[0].url)
      // if (result.success) {
      //   const data = result.data
      //   this.avatar = this.$config.baseUrl + data.url
        // 入库更新用户信息
        this.updateInfo(this.avatar)
      // }
    },
	/**
	 * 退出登录
	 */
	 logout() {
	     this.$store.dispatch('Logout')
	     this.$navTo('pages/login/index')
	 },
    // 我的名片
    toCard() {
      this.show = true
    },
    // 个人信息
    toInfo() {
      this.$Router.push({path: '/pages/mine/info'})
    },
    close() {
      this.show = false
    },
    // 修改密码
    toUpdatePwd(){
      // this.modalShow = true
	  this.$Router.push({path: '/pages/mine/password'})
    },
    async updateInfo(avatar){
		this.chooseImage()
		if(this.flagA){
			this.user['avatar'] = this.avatar
			const result = await this.$api.userUpdate(this.user)
			if(result.code){
				this.$tips.success("Avatar updated successfully")
			}
		}
    },
	// 选择图片
	chooseImage() {
		const app = this
		app.flagA = false
		uni.chooseImage({
			count: 1,
			sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
			sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
			success({tempFiles}) {
				const imageList = tempFiles;
				return new Promise((resolve, reject) => {
					if (imageList.length > 0) {
						UploadApi.image(imageList)
							.then(files => {
								if (files && files.length > 0) {
									// app.model.userInfo.avatar = files[0].fileName;
									app.avatar = files[0].domain + files[0].fileName;
									console.log(app.avatar)
									app.flagA = true
									app.user['avatar'] = app.avatar
									const result =  app.$api.userUpdate(app.user)
									if(result.code){
										app.$tips.success("Avatar updated successfully")
									}
								}
								resolve(files)
							})
							.catch(err => reject(err))
					} else {
						resolve()
					}
				})
			}
		});
	},
    async mineInfo() {
      const userId = this.userInfo.id
      this.options.code ="friend=" + userId
	  if(userId === undefined){
		  this.$tips.error("Please log in first.！")
		  return
	  }else{
		  const res = await this.$api.userDetail(userId)
		  if(!res.code){
		    this.$tips.error(res.message)
		    return
		  }
		  this.user = res.data
		  this.avatar = this.user.avatar?this.user.avatar:'/static/img/avatar-1.jpeg'
	  }

    },
    // 密码重置
    async reset(){
      const {oldPwd, newPwd, comfirmPwd} = this.updatePwd

      if(!oldPwd || !newPwd || !comfirmPwd){
        uni.$u.toast("Please enter the old and new passwords!")
        return
      }
      if(oldPwd.length<6 || newPwd.length<6 || comfirmPwd.length<6){
        uni.$u.toast("Please ensure that the password length is at least 6 characters long!")
        return
      }
      if(newPwd != comfirmPwd){
        uni.$u.toast("The new password does not match the confirmed password!")
        return
      }
      const result = await this.$api.userPwd(this.updatePwd)
      if (result.success) {
        uni.$u.toast("Operation successful")
        this.modalShow = false
      }else {
        uni.$u.toast(result.message)
      }
    },
    init() {
      this.mineInfo()
    },
  },
  onShow() {
    this.init()
  },
  mounted() {
    // this.init()
  }
}
</script>

<style lang="scss">
.container {
  padding: 40upx;

  .main {
    margin-top: 40upx;
    border-top: 1px solid #efefef;

    .row {
      margin-top: 10vh;
    }
  }
}
</style>
