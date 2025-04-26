<template>
  <view v-if="!isLoading" class="container b-f p-b">
	<navbar :isBack="true" type="second" :title="detail.zoneTitle"></navbar>
    <view class="article-title">
      <text>{{ detail.zoneTitle }}</text>
    </view>
    <view class="article-little dis-flex flex-x-between m-top10">
      <view class="article-little-left">
        <!-- <text class="article-views f-24 col-8">{{ detail.counts }}次浏览</text> -->
      </view>
      <view class="article-little-right">
        <text class="article-time f-24 col-8">{{ detail.createTime | timeFormat('yyyy-mm-dd hh:MM') }}</text>
      </view>
    </view>
    <view class="article-content m-top20" style="margin-top: 20rpx;">
      <jyf-parser :html="detail.zoneContent" class="loong-html"></jyf-parser>
    </view>
	<view class="article-content" style="margin-top: 20rpx;" v-if="false">
		<view v-if="detail.remark" style="background-color: #e7e7e7;padding: 16rpx 56rpx;border-radius: 20rpx;" @click="toInfo">
			<view style="color: #aaa;">请点击加入群聊组队</view>
			<view style="display: flex;flex-direction: row;justify-content: left;margin-top: 30rpx;">
				  <view style="">
					  	<image :src="currentGroup.avatar?currentGroup.avatar:'/static/img/no-avatar.png'"
					  		mode="aspectFill" style="width: 60px;height: 60px;"></image>
				  </view>
				  <view style="padding-left: 30rpx;">
					  <view>{{currentGroup.name}} <text>期待着您的加入...</text></view>
					  <view style="color: #aaa;">{{currentGroup.intro}}</view>
				  </view>
			</view>
		</view>
	</view>
	<view class="loong-box-title" v-if="false">
		<view class="loong-box-title-item">评论</view>
	</view>
	<view v-if="false">
		<view v-for="(item,index) in comments" :key="index">
			<view class="loong-comment-box">
				<!-- 头像+姓名 -->
				<view style="text-align: center; width: 136rpx;">
	<!-- 				<image :src="item.avatar?item.avatar:'/static/img/no-avatar.png'"
					       mode="widthFix" style="width: 50px;height: 50px;"></image> -->
					<!-- <view class="name-box-item">{{item.fullName}}</view> -->
					<u-avatar :src="item.avatar?item.avatar:'/static/img/no-avatar.png'"></u-avatar>
				</view>
				<view style="padding-left: 20rpx;width: 100%;">
					<view class="name-box-item">
						<text style="color: #000;padding-right: 16rpx;">{{item.fullName}}</text>
						<text>{{item.createTime | timeFormat('yyyy-mm-dd hh:MM')}}</text>
					</view>
					<view style="padding-top: 6rpx;width: 600rpx; word-wrap: break-word; word-break: break-all;">{{item.content}}</view>
				</view>
			</view>
		</view>
	</view>
	<view style="width: 130rpx;" class="floating-button" @click="onComment" v-if="false">
		评论
	</view>
    <u-popup :show="show" :round="10" mode="center" @close="show = false" @open="openPopup" class="loong-u-popup-slot">
    	<view style="width: 660rpx;">
           <u--textarea v-model="comContent" placeholder="请输入内容" count ></u--textarea>
		   <view @click="submit">
			   <view style="padding: 16rpx; color: #fff;background-color: #2979ff;text-align: center;">提交</view>
		   </view>
    	</view>
    </u-popup>
	<u-notify ref="uNotify"  message="请输入信息" :show="noContent"></u-notify>
	<!-- 快捷导航 -->
    <!-- <shortcut /> -->
  </view>
</template>

