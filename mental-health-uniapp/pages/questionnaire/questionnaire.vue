<template>
  <view class="container">
	<navbar type="second" back="false" title="Mental Health Test"></navbar>
    <view v-if="questionnaire">
      <view class="header">
        <h1>{{ questionnaire.title }}</h1>
        <p>{{ questionnaire.description }}</p>
      </view>
      
      <view v-for="(q, index) in questionnaire.questions" :key="q.id" class="question">
        <view class="question-content">
          <text>{{ index + 1 }}. {{ q.content }}</text>
          <view v-if="q.type === 'SINGLE'" class="options">
            <radio-group @change="(e) => handleRadioChange(q.id, e.detail.value,q.options)">
              <label v-for="opt in q.options" :key="opt.id" class="option">
                <radio :value="opt.id" :checked="answers[q.id].selectedOptions.includes(opt.id)" />
                {{ opt.content }}
              </label>
            </radio-group>
          </view>
          <view v-else-if="q.type === 'MULTIPLE'" class="options">
            <checkbox-group @change="(e) => handleCheckboxChange(q.id, e.detail.value)">
              <label v-for="opt in q.options" :key="opt.id" class="option">
                <checkbox :value="opt.id" :checked="answers[q.id].selectedOptions.includes(opt.id)" />
                {{ opt.content }}
              </label>
            </checkbox-group>
          </view>
          <view v-else class="text-input">
            <input type="text" v-model="answers[q.id].answerText" placeholder="Please enter the answer" />
          </view>
        </view>
      </view>
      
      <button @click="submit" class="submit-btn">Submit</button>
    </view>
  </view>
</template>

<script>
	import {
	  mapActions,
	  mapGetters
	} from "vuex"
export default {
  data() {
    return {
      questionnaire: null,
      answers: {}
    };
  },
  onLoad(options) {
    this.loadQuestionnaire(options.id);
  },
  computed: {
    ...mapGetters(['userInfo','token'])
  },
  methods: {
    async loadQuestionnaire(id) {
      const res = await uni.request({
        url: `http://localhost:8091/clientApi/questionnaire/${id}`
      });
	  console.log("res",res[1])
      if (res[1].data.code === 200) {
        this.questionnaire = res[1].data.data;
		console.log("hhh",this.questionnaire)
        this.initAnswers();
      }
    },
    initAnswers() {
      this.questionnaire.questions.forEach(q => {
        this.$set(this.answers, q.id, {
          type: q.type,
          selectedOptions: [],
          answerText: '',
		  score:0
        });
      });
    },
    handleRadioChange(qid, value,object) {
		console.log(object)
      this.answers[qid].selectedOptions = [value];
	  if(value!==null){
		  const namesOfItemsWithTargetScore = object.filter(item => item.id === value);
		   
		  this.answers[qid].score = namesOfItemsWithTargetScore[0].score
	  }
    },
    handleCheckboxChange(qid, values) {
      this.answers[qid].selectedOptions = values;
    },
    async submit() {
		console.log("token",this.token)
      const userId = this.token; // 实际项目中应获取用户标识
      const answerDTOs = Object.keys(this.answers).map(qid => ({
        questionId: Number(qid),
        type: this.answers[qid].type,
        selectedOptions: this.answers[qid].selectedOptions,
		score:this.answers[qid].score,
        answerText: this.answers[qid].answerText
      }));
      
      const res = await uni.request({
        url: 'http://localhost:8091/clientApi/answer/submit',
        method: 'POST',
        data: {
          userId: userId,
          questionnaireId: this.questionnaire.id,
          answers: answerDTOs
        }
      });
      console.log("res",res[1])
	  if (res[1].data.code === 200) {
        uni.showToast({ title: '提交成功' });
        setTimeout(() => uni.navigateBack(), 1500);
      } else {
        uni.showToast({ title: res[1].data.message, icon: 'error' });
      }
    }
  }
};
</script>

<style>
.container { padding: 20px; }
.header { margin-bottom: 20px; }
.question { margin-bottom: 15px; }
.options { margin-top: 10px; }
.option { display: block; margin: 5px 0; }
.text-input input { border: 1px solid #ccc; padding: 8px; width: 100%; }
.submit-btn { margin-top: 20px; background: #007AFF; color: white; }
</style>
