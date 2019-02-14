<template>
    <div>
        <div v-if="!logedUser" class='loginRegisterToolbar'>
            <v-btn flat class="loginMenuItem"><a href="/#/register">Регистрација</a></v-btn>
            <v-btn flat class="loginMenuItem"><a href="/#/login">Најава</a></v-btn>
        </div>
        <div v-if="logedUser" class='loginRegisterToolbar'>
            <v-btn v-if="isAdminLogged" flat class="loginMenuItem"><a href="/#/newpost">Креирај Артикал</a></v-btn>
            <v-btn flat class="loginMenuItem"><a href="/#/register">{{user.firstName}}</a></v-btn>
            <v-btn flat class="loginMenuItem"><a v-on:click="logOut">Одјава</a></v-btn>
        </div>
    </div>
</template>

<script>
    import { EventBus } from '../main';

    export default {
        data: () => ({
            works: "Header",
            user: '',
            logedUser:false,
            isAdminLogged:false
        }),
        watch: {
            '$route' (to, from) {
                this.getLoggedUser();
            }
        },

        created() {
            this.getLoggedUser();
            this.isAdmin();
            EventBus.$on('i-got-clicked', clickCount => {
                this.logedUser=true;
            });
        },
        methods: {
            getLoggedUser: function () {
                this.$http.get('/api/user/loggedUser')
                    .then((res) => {
                        let status = JSON.parse(res.status);
                        if (status == 200) {
                            this.user = res.data
                            this.logedUser=true;
                        }
                    }).catch(() => {
                    this.logedUser = false;
                });
            },

            logOut: function () {
                this.$http.get('/api/logout')
                    .then(() => {
                        this.logedUser=false;
                        this.$router.push('/');

                    }).catch((e) => console.log("Error occurred", e))
            },

            isAdmin: function () {
                this.$http.get('/api/admin/loggedAdmin')
                    .then((res) => {
                        let status = JSON.parse(res.status);
                        if (status == 200) {
                            this.user = res.data;
                            this.isAdminLogged=true;
                            this.logedUser = true;
                        }
                    }).catch(() => {
                    this.isAdminLogged=false;
                });
            }
        }
    }

</script>

<style>
    .loginRegisterToolbar {
        height: 40px !important;
        background-color: #009688 !important;
        text-align: right;
    }

    .loginMenuItem {
        height: 27px;
    }

    .loginMenuItem a {
        color: white;
        text-decoration: none;
        font-size: 13px;
    }
</style>
