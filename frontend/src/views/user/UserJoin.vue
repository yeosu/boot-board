<template>
  <div class="container">
    <h2>회원가입</h2>
    <form v-on:submit="registerUser">
      <div class="form-group">
        <label for="userId">아이디</label>
        <input type="text" id="userId" v-model="user.userId" required>
      </div>
      <div class="form-group">
        <label for="password">비밀번호</label>
        <input type="password" id="userPassword" v-model="user.userPassword" required>
      </div>
      <div class="form-group">
        <label for="userPassword2">비밀번호 확인</label>
        <input type="password" id="userPassword2" v-model="user.userPassword2" required>
      </div>
      <div class="form-group">
        <label for="userName">이름</label>
        <input type="text" id="userName" v-model="user.userName" required>
      </div>
      <div class="form-group">
        <label for="nickname">닉네임</label>
        <input type="text" id="nickname" v-model="user.nickname" required>
      </div>
      <div class="form-group">
        <label for="email">이메일</label>
        <input type="email" id="email" v-model="user.email" required>
      </div>
      <div class="form-group">
        <label for="birth">생년월일</label>
        <input type="date" id="birth" v-model="user.birth" required>
      </div>
      <button type="submit">가입하기</button>
    </form>
  </div>
</template>

<script>

export default {
  data() {
    return {
      requestBody: this.$route.query,
      id: this.$route.query.id,
      user: {
        userId: '',
        userPassword: '',
        userPassword2: '',
        userName: '',
        nickname: '',
        password: '',
        password2: '',
        email: '',
        birth: ''
      },
      errors: {}
    };
  },
  methods: {
    registerUser() {
      // 유효성검사
      if(!this.user.userId) {
        alert('아이디를 입력해주세요');
        return false;
      }
      if(!this.user.userPassword) {
        alert('비밀번호를 입력해주세요');
        return false;
      }
      if(!this.user.userPassword2) {
        alert('비밀번호 확인을 입력해주세요');
        return false;
      }
      if(!this.user.userName) {
        alert('이름을 입력해주세요');
        return false;
      }
      if(!this.user.nickname) {
        alert('닉네임을 입력해주세요');
        return false;
      }
      if(!this.user.email) {
        alert('이메일을 입력해주세요');
        return false;
      }
      if(this.user.password !== this.user.password2){
        alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
        return false;
      }

      //유효성 검사 통과여부 확인
      if (Object.keys(this.errors).length > 0) {
        alert('미작성 항목이 존재합니다.');
        return;
      }
      console.log("회원가입");
      // this.user 객체에 입력된 회원 정보를 서버로 전송하거나 처리하는 코드
      this.$axios
          .post(this.$serverUrl + '/api/user/join', this.user)
          .then(() => {
            alert('회원가입이 완료되었습니다.');
            this.$router.push("/");
          })
          .catch((err) => {
            if(err.message.indexOf('Network Error') > -1) {
              alert('회원가입 중 오류가 발생했습니다. \n 다시 시도해주세요.')
            }
          });
    }
  }
};
</script>