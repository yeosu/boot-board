<template>
  <div class="board-detail">
    <div class="common-buttons">
      <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSave">저장</button>&nbsp;
      <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
    </div>
    <div class="board-contents">
      <input type="text" v-model="title" class="w3-input w3-border" placeholder="제목을 입력해주세요.">
      <input type="text" v-model="reg_user" class="w3-input w3-border" placeholder="작성자를 입력해주세요." v-if="id === undefined">
    </div>
    <div class="board-contents">
      <textarea id="" cols="30" rows="10" v-model="content" class="w3-input w3-border" style="resize: none;">
      </textarea>
    </div>
    <div class="common-buttons">
      <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSave">저장</button>&nbsp;
      <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      requestBody: this.$route.query,
      id: this.$route.query.id,
      title: '',
      reg_user: '',
      content: '',
      reg_dt: ''
    }
  },
  mounted() {
    this.fnGetView()
  },
  methods: {
    fnGetView() {
      if(this.id !== undefined) {
        this.$axios.get(this.$serverUrl + '/board/' + this.id, {
          params: this.requestBody
        }).then((res) => {
          this.title = res.data.title
          this.reg_user = res.data.reg_user
          this.content = res.data.content
          this.reg_dt = res.data.reg_dt
        }).catch((err) => {
          console.log(err)
        })
      }
    },
    fnList() {
      delete this.requestBody.id
      this.$router.push({
        path: './list',
        query: this.requestBody
      })
    },
    fnView(id) {
      this.requestBody.id = id
      this.$router.push({
        path: './detail',
        query: this.requestBody
      })
    },
    fnSave() {
      let apiUrl = this.$serverUrl + '/board'
      this.form = {
        "id": this.id,
        "title": this.title,
        "content": this.content
      }

      if(this.id === undefined) {
        //id가 없으면 insert
        this.$axios.post(apiUrl, this.form)
        .then((res) => {
          alert('저장되었습니다.')
          this.fnView(res.data.id)
        }).catch((err) => {
          if(err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다. \n 잠시 후 다시 시도해주세요.')
          }
        })
      }else{
        //id가 있으면 update
        this.$axios.patch(apiUrl, this.form)
        .then((res) => {
          alert('저장되었습니다.')
          this.fnView(res.data.id)
        }).catch((err) => {
          if(err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다. \n 잠시 후 다시 시도해주세요.')
          }
        })
      }
    }
  }
}
</script>