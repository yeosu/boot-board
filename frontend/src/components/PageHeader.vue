<template>
  <header class="Header">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/board/list">Board</router-link> |
      <router-link to="/login" v-if="!this.$store.state.isLogin">Login</router-link> |
      <router-link to="/join" v-if="!this.$store.state.isLogin">Join</router-link>
      <a v-if="this.$store.state.isLogin" @click="fnLogout">Logout</a>
    </div>
  </header>
  <hr/>
</template>

<script>
export default {
  methods: {
    fnLogout() {
      localStorage.setItem('user_token', null)
      localStorage.setItem('user_role', null)
    }
  }
}
</script>

<style scoped>

</style>