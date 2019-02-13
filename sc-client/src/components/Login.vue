<template>
  <form>
      <div class='loginForm'>

          <h1 style="text-align:center">НАЈАВА</h1>
    
    <v-text-field
      v-model="email"
      :error-messages="emailErrors"
      label="Е-маил"
      required
      @input="$v.email.$touch()"
      @blur="$v.email.$touch()"
    ></v-text-field>

    <v-text-field
      v-model="password"
      :error-messages="passwordErrors"
      :type="'password'"
      label="Лозинка"
      required
      @input="$v.password.$touch()"
      @blur="$v.password.$touch()"
    ></v-text-field>


    <div class="login">
        <v-btn @click="submit">Најави се</v-btn>
    </div>

    </div>
  </form>
</template>

<script>
  import { validationMixin } from 'vuelidate'
  import { required, email } from 'vuelidate/lib/validators'

  export default {
    mixins: [validationMixin],

    validations: {
      email: { required, email },
      password: { required }
    },

    data: () => ({
      email: ''
    }),

    computed: {
      emailErrors () {
        const errors = []
        if (!this.$v.email.$dirty) return errors
        !this.$v.email.required && errors.push('Внесете ја вашата е-маил адреса !')
        return errors
      },
      passwordErrors (){
          const errors = []
          if (!this.$v.password.$dirty) return errors
          !this.$v.password.required && errors.push('Внесете ја вашата лозинка !')
          return errors
      }
    },

    methods: {
      submit () {
        this.$v.$touch()
      },
      clear () {
        this.$v.$reset()
        this.email = ''
      }
    }
  }
</script>

<style>
    .loginForm{
        width:38%;
        margin:auto;
        min-width:350px;
    }
    .login{
        margin: auto
    }

    .login button{
        width:200px;
        margin-left:30%;
    }
</style>