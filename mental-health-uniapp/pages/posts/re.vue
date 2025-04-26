<template>
  <view class="add">
    <navbar :isBack="true" type="second" title="发布话题"></navbar>
    <view class="container">
      <u--form
          labelPosition="left"
          :model="model"
          :rules="rules"
          ref="form"
      >
        <u-form-item
            label="话题"
            prop="group.name"
            borderBottom
            ref="item"
            labelWidth="80"
        >
          <u--input
              v-model="model.group.name"
              placeholder="请输入话题"
              border="none"
          ></u--input>
        </u-form-item>
		<u-form-item label="游戏" prop="group.typeId" borderBottom ref="item" labelWidth="80" @click="showType = true; hideKeyboard()">
			<u--input
				v-model="model.group.typeId"
				disabled
				disabledColor="#ffffff"
				placeholder="请选择游戏分区"
				border="none"></u--input>
				<u-icon
						slot="right"
						name="arrow-right"
				></u-icon>
			  <!-- <u-picker :show="show" :columns="columns" keyName="label"></u-picker> -->
		</u-form-item>
        <u-form-item
            label="内容"
            prop="group.notice"
            borderBottom
            ref="item"
            labelWidth="80"
        >
		<!-- <editor v-model="model.group.notice"></editor> -->
		 <!-- <u-parse :content="article" @preview="preview" @navigate="navigate" noData="正在加载中..." />  -->
		 <!-- <rich-text :nodes="model.group.notice"></rich-text> -->
		<u--textarea v-model="model.group.notice" placeholder="请输入内容" ></u--textarea>
        </u-form-item>
		<u-form-item
		    label="邀请组队"
		    borderBottom
		    ref="item"
		    labelWidth="80"
		>
			<u-switch v-model="isAGroup" @change="change"></u-switch>
		</u-form-item>
		<u-form-item v-if="isAGroup" label="组队" prop="group.remark" borderBottom ref="item" labelWidth="80" @click="showGroup = true; hideKeyboard()">
			<u--input
				v-model="model.group.remark"
				disabled
				disabledColor="#ffffff"
				placeholder="请选择组队群聊"
				border="none"></u--input>
				<u-icon
						slot="right"
						name="arrow-right"
				></u-icon>
			  <!-- <u-picker :show="show" :columns="columns" keyName="label"></u-picker> -->
		</u-form-item>
      </u--form>
	  <view v-if="isAGroup&& groupName!==''">
		  <view style="display: flex;flex-direction: row;justify-content: left;margin-top: 30rpx;">
			  <view style="">
				  	<image :src="currentGroup.avatar?currentGroup.avatar:'/static/img/no-avatar.png'"
				  		mode="aspectFill" style="width: 60px;height: 60px;"></image>
			  </view>
			  <view style="padding-left: 30rpx;">
				  <view>{{currentGroup.name}} 期待着您的加入...</view>
				  <view style="color: #aaa;">{{currentGroup.intro}}</view>
			  </view>
		  </view>
	  </view>
	  <u-action-sheet
				:show="showType"
				:actions="categoryList"
				title="请选择游戏分区"
				description="将会发布到指定的游戏分区"
				@close="showType = false"
				@select="typeSelect"
		>
	  </u-action-sheet>
	  <u-action-sheet
	  				:show="showGroup"
	  				:actions="groupList"
	  				title="请选择组队群聊"
	  				description="生成邀请链接加入组队群聊"
	  				@close="showGroup = false"
	  				@select="groupSelect"
	  		>
	  </u-action-sheet>
      <u-button
          type="primary"
          text="提交"
          customStyle="margin-top: 50px"
          @click="submit"
      ></u-button>
    </view>

  </view>
</template>

