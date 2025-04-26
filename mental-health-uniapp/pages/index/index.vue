<template>
	<view class="home-container">
		<view style="" class="home-box">
			<view style="text-align: center;padding: 20rpx;margin-bottom: 100rpx;margin-top: 60rpx;">
				<text style="font-size: 40rpx;font-weight: 700;">Plymouth Student Mental Health</text>
			</view>
			
			<view style="display: flex;justify-content: center;">
				<view style="background-color: #55aaff; padding: 30rpx;text-align: center;border-radius: 10rpx;width: 70%;
				"
				@click="toTest()">
					<view style="font-size: 36rpx;font-weight: 700;">Mental Health Test</view>
				</view>
			</view>
			<view style="display: flex;justify-content: center;margin-top: 60rpx;">
				<view class="lonng-item-home" @click="toResources()">
					<view style="font-size: 36rpx;font-weight: 700;">Mental Health Resources</view>
				</view>
			</view>
			
			<view style="display: flex;justify-content: center;margin-top: 60rpx;">
				<view style="background-color: #55aaff; padding: 30rpx;text-align: center;border-radius: 10rpx;width: 70%;"
				@click="toHistory()">
					<view style="font-size: 36rpx;font-weight: 700;">Test History</view>
				</view>
			</view>
			
			<view style="display: flex;justify-content: center;margin-top: 60rpx;">
				<view style="background-color: #55aaff; padding: 30rpx;text-align: center;border-radius: 10rpx;width: 70%;" @click="toMine">
					<view style="font-size: 36rpx;font-weight: 700;">My Account</view>
				</view>
			</view>
		</view>
<!-- 		<block>
			<Banner :itemStyle="options.bannerStyle" :params="options.bannerParam" :dataList="banner" />
		</block> -->
		<view class="loong-box-title" style="display: flex;justify-content: space-between;" v-if="false">
			<view class="loong-box-title-item">
				公告
			</view>
			<view class="loong-text-bottom" @click="toListIndex">
				<view style="color: #aaa; font-size: 32rpx;line-height: 32rpx;padding-bottom:5rpx;">
					更多>>
				</view>
			</view>
		</view>
		<!-- 列表 -->
		<view class="article-list" style="padding: 10rpx;" v-if="false">
			<view class="article-item show-type" v-for="(item, index) in articleList" :key="index"
				@click="onTargetDetail(item.id)">
				<block>
					<view class="article-item-left flex-box">
						<view class="article-item-title twolist-hidden">
							<text>{{ item.title }}</text>
						</view>
						<view class="article-item-footer m-top10">
							<text class="article-views f-24 col-8" style="color: #aaa;">{{ item.createTime }}</text>
						</view>
					</view>
				</block>
			</view>
		</view>
	</view>
</template>

<script>
	import Banner from '@/components/banner'
	import * as ArticleApi from '@/api/article.js'
	import * as Api from '@/api/banner.js'
	const App = getApp()

	export default {
		components: {
			Banner,
		},
		data() {
			return {
				options:{
					"bannerStyle": {
						"btnColor": "#ffffff",
						"btnShape": "round",
						"interval": 2.5,
					},
				},
				menuList: [
					{
						name:"Mental Health Test",
						path:"pages/questionnaire/questionnaire",
					},
					{
						name:"Mental Health Resources",
						path:"pages/article/index",
					},
					{
						name:"Test History",
						path:"pages/questionnaire/questionnaire",
					},
					{
						name:"My Account",
						path:"pages/mine/mine",
					},
				],
				currentName:'',
				banner: [],
				imagePath: 'http://localhost:8066/',
				articleList: [],
				storeInfo: null,
				isReflash: false,
				isLoading: false
			}
		},

		/**
		 * 生命周期函数--监听页面加载
		 */
		onLoad() {
			this.getPageData()
		},
		/**
		 * 生命周期函数--监听页面显示
		 */
		onShow() {
			const app = this;
			// setCartTabBadge();
			app.getPageData()
			uni.getLocation({
				type: 'gcj02',
				success(res) {
					uni.setStorageSync('latitude', res.latitude);
					uni.setStorageSync('longitude', res.longitude);
				},
				fail(e) {}
			})

		},

		methods: {
			// 跳转文章详情页
			showDetails(articleId) {
				this.$navTo('pages/zoneDetail/zoneDetail', {
					articleId
				});
			},
			toTest(){
				this.$navTo('pages/questionnaire/questionnaire',{id:4})
			},
			toListIndex(){
				this.$navTo('pages/article/index',1)
			},
			toMine(){
				uni.switchTab({
					url:'/pages/mine/mine'
				})
			},
			toResources(){
				this.$navTo("pages/posts/posts")
			},
			toHistory(){
				this.$navTo("pages/evaluation/evaluation")
			},
			/**
			 * 加载页面数据
			 * @param {Object} callback
			 */
			getPageData(callback) {
				const app = this;
				Api.banner()
					.then(result => {
						app.banner = result.data.content;
					})
					.finally(() => callback && callback())
				ArticleApi.page({
					page: 1,
					pageSize: 5
				}).then(result => {
					app.articleList = result.data.content
				}).finally(() => callback && callback())
			},

			// 跳转文章详情页
			onTargetDetail(articleId) {
				this.$navTo('pages/article/detail', {
					articleId
				});
			},

			/**
			 * 下拉刷新
			 */
			onPullDownRefresh() {
				// 获取数据
				this.getPageData(() => {
					uni.stopPullDownRefresh()
				})
			},
			onGetStoreInfo() {
				const app = this;
				settingApi.systemConfig()
					.then(result => {
						app.storeInfo = result.data.storeInfo;
						if (app.storeInfo) {
							uni.setStorageSync("storeId", app.storeInfo.id);
							uni.setStorageSync("merchantNo", app.storeInfo.merchantNo);
							let isReflash = uni.getStorageSync("reflashHomeData");
							app.isReflash = isReflash;
							if (isReflash === true) {
								app.getPageData();
							}
						}
					})
			}
		},

		/**
		 * 分享当前页面
		 */
		onShareAppMessage() {
			const app = this
			return {
				title: "物业",
				path: "/pages/index/index?" + app.$getShareUrlParams()
			}
		},

		onShareTimeline() {
			const app = this
			const {
				page
			} = app
			return {
				title: page.params.share_title,
				path: "/pages/index/index?" + app.$getShareUrlParams()
			}
		}

	}
