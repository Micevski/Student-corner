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

            <div class="login">
                <v-btn @click="loginAsAdmin">Најави се како админ</v-btn>
            </div>

            <h5 :class="{hasError: error, initial: vInitial}" >Погрешна емаил адреса или лозинка</h5>

        </div>
    </form>
</template>

<script>
    import {validationMixin} from 'vuelidate'
    import {email, required} from 'vuelidate/lib/validators'
    import { EventBus } from '../main';

    export default {
        mixins: [validationMixin],

        validations: {
            email: {required, email},
            password: {required}
        },

        data: () => ({
            email: '',
            password: '',
            error: false,
            vInitial: true
        }),

        computed: {
            emailErrors() {
                this.error = false;
                this.vInitial = true;
                const errors = []
                if (!this.$v.email.$dirty) return errors
                !this.$v.email.required && errors.push('Внесете ја вашата е-маил адреса !')
                return errors
            },
            passwordErrors() {
                this.error = false;
                this.vInitial = true;
                const errors = []
                if (!this.$v.password.$dirty) return errors
                !this.$v.password.required && errors.push('Внесете ја вашата лозинка !')
                return errors
            }
        },

        methods: {
            submit() {
                this.error = false;
                this.vInitial = true;
                this.$v.$touch()
                let form = new FormData();
                form.append("email", this.email);
                form.append("password", this.password);
                this.$http.post('/api/login', form,
                    {
                        headers: {'Content-Type': 'multipart/form-data'}
                    }).then((res) => {
                    const status = JSON.parse(res.status);
                    if (status == 200){
                        this.$router.push('/')
                        this.emitGlobalClickEvent()
                    }

                }).catch((er) => {
                    console.log(er);
                    this.error = true;
                    this.vInitial = false;
                });

            },
            clear() {
                this.$v.$reset()
                this.email = ''
            },
            emitGlobalClickEvent() {
                console.log("iam clicked")
                // Send the event on a channel (i-got-clicked) with a payload (the click count.)
                EventBus.$emit('i-got-clicked', true);
            },
            loginAsAdmin() {
                this.error = false;
                this.vInitial = true;
                this.$v.$touch()
                let form = new FormData();
                form.append("email", this.email);
                form.append("password", this.password);
                this.$http.post('/api/admin/login', form,
                    {
                        headers: {'Content-Type': 'multipart/form-data'}
                    }).then((res) => {
                    const status = JSON.parse(res.status);
                    if (status == 200){
                        this.$router.push('/')
                        this.emitGlobalClickEvent()
                    }

                }).catch((er) => {
                    console.log(er);
                    this.error = true;
                    this.vInitial = false;
                });

            }


        }
    }
</script>

<style>
    .loginForm {
        width: 38%;
        margin: auto;
        min-width: 350px;
    }

    .login {
        margin: auto
    }

    .login button {
        width: 200px;
        margin-left: 30%;
    }

    .hasError {
        color: red;
        visibility: visible;
    }

    .initial {
        visibility: hidden;
    }
</style>