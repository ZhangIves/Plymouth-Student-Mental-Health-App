<script>
	export default {
		data() {
			return {
				formData: {
					gender: '',
					socialMedia: [],
					suggestion: ''
				},
				genderOptions: [{
						label: '男',
						value: 'male'
					},
					{
						label: '女',
						value: 'female'
					},
					{
						label: '其他',
						value: 'other'
					}
				],
				socialMediaOptions: [{
						label: '微信',
						value: 'wechat'
					},
					{
						label: '微博',
						value: 'weibo'
					},
					{
						label: '抖音',
						value: 'tiktok'
					},
					{
						label: 'B站',
						value: 'bilibili'
					}
				],
				survey: {},
				answers: {}
			}
		},
		onLoad(options) {
			this.loadSurvey(options.id);
		},
		methods: {
			async loadSurvey(id) {
				const res = await uni.request({
					url: `http://your-api-url/api/surveys/${id}`
				});
				this.survey = res.data;
			},

			async submitAnswers() {
				const answerList = Object.keys(this.answers).map(questionId => ({
					questionId: parseInt(questionId),
					content: this.answers[questionId]
				}));

				const res = await uni.request({
					url: 'http://your-api-url/api/surveys/submit',
					method: 'POST',
					data: answerList
				});

				uni.showToast({
					title: '提交成功'
				});
			},
			submitForm() {
				// 这里可以添加表单验证逻辑
				console.log('提交数据:', this.formData);

				// 发送到后端（示例）
				uni.request({
					url: 'https://your-api-endpoint.com/submit-survey',
					method: 'POST',
					data: this.formData,
					success: (res) => {
						uni.showToast({
							title: '提交成功',
							icon: 'success'
						});
					},
					fail: (err) => {
						uni.showToast({
							title: '提交失败',
							icon: 'none'
						});
					}
				});
			}
		}
	}
</script>

<style>

</style>