</script>

<style lang="scss">
	.home-container{
		width: 100%;
		height: 90vh;
		// background-color: #2979ff;
		padding: 30rxp 16rpx;
		margin-top: 30rpx;
		display: flex;
		justify-content: center;
	}
	.home-box{
		margin-top: 30rpx;
		border: #aaa solid 2rpx;
		box-shadow: 
		10rpx 10rpx 10rpx #aaa,
		10rpx -10rpx 10rpx #aaa,
		-10rpx 10rpx 10rpx #aaa,
		-10rpx -10rpx 10rpx #aaa;
		width: 90%;border-radius: 30rpx;
	}
	.article-item:hover {
		color: blue
	}

	.loong-box-title {
		// padding: 16rpx 26rpx 0rpx;
		margin: 20rpx 26rpx 6rpx;
		border-bottom: 5rpx solid #2979ff;
		font-size: 18px;
		margin-bottom: 18px;

		.loong-box-title-item {
			border-top-left-radius: 30rpx 40rpx;
			border-top-right-radius: 30rpx 40rpx;
			display: block;
			width: 120px;
			height: 34px;
			line-height: 34px;
			text-align: center;
			background: #2979ff;
			color: #fff;
		}
	}

	.news-list-item {
		display: flex;
		align-items: center;
		padding: 10px;
		border-bottom: 1px solid #eee;
	}

	.news-item-container {
		display: flex;
		flex-direction: row;
		align-items: flex-start;
	}

	.news-image {
		width: 100rpx;
		// height: 100px;
		object-fit: cover;
		margin-right: 10px;
	}

	.news-item-image {
		width: 50%;
		// height: 33%;
		// height: 0;
		padding-bottom: 66.66%;
		/* 1.5:1 Aspect Ratio */
		background-size: cover;
		background-position: center;
	}

	.avatar-uploader {
		width: 200px;
		height: 160px;
		border-radius: 16rpx;
		border: 1px solid #ccc;
		text-align: center;
		line-height: 160px;
		font-size: 46px;
	}

	.news-item-content {
		padding-left: 10px;
		flex: 1;
	}

	.news-item-title {
		font-weight: bold;
		margin-bottom: 5px;
	}

	.news-item-description {
		display: -webkit-box;
		-webkit-line-clamp: 5;
		-webkit-box-orient: vertical;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: normal;
		margin-bottom: 5px;
	}

	.news-item-status {
		position: absolute;
		top: 0;
		right: 0;
		padding: 2px 5px;
		background-color: #f00;
		color: #fff;
		border-radius: 3px;
	}

	.status-text {
		font-size: 10px;
	}

	.article-list {
		padding-top: 20rpx;
		line-height: 1;
		background: #f7f7f7;
	}

	.article-item {
		margin-bottom: 20rpx;
		padding: 30rpx;
		background: #fff;

		&:last-child {
			margin-bottom: 0;
		}

		.article-item-title {
			max-height: 80rpx;
			font-size: 32rpx;
			line-height: 60rpx;
			color: #333;
		}

		.article-item-image .image {
			display: block;
			border-radius: 8rpx;
			height: 140rpx;
			width: 180rpx;
			border: 2rpx solid #cccccc;
		}
	}
	.loong-text-bottom {
	    display: flex;
	    flex-direction: column;
	    justify-content: flex-end;
	    align-items: center;
	    // height: 200px; /* 您可以根据需要设置这个高度 */
	    // border: 1px solid black; /* 仅用于示例，显示容器边界 */
	}
	.lonng-item-home {
		background-color: #55aaff; 
		padding: 30rpx;
		text-align: center;
		border-radius: 10rpx;
		width: 70%;
	}
	.loong-item-home-box-shadow{
		box-shadow: 0 0 10rpx 10rpx rgba(85, 170, 255, 0.3);
	}
</style>