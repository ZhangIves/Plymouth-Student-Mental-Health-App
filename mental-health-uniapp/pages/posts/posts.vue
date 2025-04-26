<template>
  <mescroll-body ref="mescrollRef" :sticky="true" @init="mescrollInit" :down="{ use: false }" :up="upOption" @up="upCallback">
    <navbar type="second" title="Mental Health Resources"></navbar>
	<!-- 分类列表tab -->
    <view class="tabs-wrapper">
      <scroll-view class="scroll-view" scroll-x>
       <view class="tab-item" :class="{ active: curId ==  0 }" @click="onSwitchTab(0)">
          <view class="value"><text>All</text></view>
        </view>
        <!-- 分类列表 -->
        <view class="tab-item" :class="{ active: curId ==  item.id }" @click="onSwitchTab(item.id)"
          v-for="(item, index) in categoryList" :key="index">
          <view class="value"><text>{{ item.name }}</text></view>
        </view>
      </scroll-view>
    </view>
    <!-- 列表 -->
    <view class="article-list">
      <view class="article-item show-type" v-for="(item, index) in list.content" :key="index" @click="onTargetDetail(item.id)">
        <block>
          <view class="article-item-left flex-box">
            <view class="article-item-title twolist-hidden">
              <text>{{ item.zoneTitle }}</text>
            </view>
            <view class="article-item-footer m-top10">
              <text class="article-views f-24 col-8" style="color: #aaa;">{{ item.createTime }}</text>
            </view>
          </view>
        </block>
      </view>
    </view>
	<view style="width: 130rpx;" class="floating-button" @click="onRe" v-if="false&&token">
		发布
	</view>
  </mescroll-body>
</template>

<script>
  import MescrollBody from '@/components/mescroll-uni/mescroll-body.vue'
  import MescrollMixin from '@/components/mescroll-uni/mescroll-mixins'
  import * as ZoneApi from '@/api/zone'
  import { getEmptyPaginateObj, getMoreListData } from '@/utils/app'
  import {mapGetters} from "vuex"
  const pageSize = 15
  export default {
    components: {
      MescrollBody
    },
    mixins: [MescrollMixin],
    data() {
      return {
        // 分类列表
        categoryList: [],
        // 文章列表
        list: getEmptyPaginateObj(),
        // 当前选中的分类id (0则代表首页)
        curId: 0,
        // 上拉加载配置
        upOption: {
          // 首次自动执行
          auto: true,
          // 每页数据的数量; 默认10
          page: { size: pageSize },
          // 数量要大于3条才显示无更多数据
          noMoreSize: 3,
        }
      }
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
      const app = this;
      if (options.categoryId) {
          app.curId = options.categoryId;
      }
      // 获取分类数据
      app.getCategoryList();
    },
	onShow() {
		this.upCallback(1)
	},
    computed: {
      ...mapGetters(['token'])
    },
    methods: {
      /**
       * 上拉加载的回调 (页面初始化时也会执行一次)
       * 其中page.num:当前页 从1开始, page.size:每页数据条数,默认10
       * @param {Object} page
       */
      upCallback(page) {
        const app = this;
		app.getCategoryList();
        // 设置列表数据
        app.getArticleList(page.num)
          .then(list => {
			  const curPageLen = list.content.length;
			  const totalSize = list.totalElements;
			  app.mescroll.endBySize(curPageLen, totalSize);
          })
          .catch(() => app.mescroll.endErr());
      },
	  onRe(){
		  uni.navigateTo({
		  	url:'/pages/posts/re'
		  })
	  },
	  // 跳转群组信息
	  toInfo(group){
	    this.$Router.push({ path: '/pages/group/info', query: {groupId:group.id}})
	  },
      // 获取分类数据
      getCategoryList() {
        const app = this;
        ZoneApi.cateList()
          .then(result => {
              app.categoryList = result.data;
          })
      },

      /**
       * 获取文章列表
       * @param {Number} pageNo 页码
       */
      getArticleList(pageNo = 1) {
        const app = this
        return new Promise((resolve, reject) => {
          ZoneApi.page({ page: pageNo,pageSize:pageSize ,zoneTypeId:app.curId}, { load: false })
            .then(result => {
              // 合并新数据
              const newList = result.data;
              app.list.content = getMoreListData(newList, app.list, pageNo);
              resolve(newList)
            })
            .catch(result => reject());
        })
      },
	  checkImg(htmlString){
		  // let htmlString = '<p><img src="http://localhost:9755/uploadImages/20240406104652975.png">撒旦<strong>大圣带<em>大</em></strong><strong class="ql-size-huge"><em>啊大苏打</em></strong></p>';
		  // 创建一个DOMParser实例
		  let parser = new DOMParser();
		  // 使用DOMParser解析HTML字符串
		  let doc = parser.parseFromString(htmlString, "text/html");
		  // 获取所有的img元素
		  let imgElements = doc.getElementsByTagName('img');
		  if (imgElements.length === 0) {
		      console.log('没有找到图片。');
		      return false;
		  }
		  // 遍历img元素，提取src属性
		  let imageSrcs = Array.from(imgElements).map(img => img.getAttribute('src'));
		  return imageSrcs[0];
	  },
      // 切换选择的分类
      onSwitchTab(categoryId = 0) {
        const app = this;
        // 切换当前的分类ID
        app.curId = categoryId;
        // 刷新列表数据
        app.list = getEmptyPaginateObj();
        app.mescroll.resetUpScroll();
      },

      // 跳转详情页
      onTargetDetail(id) {
        this.$navTo('pages/posts/detail', {id});
      }
    },

    /**
     * 分享当前页面
     */
    onShareAppMessage() {
      return {
        title: '文章首页',
        path: "/pages/article/index?" + this.$getShareUrlParams()
      }
    },

  }
</script>

<style lang="scss" scoped>
  /* 顶部选项卡 */

  .container {
    min-height: 100vh;
  }

  .tabs-wrapper {
    position: sticky;
    top: var(--window-top);
    display: flex;
    width: 100%;
    height: 88rpx;
    color: #333;
    font-size: 28rpx;
    background: #fff;
    border-bottom: 1rpx solid #e4e4e4;
    z-index: 100;
    overflow: hidden;
    white-space: nowrap;
  }

  .tab-item {
    display: inline-block;
    padding: 0 15rpx;
    text-align: center;
    min-width: 20%;
    height: 87rpx;
    line-height: 88rpx;
    box-sizing: border-box;

    .value {
      height: 100%;
    }

    &.active .value {
      color: #0055ff;
      border-bottom: 4rpx solid #0055ff;
    }
  }

  /* 文章列表 */
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

  .show-type {
    display: flex;
    .article-item-left {
      padding-right: 20rpx;
    }
    .article-item-title {
      min-height: 72rpx;
    }
  }
  
  .floating-button {
      position: fixed;
      bottom: 160rpx; /* 距离页面底部的距离 */
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
</style>
