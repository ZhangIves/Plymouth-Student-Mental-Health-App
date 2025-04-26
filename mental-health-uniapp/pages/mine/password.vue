<template>
  <view class="container" style="padding: 20rpx;">
	<navbar :isBack="true" type="second" title="Password"></navbar>
    <view class="info-item">
      <view class="contacts">
        <text class="name">Old password：</text>
		<input class="weui-input value" password type="text" placeholder="Please enter" v-model="passwordOld"/>
        <!-- <input class="weui-input value" type="password" disabled="false"  v-model="passwordOld" placeholder="请输入旧密码"/> -->
      </view>
    </view>
    <view class="info-item">
      <view class="contacts">
        <text class="name">Password：</text>
		<input class="weui-input value" password type="text" placeholder="Please enter" v-model="password"/>
        <!-- <input class="weui-input value" type="password" disabled="false" @click="inputPassword(2)" v-model="password" placeholder="请输入新密码"/> -->
      </view>
    </view>
    <view class="info-item">
      <view class="contacts">
        <text class="name">Confirm password：</text>
		<input class="weui-input value" password type="text" placeholder="Please enter" v-model="passwordCopy"/>
        <!-- <input class="weui-input value" type="password" disabled="false" @click="inputPassword(3)" v-model="passwordCopy" placeholder="请输入新密码确认"/> -->
      </view>
    </view>
    <!-- 底部操作按钮 -->
    <view class="footer-fixed">
      <view class="btn-wrapper">
        <view class="btn-item btn-item-main" @click="doSubmit()">Submit</view>
      </view>
    </view>
    
    <!-- 设置密码对话框 -->
    <!-- <key-words :mix="true" :title="title" :show_key="showPass"  @closeFuc="closeFuc" @getPassword="getPassword"></key-words> -->
  </view>
</template>

<script>
  import * as UserApi from '@/api/user'
  import store from '@/store'
  export default {
    components: {
    },
    data() {
      return {
        //当前页面参数
        options: {},
        // 正在加载
        isLoading: false,
        userInfo: {},
        qrCode: "",
        title: "",
        showPass: false,
        popType: 1,
        password: "",
        passwordCopy: "",
        passwordOld: "",
        hasPassword: ""
      }
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
      this.hasPassword = options.hasPassword;
    },

    methods: {
      // 关闭密码对话框
      closeFuc() {
         this.showPass = false;
      },
      // 输入密码
      inputPassword(type) {
         if (type == 1) {
             this.title = "请输入旧密码";
         } else if (type == 2) {
             this.title = "请输入新密码";
         } else {
             this.title = "请输入新密码确认";
         }
         this.popType = type;
         this.showPass = true;
      },
      // 获取输入的密码
      getPassword(password) {
         if (this.popType == 1) {
             this.passwordOld = password.password;
         } else if (this.popType == 2) {
             this.password = password.password;
         } else {
             this.passwordCopy = password.password;
         }
         this.closeFuc();
      },
      // 提交保存
      doSubmit() {
         const app = this;
         app.isLoading = true;
         if (app.hasPassword == 'Y' && (!app.password || !app.passwordOld)) {
             return false;
         }
		 if (app.password ===''|| app.passwordCopy === '') {
		    this.$tips.error("Please enter complete information first")
		     return false;
		 }
		 if (app.password.length < 6) {
		    this.$tips.toast("Password must not be less than 6 characters")
		     return false;
		 }
         if (app.password != app.passwordCopy) {
			 this.$tips.error("Inconsistent input of new password")
             // app.$error('！');
             return false;
         }
         UserApi.save({"password": app.password, "passwordOld": app.passwordOld})
             .then(result => {
               app.isLoading = false;
               if (result.code == 200) {
				    this.$tips.success("Password changed successfully")
					uni.navigateBack(-1)
               } else {
				    this.$tips.error(result.message)
               }
         }).catch(err => {
             app.isLoading = false;
         })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .info-list {
      padding-bottom: 30rpx;
      margin-top: 30rpx;
  }

  // 项目内容
  .info-item {
    margin: 20rpx auto 20rpx auto;
    padding: 30rpx 40rpx;
    width: 94%;
    box-shadow: 0 1rpx 5rpx 0px rgba(0, 0, 0, 0.05);
    border-radius: 16rpx;
    background: #fff;
    .avatar-warp {
        line-height: 120rpx;
    }
  }

  .contacts {
    font-size: 30rpx;
    height: 40rpx;
    .name {
      margin-left: 0px;
      float: left;
      margin-right: 10rpx;
      line-height: 40rpx;
    }
    .value {
        float:right;
        color:#999999;
        text-align: right;
        .second {
            margin-left: .6rem;
        }
    }
    .vcode {
        float: left;
        line-height: 40rpx;
    }
    .password {
        text-align: right;
        float: left;
        padding-right: 5rpx;
    }
    .avatar {
        width: 120rpx;
        height: 120rpx;
        border-radius: 120rpx;
        border: solid 1px #cccccc;
        float: right;
    }
  }

  // 底部操作栏
  .footer-fixed {
    z-index: 11;
    box-shadow: 0 -4rpx 40rpx 0 rgba(144, 52, 52, 0.1);
    margin-top: 80rpx;
    .btn-wrapper {
      height: 100%;
      display: flex;
      text-align: center;
      align-items: center;
      padding: 0 30rpx;
      margin-bottom: 10rpx;
    }

    .btn-item {
      flex: 1;
      font-size: 28rpx;
      height: 80rpx;
      line-height: 80rpx;
      text-align: center;
      color: #fff;
      border-radius: 40rpx;
    }

    .btn-item-main {
      background: linear-gradient(to right, #0000ff, #0055ff);
    }
    
    .btn-item-back {
      margin-top: 20rpx;
      background: #FFFFFF;
      border: 1px solid #0055ff;
      color: #666666;
    }

  }
</style>
