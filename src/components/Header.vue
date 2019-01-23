<template>
    <v-toolbar>
        <v-toolbar-side-icon><img class="logo" src="../assets/Eductation-icon.jpg"></v-toolbar-side-icon>
        <v-toolbar-title>Студентски Агол</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-toolbar-items centered class="hidden-sm-and-down">
            <v-btn active flat>Почетна</v-btn>
            <v-btn flat>Услуги</v-btn>
            <v-btn flat>Попусти</v-btn>
            <v-btn flat>Новости</v-btn>
        </v-toolbar-items>
    </v-toolbar>
</template>

<script>
    export default {
        data: () => ({
            works: "Header"
        })
    }
</script>

<style>

    .logo {
        width: 90%;
        height: 90%;
    }

</style>
