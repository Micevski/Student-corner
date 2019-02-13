<template>
      <div class = 'loginRegisterToolbar'>
            <v-btn flat class="loginMenuItem"><a href="/#/register">Регистрација</a></v-btn>
            <v-btn flat class="loginMenuItem"><a href="/#/login">Најава</a></v-btn>
      </div>
</template>

<script>
</script>

<style>
  .loginRegisterToolbar{
    height:40px !important;
    background-color: #009688 !important;
    text-align: right;
  }

  .loginMenuItem{
    height: 27px;
  }    

  .loginMenuItem a{
    color: white;
    text-decoration: none;
    font-size: 13px;
  }
</style>
