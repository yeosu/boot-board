<template>
  <header class="Header">
    <div id="nav" class="nav">
      <div class="nav-left">
        <router-link to="/">Home</router-link> |
        <router-link to="/board/list">Board</router-link> |
        <router-link to="/login" v-if="!this.$store.state.isLogin">Login</router-link><span v-if="!this.$store.state.isLogin"> | </span>
        <router-link to="/join" v-if="!this.$store.state.isLogin">Join</router-link>
      </div>
      <div class="nav-right">
        <a href="javascript:void(0)" style="float: right" v-if="this.$store.state.isLogin" v-on:click="fnLogout">Logout</a>
      </div>
    </div>
  </header>
  <hr/>
</template>

<script>
export default {
  methods: {
    fnLogout() {
      localStorage.removeItem('user_token');
      localStorage.removeItem('user_role');
      this.$router.push('/');
      location.reload();
    }
  }
}
</script>

<style scoped>

</style>