<script>
import uParse from '@/components/gaoyia-parse/parse.vue' 
import Editor from '@/components/Editor/index.vue'
import * as UploadApi from '@/api/upload.js'
import * as ZoneApi from '@/api/zone/index.js'
export default {
  components: {
    uParse,
	Editor
  },
  data() {
    return {
      model: {
        group:{
          name: '',
          notice: '',
          intro: '',
          avatar: ''
        },
      },
	  isAGroup:false,
	  article: '<p>html代码，具体参见https://github.com/gaoyia/parse/tree/1.0.7/parse-demo中的demo</p>',
	  showType:false,categoryList:[],
	  showGroup:false,groupList:[],
      // 群头像List
      avatarList:[] ,flagA:false,avatar:'',tmpName:'',groupName:'',currentGroup:{},
      rules: {
        'group.name': {
          type: 'string',
          required: true,
          message: '请填写话题',
          trigger: ['blur', 'change']
        },
        'group.notice': {
          type: 'string',
          required: true,
          message: '请填写内容',
          trigger: ['blur', 'change']
        },
        'group.typeId': {
          type: 'string',
          required: true,
          message: '请选择游戏类型',
          trigger: ['blur', 'change']
        },
        'group.remark': {
          type: 'string',
          required: true,
          message: '请选择组队群聊',
          trigger: ['blur', 'change']
        }
      },
    };
  },
  onLoad(options) {
    const app = this;
    app.getCategoryList();
    app.groupPage();
  },
  onShow() {
  },
  methods: {
	typeSelect(e) {
	  	this.model.group.typeId = e.name
	  	this.model.group.tmpName = e.id
	  	this.$refs.uForm.validateField('group.typeId')
	},
	getCategoryList() {
	  const app = this;
	  ZoneApi.cateList()
	    .then(result => {
	        app.categoryList = result.data;
	    })
	},
	groupPage() {
	  // 不分页查询
	  let params = {
	    current: -1,
	    size: -1,
	  }
	  this.$api.groupPage(params).then(res => {
	    if (res.code) {
	      this.groupList = res.data.records;
	    }
	  })
	},
	async groupSelect(e) {
	  	this.groupName = e.id
	  	this.model.group.remark = e.name
		const result = await this.$api.groupDetail(e.id)
		if(!result.code){
		  this.$tips.error(result.message)
		  return
		}
		const group = result.data
		this.currentGroup = group
	  	this.$refs.uForm.validateField('group.typeId')
	},
    // 提交
    submit() {
      // 如果有错误，会在catch中返回报错信息数组，校验通过则在then中返回true
      this.$refs.form.validate().then(async res => {
        // 调取接口进行数据更新
		const tmp = {
			zoneTitle:this.model.group.name,
			zoneContent:this.model.group.notice,
			zoneTypeId:this.model.group.tmpName
		}
		if(this.isAGroup){
			tmp.remark = this.groupName
		}
		ZoneApi.save(tmp).then(res => {
			if(res.code){
				this.$tips.success("操作成功")
				this.$Router.push({path: '/pages/posts/posts'})
			}
		})
        // const result = await this.$api.groupAdd(this.model.group)
        // if (result.code) {
        //   this.$tips.success("操作成功")
        //   this.$Router.push({path: '/pages/posts/posts'})
        // }
      })
    },
	// 选择图片
	chooseImage() {
		const app = this
		app.flagA = false
		uni.chooseImage({
			count: 1,
			sizeType: ['original', 'compressed'],
			sourceType: ['album', 'camera'], 
			success({tempFiles}) {
				const imageList = tempFiles;
				return new Promise((resolve, reject) => {
					if (imageList.length > 0) {
						UploadApi.image(imageList)
							.then(files => {
								if (files && files.length > 0) {
									app.avatar = files[0].domain + files[0].fileName;
									app.model.group.avatar = app.avatar;
									app.flagA = true
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
	preview(src, e) {  //事件：点击富文本里的图片
	      // do something
	    },
	navigate(href, e) {  //事件：点击富文本里的链接
	  // do something
	  let a=encodeURI(href)  //为兼容iOS端，特别长的url需做转码处理
	  //打开外部url
	  if(a){
	      plus.runtime.openURL(a, res=>{
	          uni.showToast({title: '打开当前地址出错啦，请稍后再试',icon:'none'});
	      }) 
	    }
	}
  }
};
</script>

<style lang="scss">
.container {
  padding: 40upx;
  background: #fff;
}
</style>
