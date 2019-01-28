<template>
  <form>
      <div class='registerForm'>

          <h1 style="text-align:center">РЕГИСТРАЦИЈА</h1>
    <v-text-field
      v-model="name"
      :error-messages="nameErrors"
      label="Име"
      required
      @input="$v.name.$touch()"
      @blur="$v.name.$touch()"
    ></v-text-field>

    <v-text-field
      v-model="lastName"
      :error-messages="lastNameErrors"
      label="Презиме"
      required
      @input="$v.lastName.$touch()"
      @blur="$v.lastName.$touch()"
    ></v-text-field>

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

    <v-select
      v-model="select"
      :items="items"
      :error-messages="selectErrors"
      label="Факултет"
      required
      @change="$v.select.$touch()"
      @blur="$v.select.$touch()"
    ></v-select>
    <v-checkbox
      v-model="checkbox"
      :error-messages="checkboxErrors"
      label="Дали се согласувате со правилата на користење ?"
      required
      @change="$v.checkbox.$touch()"
      @blur="$v.checkbox.$touch()"
    ></v-checkbox>

    <div class="chooseButtons">
        <v-btn @click="submit">Потврди</v-btn>
    <v-btn @click="clear">Откажи</v-btn>
    </div>
    
    </div>
  </form>
</template>

<script>
  import { validationMixin } from 'vuelidate'
  import { required, maxLength, email } from 'vuelidate/lib/validators'

  export default {
    mixins: [validationMixin],

    validations: {
      name: { required },
      password: { required },
      lastName: { required },
      email: { required, email },
      select: { required },
      checkbox: {
        checked (val) {
          return val
        }
      }
    },

    data: () => ({
      name: '',
      lastName: '',
      email: '',
      select: null,
      items: [
        'Fakultet 1',
        'Fakultet 2',
        'Fakultet 3',
        'Srednoskolec'
      ],
      checkbox: false
    }),

    computed: {
      checkboxErrors () {
        const errors = []
        if (!this.$v.checkbox.$dirty) return errors
        !this.$v.checkbox.checked && errors.push('You must agree to continue!')
        return errors
      },
      selectErrors () {
        const errors = []
        if (!this.$v.select.$dirty) return errors
        !this.$v.select.required && errors.push('Item is required')
        return errors
      },
      nameErrors () {
        const errors = []
        if (!this.$v.name.$dirty) return errors
        !this.$v.name.required && errors.push('Name is required.')
        return errors
      },
      lastNameErrors () {
        const errors = []
        if (!this.$v.lastName.$dirty) return errors
        !this.$v.lastName.required && errors.push('Last Name is required.')
        return errors
      },
      passwordErrors () {
        const errors = []
        if (!this.$v.password.$dirty) return errors
        !this.$v.password.required && errors.push('Password is required.')
        return errors
      },
      emailErrors () {
        const errors = []
        if (!this.$v.email.$dirty) return errors
        !this.$v.email.email && errors.push('Must be valid e-mail')
        !this.$v.email.required && errors.push('E-mail is required')
        return errors
      }
    },

    methods: {
      submit () {
        this.$v.$touch()
      },
      clear () {
        this.$v.$reset()
        this.name = ''
        this.email = ''
        this.select = null
        this.checkbox = false
      }
    }
  }
</script>

<style>
    .registerForm{
        width:38%;
        margin:auto;
        min-width:350px;
    }
    .chooseButtons{
        margin-left:24%
    }
</style>