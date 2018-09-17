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
    import {Message} from 'iview'

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
                        this.$Spin.show();
                        this.postRequest('/login', {
                            userName: 'supAdmin',
                            password: '888888'
                        }).then((req) => {
                            this.$Spin.hide();
                            if (req && req.data && req.data.status === 200) {
                                const data = req.data
                                if (data.status === 200) {
                                    _this.$store.dispatch('user/login', data.data);
                                    //setToken(data.data)
                                    _this.$router.push("/")
                                }
                            }
                        })
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>
