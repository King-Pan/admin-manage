<template>
    <Form ref="loginForm" :model="form" :rules="rules" @keydown.enter.native="handleSubmit">
        <FormItem prop="userName">
            <Input v-model="form.userName" placeholder="请输入用户名">
            <span slot="prepend">
          <Icon :size="16" type="ios-person"></Icon>
        </span>
            </Input>
        </FormItem>
        <FormItem prop="password">
            <Input type="password" v-model="form.password" placeholder="请输入密码">
            <span slot="prepend">
          <Icon :size="14" type="md-lock"></Icon>
        </span>
            </Input>
        </FormItem>
        <FormItem>
            <Button @click="handleSubmit" type="primary" long>登录</Button>
        </FormItem>
    </Form>
</template>

<script>
    import {setToken} from "../../assets/js/token";

    export default {
        name: 'LoginForm',
        props: {
            userNameRules: {
                type: Array,
                default: () => {
                    return [
                        {required: true, message: '账号不能为空', trigger: 'blur'}
                    ]
                }
            },
            passwordRules: {
                type: Array,
                default: () => {
                    return [
                        {required: true, message: '密码不能为空', trigger: 'blur'}
                    ]
                }
            }
        },
        data() {
            return {
                form: {
                    userName: 'admin',
                    password: ''
                }
            }
        },
        computed: {
            rules() {
                return {
                    userName: this.userNameRules,
                    password: this.passwordRules
                }
            }
        },
        methods: {
            handleSubmit() {
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        var _this = this;
                        console.log('登陆成功')
                        var data = {userName: 'supAdmin', password: '888888'}
                        this.$axios({
                            method: 'post',
                            url: '/login',
                            data: this.$qs.stringify({
                                userName: 'supAdmin',
                                password: '888888'
                            })
                        }).then((req) => {
                            console.log(req.data)
                            const data = req.data
                            if(data.status === 200){
                                setToken(data.data)
                                console.log(_this)
                                _this.$router.push("/")
                            }
                        })
                        // this.$axios.post("/login", this.$qs.stringify({
                        //     userName: 'supAdmin',
                        //     password: '888888'
                        // }, {indices: false}), response => {
                        //     if (response.status >= 200 && response.status < 300) {
                        //         console.log(response.data);
                        //     } else {
                        //         console.log(response.message);
                        //     }
                        //     console.log(response)
                        // })
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>
