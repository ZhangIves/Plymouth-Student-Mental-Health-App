<template>
  <view class="info">
	<navbar type="second" back="false" title="Mine"></navbar>
    <!-- <navbar :isBack="true" type="second" title="我的资料"></navbar> -->
    <!-- 注意，如果需要兼容微信小程序，最好通过setRules方法设置rules规则 -->
    <view class="container">
      <u--form
          labelPosition="left"
          :model="model"
          :rules="rules"
          ref="form1"
      >
        <u-form-item
            label="Phone"
            prop="userInfo.phone"
            borderBottom
            ref="item1"
        >
          <u--input
			  class="loong-form-input"
              v-model="model.userInfo.phone"
              disabled
              disabledColor=""
              border="none"
          ></u--input>
        </u-form-item>
        <u-form-item
            label="Name"
            prop="userInfo.fullName"
            borderBottom
            ref="item"
        >
          <u--input
				class="loong-form-input"
              v-model="model.userInfo.fullName"
              border="none"
              placeholder="Please enter a user name"
          ></u--input>
        </u-form-item>
<!--        <u-form-item
            label="邮箱"
            prop="userInfo.email"
            borderBottom
            ref="item"
        >
          <u--input
              v-model="model.userInfo.email"
              border="none"
              placeholder="请输入邮箱"
          ></u--input>
        </u-form-item> -->
        <u-form-item
            label="Sex"
            prop="userInfo.sex"
            borderBottom
            @click="showSex = true;"
            ref="item1"
        >
          <u--input
		  class="loong-form-input"
              :value="model.userInfo.sex==0?'female':'male'"
              disabled
              disabledColor="#ffffff"
              placeholder="Please select gender"
              border="none"
          ></u--input>
          <u-icon
              slot="right"
              name="arrow-right"
          ></u-icon>
        </u-form-item>
<!--        <u-form-item
            label="生日"
            prop="userInfo.birthday"
            borderBottom
            @click="showBirthday = true; hideKeyboard()"
            ref="item1"
        >
          <u--input
              v-model="model.userInfo.birthday"
              disabled
              disabledColor="#ffffff"
              placeholder="请选择生日"
              border="none"
          ></u--input>
          <u-icon
              slot="right"
              name="arrow-right"
          ></u-icon>
        </u-form-item> -->

<!--        <u-form-item
            label="简介"
            prop="intro"
            ref="item3"
        >
          <u--textarea
              placeholder="请输入简介"
              v-model="model.userInfo.intro"
              maxlength="128"
          ></u--textarea>
        </u-form-item> -->

      </u--form>

      <u-button
          type="primary"
          text="提交"
          customStyle="margin-top: 50px"
          @click="submit"
      ></u-button>

      <u-action-sheet
          :show="showSex"
          :actions="actions"
          title="Please select gender"
          @close="sexClose"
          @select="sexSelect"
      >
      </u-action-sheet>
      <u-datetime-picker
          :show="showBirthday"
          :value="birthday"
          :minDate="0"
          :maxDate="1650596800000"
          mode="date"
          closeOnClickOverlay
          @confirm="birthdayConfirm"
          @cancel="birthdayClose"
          @close="birthdayClose"
      ></u-datetime-picker>
    </view>
  </view>
</template>

<script>
import {
  mapGetters
} from "vuex"
import * as UserApi from '@/api/user/index.js'
export default {
  data() {
    return {
      showSex: false,
      showBirthday: false,
      birthday: Number(new Date()),
	  avatar:'',
      model: {
        userInfo: {
          name: '',
          sex: '',
          intro: '',
          birthday: '',
          email: ''
        },
      },
      actions: [
        {
          name: 'female',
          value: 0
        },
        {
          name: 'male',
          value: 1
        }
      ],
      rules: {
        'userInfo.nickname': {
          type: 'string',
          required: true,
          message: 'Please enter a user name',
          trigger: ['blur', 'change']
        },
        'userInfo.sex': {
          type: 'number',
          required: true,
          message: 'Please select gender',
          trigger: ['blur', 'change']
        },
        'userInfo.birthday': {
          type: 'string',
          required: true,
          message: '请选择生日',
          trigger: ['change']
        },
        'userInfo.email': {
          type: 'email',
          required: true,
          message: '请填写邮箱',
          trigger: ['blur', 'change']
        }
      },
    }
  },
  computed: {
    ...mapGetters(['userInfo'])
  },
  methods: {
    // 性别选择框关闭
    sexClose() {
      this.showSex = false
      this.$nextTick(() => {
        this.$refs.form1.validateField('userInfo.sex')
      })
    },
    // 性别选择
    sexSelect(e) {
      this.model.userInfo.sex = e.value
      this.$nextTick(() => {
        this.$refs.form1.validateField('userInfo.sex')
      })
    },
    // 生日选择框关闭
    birthdayClose() {
      this.showBirthday = false
      this.$refs.form1.validateField('userInfo.birthday')
    },
    // 生日确认
    birthdayConfirm(e) {
      this.showBirthday = false
      this.model.userInfo.birthday = uni.$u.timeFormat(e.value, 'yyyy-mm-dd')
      this.$refs.form1.validateField('userInfo.birthday')
    },
    // 用户信息
    async info() {
      const userId = this.userInfo.id
      const result = await this.$api.userDetail(userId)
      if (result.code) {
        this.model.userInfo = result.data
		console.log("phone",result.data.phone)
		console.log("fullName",result.data.fullName)
      }
    },
	// 提交
    submit() {
      // 如果有错误，会在catch中返回报错信息数组，校验通过则在then中返回true
      this.$refs.form1.validate().then(async res => {
        // 调取接口进行数据更新
		UserApi.save(this.model.userInfo).then(result => {
			if (result.code) {
			  this.$tips.success("信息更新成功")
			}
		})
      })
    },
    init() {
      this.info()
    }
  },
  mounted() {
    this.init()
  }
};
</script>

<style lang="scss">
.container {
  padding: 40upx;
  background: #fff;
}
.loong-form-input input{
	text-align: right;
	padding-left: 30rpx!important;
	margin-left: 30rpx;
	
}
</style>
