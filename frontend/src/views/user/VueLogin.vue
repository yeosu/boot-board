<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="login" class="login-form">
      <div class="form-group">
        <label for="userId">User ID</label>
        <input type="text" id="userId" v-model="userId" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="userPassword" required>
      </div>
      <div class="form-group">
        <button type="submit">Log In</button>
      </div>
    </form>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
  data() {
    return {
      userId: '',
      userPassword: ''
    };
  },
  methods: {
    ...mapActions(['login']),

    async fnLogin() {
      //validation
      if(this.user_id === ''){
        alert('ID를 입력해주세요.')
        return
      }
      if(this.user_pw === '') {
        alert('비밀번호를 입력해주세요.')
        return
      }

      //로그인 API 호출
      try {
        let loginResult = await this.login({user_id: this.userId, user_pw: this.user.userPassword})
        if(loginResult) alert('로그인 결과 : ' + loginResult)
      } catch (err) {
        if(err.message.indexOf('Network Error') > -1) {
          alert('서버에 접속할 수 없습니다. 상태를 확인해주세요.')
        } else {
          alert('로그인 정보를 확인할 수 없습니다.')
        }
      }
    },
    gotoPages() {
      this.$router.push({
        name: 'List'
      })
    }
  },
  computed: {
    ...mapGetters({
      errorState: 'getErrorState'
    })
  }
};
</script>