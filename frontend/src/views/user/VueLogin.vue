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
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userId: '',
      userPassword: '',
      errorMessage: ''
    };
  },
  methods: {
    login() {
      // 로그인 요청 처리
    }
  }
};
</script>