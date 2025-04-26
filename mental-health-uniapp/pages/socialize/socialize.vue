<template>
	<view>
		<!-- tab栏 -->
<!-- 		<view style="display: flex;justify-content: center;flex-wrap: wrap;justify-content: flex-start;">
			<view v-for="(item,index) in tabs" :key="index" @click="selectW(index,item)"
							:style="{'background-color': isSelected(index) ? '#1acc1a' : '#b1b1b1'}">
				<text>{{item.name}}</text>
			</view>
		</view> -->
<!-- 		<u-tabs :list="tabs" :is-scroll="false" :current="curTab" active-color="#FA2209" :duration="0.2"
			@change="onChangeTab" /> -->
		<friend v-if="curTab === 0" />
		<!-- <s-group v-if="curTab === 1" /> -->
	</view>
</template>

<script>
	import Friend from '@/pages/friend/friend.vue'
	import SGroup from '@/pages/group/group.vue'
	// 每页记录数量
	const pageSize = 15
	// tab栏数据
	export default {
		components: {
			Friend,
			SGroup
		},
		data() {
			return {
				curTab: 0,
				tabs : [{
					name: '好友',
					value: 'friend'
				}, {
					name: '群聊',
					value: 'group'
				}],
				selectedIndex:0,selectedW:null
			};
		},
		onLoad(options) {
			// empty
		},
		onShow() {

		},
		methods: {
			// 切换标签项
			onChangeTab(index) {
				const app = this
				// 设置当前选中的标签
				app.curTab = index
				// 刷新列表
				// app.onRefreshList()
			},
			isSelected(index) {
				// 检查当前项是否被选中
				return index === this.selectedIndex;
			},
			selectW(index, item) {
				// 点击时更新选中的项
				this.selectedIndex = index;
				this.selectedW = item;
			},
		}
	}
</script>

<style lang="scss">

</style>