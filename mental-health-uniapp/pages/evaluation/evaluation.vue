<template>
  <mescroll-body ref="mescrollRef" :sticky="true" @init="mescrollInit" :down="{ use: false }" :up="upOption" @up="upCallback">
	<navbar type="second" :title="title"></navbar>
    <!-- 分类列表tab -->
    <view class="tabs-wrapper" v-if="false">
      <scroll-view class="scroll-view" scroll-x>
        <view class="tab-item" :class="{ active: curId ==  0 }" @click="onSwitchTab(0)">
          <view class="value"><text>全部公告</text></view>
        </view>
        <!-- 分类列表 -->
        <view class="tab-item" :class="{ active: curId ==  item.categoryId }" @click="onSwitchTab(item.id)"
          v-for="(item, index) in categoryList" :key="index">
          <view class="value"><text>{{ item.name }}</text></view>
        </view>
      </scroll-view>
    </view>

    <!-- 列表 -->
    <view class="article-list">
      <view class="article-item show-type" v-for="(item, index) in list.content" :key="index">
        <block>
          <view class="article-item-left flex-box">
            <view class="article-item-title twolist-hidden">
              <text>{{ item.resultDescription }}</text>
            </view>
				 <view>{{item.dailyAdvice}}</view>
				 <view style="width: 100%;border-bottom: 2rpx solid #888888;padding-top: 36rpx;"></view>
            <view class="article-item-footer m-top10" style="padding-top: 10rpx;color: #888888;">
              <text class="article-views f-24 col-8">{{ item.createTime }}</text> 
            </view>
		
          </view>
          <!-- <view class="article-item-image"> -->
			  <!-- <view>{{item.dailyAdvice}}</view> -->
            <!-- <image class="image" v-if="wxCheckImg(item.content) !== null" :src="wxCheckImg(item.content)"></image> -->
			<!-- <image class="image"  :src="wxCheckImg(item.content)"></image> -->
          <!-- </view> -->
        </block>
      </view>
    </view>
  </mescroll-body>
</template>

<script>
  import MescrollBody from '@/components/mescroll-uni/mescroll-body.vue'
  import MescrollMixin from '@/components/mescroll-uni/mescroll-mixins'
  import * as Api from '@/api/evaluation.js'
  import { getEmptyPaginateObj, getMoreListData } from '@/utils/app'

  const pageSize = 15
  export default {
    components: {
      MescrollBody
    },
    mixins: [MescrollMixin],
    data() {
      return {
        // 分类列表
		title:"Mental Evaluation",
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
      // 获取文章分类数据
      // app.getCategoryList();
    },
	onShow() {
		this.upCallback(1)
	},

    methods: {
      /**
       * 上拉加载的回调 (页面初始化时也会执行一次)
       * 其中page.num:当前页 从1开始, page.size:每页数据条数,默认10
       * @param {Object} page
       */
      upCallback(page) {
        const app = this;
        // 设置列表数据
        app.getArticleList(page.num)
          .then(list => {
			  const curPageLen = list.content.length;
			  const totalSize = list.totalElements;
			  app.mescroll.endBySize(curPageLen, totalSize);
            // const curPageLen = list.current;
            // const totalSize = list.total;
            // app.mescroll.endBySize(curPageLen, totalSize);
          })
          .catch(() => app.mescroll.endErr());
      },

      // 获取文章分类数据
      getCategoryList() {
        // const app = this;
        // ArticleApi.cateList()
        //   .then(result => {
        //       app.categoryList = result.data.list;
        //   })
      },

      /**
       * 获取文章列表
       * @param {Number} pageNo 页码
       */
      getArticleList(pageNo = 1) {
        const app = this
        return new Promise((resolve, reject) => {
          Api.page({ page: pageNo,pageSize:pageSize }, { load: false })
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
		  // 遍历img元素，提取src属性
		  let imageSrcs = Array.from(imgElements).map(img => img.getAttribute('src'));
		  // console.log(imageSrcs);
		  return imageSrcs[0];
	  },
	  wxCheckImg(htmlString){
		
		// 正则表达式，匹配img标签的src属性
		let imgRegex = /<img[^>]*src="([^">]+)">/i;
		
		// 使用match方法获取第一个匹配项
		let match = imgRegex.exec(htmlString);
		
		// 如果找到了匹配项，match[1]就是src属性的值
		if (match) {
		  let firstImageSrc = match[1];
		  console.log(firstImageSrc); // 输出: "http://localhost:9755/uploadImages/20240406104652975.png"
		  return firstImageSrc;
		  // 在微信小程序中，你可以直接使用这个URL来加载图片
		  // 例如，在Image组件中设置src属性
		  // wx.setData({
		  //   imageSrc: firstImageSrc
		  // });
		} else {
		  console.log('No image found in the HTML string.');
		  return null
		}  
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

      // 跳转文章详情页
      onTargetDetail(articleId) {
        this.$navTo('pages/article/detail', { articleId });
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
      width: 60%;
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
</style>
