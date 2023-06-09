<template>
  <div class="container">
    <h2>회원가입</h2>
    <form v-on:submit="registerUser">
      <input type="hidden" id="serverAuthCode" ref="serverAuthCode" v-model="user.serverAuthCode">
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
        <input type="email" ref="email" id="email" v-model="user.email" required>
      </div>
      <div class="form-group" ref="emailAuthArea" style="display: none">
        <label for="emailAuthCode">이메일 인증 코드</label>
        <input type="email" ref="emailAuthCode" id="emailAuthCode" v-model="user.emailAuthCode" required>
      </div>
      <button type="button" ref="authBtn" v-on:click="fnEmailAuth">인증코드 발송</button>
      <button type="button" ref="authCompleteBtn" style="display: none" v-on:click="fnCompleteEmailAuth">이메일 인증</button>
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
        emailAuthCode: '',
        serverAuthCode: '',
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

      if(!this.user.emailAuthCode){
        alert('이메일 인증 코드를 입력해주세요.');
        return false;
      }

      //유효성 검사 통과여부 확인
      if (Object.keys(this.errors).length > 0) {
        alert('미작성 항목이 존재합니다.');
        return;
      }

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
    },
    fnEmailAuth() {
      if(!this.user.email) {
        alert('이메일을 입력해주세요');
        return false;
      }
      this.$axios
          .post(this.$serverUrl + '/api/user/sendEmail', this.user)
          .then((response) => {
            alert('이메일 인증 코드가 전송되었습니다.');
            this.$refs.emailAuthArea.style.display = 'block' ;
            this.$refs.authBtn.style.display = 'none';
            this.$refs.authCompleteBtn.style.display = 'block';
            this.serverAuthCode = response.data;
            console.log(response.data);
          })
          .catch((err) => {
            if(err.message.indexOf('Network Error') > -1) {
              alert('이메일 인증 코드 전송 중 오류가 발생했습니다. \n 다시 시도해주세요.')
            }
          });
    },
    fnCompleteEmailAuth() {
      if(!this.user.emailAuthCode){
        alert('이메일 인증 코드를 입력해주세요.');
        return false;
      }
      this.$axios
          .post(this.$serverUrl + '/api/user/checkEmailAuth', {emailAuthCode: this.user.emailAuthCode, serverAuthCode: this.serverAuthCode})
          .then((response) => {
            if(response.data==="Success"){
              alert("이메일 인증이 완료되었습니다.");
              this.$refs.email.readOnly=true;
              this.$refs.emailAuthCode.style.display='none';
            }else{
              alert("인증번호가 일치하지 않습니다.");
            }
          })
          .catch((err) => {
            if(err.message.indexOf('Network Error') > -1){
              alert('이메일 인증 중 오류가 발생했습니다. \n 다시 시도해주세요.');
            }else{
              alert('이메일 인증이 실패했습니다. \n 다시 시도해주세요.');
            }
          })
    }
  }
};
</script>