<script>
  import jyfParser from '@/components/jyf-parser/jyf-parser'
  // import Shortcut from '@/components/shortcut'
  import * as ArticleApi from '@/api/article'
  import * as ZoneApi from '@/api/zone'
  export default {
    components: {
      // Shortcut
    },
    data() {
      return {
        zoneId: null,
        // 加载中
        isLoading: true,
        detail: null,
		comments:[],
		show: false,comContent:'',noContent:false,currentGroup:{}
      }
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
      this.zoneId = options.id;
      this.getArticleDetail();
    },
    methods: {
      getArticleDetail() {
        const app = this
        app.isLoading = true
        ZoneApi.detail({zoneId:app.zoneId})
          .then(result => {
			  if(result.code){
				  app.detail = result.data.zone;
				  app.comments = result.data.comments
				  app.getCurrentGroup()
			  }
          })
          .finally(() => app.isLoading = false)
      },
	  onComment(){
		  this.comContent = '';
		  this.show = true
	  },
	  async getCurrentGroup(){
		  const result = await this.$api.groupDetail(this.detail.remark)
		  if(!result.code){
			this.$tips.error(result.message)
			return
		  }
		  const group = result.data
		  this.currentGroup = group
	  },
	  // 跳转群组信息
	  toInfo(group){
	    this.$Router.push({ path: '/pages/group/info', query: {groupId:this.detail.remark}})
	  },
	  openPopup(){
		  
	  },
	  submit(){
		  if(this.comContent === ''){
			this.$refs.uNotify.show({
			    top: 10,
			    type: 'error',
			    color: '#000',
			    bgColor: '#ffff00',
			    message: '请输入信息',
			    duration: 1000 * 1,
			    fontSize: 16,
			    safeAreaInsetTop:true
			})
		  }else{
			  ZoneApi.comment({
				  content:this.comContent,
				  zoneId:this.zoneId
				  }).then(res => {
					  if(res.code){
						  this.$refs.uNotify.show({
						      top: 10,
						      type: 'error',
						      color: '#000',
						      bgColor: '#55aa00',
						      message: '评论成功！',
						      duration: 1000 * 1,
						      fontSize: 16,
						      safeAreaInsetTop:true
						  })
						 this.getArticleDetail();
						 this.show = false	  
					  }
				  })
		  }
	  }
    }
  }
</script>

<style lang="scss" scoped>
  .container {
    min-height: 100vh;
    padding: 30rpx;
    background: #fff;
  }
  .article-title {
     margin-bottom: 20rpx;
     font-size: 35rpx;
	 font-weight: 600;
     display: block;
  }
  .article-content {
	  padding: 6rpx 10rpx;
     font-size: 28rpx;
  }
  .article-time{
	  color: #aaa;
  }
  .loong-comment-box{
	  display: flex;
	  flex-direction: row;
	  justify-content: left;
	  flex-wrap: nowrap;
	  overflow: hidden;
	  background-color: #fff;
	  padding: 16rpx 6rpx;
	  .name-box-item{
		  font-size: 28rpx;
		  color: #aaa;
	  }
  }
  .loong-box-title {
	margin-top: 20rpx;
  	// padding: 16rpx 26rpx 0rpx;
  	// margin: 20rpx 26rpx 6rpx;
  	border-bottom: 5rpx solid #2979ff;
  	font-size: 18px;
  	margin-bottom: 18px;
  
  	.loong-box-title-item {
  		border-top-left-radius: 20rpx 20rpx;
  		border-top-right-radius: 20rpx 20rpx;
  		display: block;
  		width: 120px;
  		height: 34px;
  		line-height: 34px;
  		text-align: center;
  		background: #2979ff;
  		color: #fff;
  	}
  }
  .floating-button {
      position: fixed;
      bottom: 20px; /* 距离页面底部的距离 */
      right: 20px; /* 距离页面右侧的距离 */
      width: 50px; /* 图标宽度 */
      height: 50px; /* 图标高度 */
      background-color: #2979ff; /* 按钮背景颜色 */
      border-radius: 50%; /* 圆形按钮 */
      display: flex;
      justify-content: center; /* 水平居中 */
      align-items: center; /* 垂直居中 */
      box-shadow: 0 0 10px rgba(0,0,0,0.3); /* 阴影效果 */
      cursor: pointer; /* 鼠标悬停时变为手型 */
      transition: all 0.3s ease; /* 过渡效果 */
	  color: #fff;
  }
  .ppp {
  	display: flex;
  	flex-direction: row;
  	justify-content: space-between;
  	flex-wrap: nowrap;
  	overflow: hidden;
  }
  .left {
  	width: 300px;
  	flex-shrink: 0;
  }
  .right {
  	width: 100%;
  }
.loong-u-popup-slot {
		width: 200px;
		height: 150px;
		@include flex;
		justify-content: center;
		align-items: center;
	}
	.loong-html p{
		line-height: 1.5em;
	}
	.loong-html {
		line-height: 2.0em;
		font-size: 32rpx;
	}
</style>
