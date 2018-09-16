<template>
    <Header :style="{padding: 0}" class="layout-header-bar">
        <Row>
            <Col span="2" order="1">
                <Icon @click.native="collapsedSider" :class="rotateIcon" :style="{margin: '0 20px'}" type="md-menu"
                      size="24"></Icon>
            </Col>
            <Col span="10" order="2">
                <Breadcrumb>
                    <BreadcrumbItem to="/">
                        <Icon type="ios-home-outline"></Icon>
                        Home
                    </BreadcrumbItem>
                    <BreadcrumbItem to="/components/breadcrumb">
                        <Icon type="logo-buffer"></Icon>
                        Components
                    </BreadcrumbItem>
                    <BreadcrumbItem>
                        <Icon type="ios-cafe"></Icon>
                        Breadcrumb
                    </BreadcrumbItem>
                </Breadcrumb>
            </Col>
            <Col span="6" order="3">
                <div>&nbsp;</div>
            </Col>
            <Col span="4" order="4">
                &nbsp;
            </Col>
            <Col span="1" order="5">
                <FullScreen v-model="isFullScreen"/>
            </Col>
            <Col span="1" order="6">
                <Dropdown @click="handleClick">
                    <Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg"/>
                    <Icon :size="18" type="md-arrow-dropdown"></Icon>
                    <DropdownMenu slot="list">
                        <DropdownItem name="logout">退出登录</DropdownItem>
                    </DropdownMenu>
                </Dropdown>
            </Col>
        </Row>
    </Header>
</template>

<script>
    import FullScreen from '../fullscreen/FullScreen'
    import User from '../user/User'
    export default {
        name: 'MainHeader',
        data(){
            return{
                isFullScreen: true
            }
        },
        components: {
            FullScreen,
            User
        },
        computed: {
            rotateIcon() {
                return [
                    'menu-icon',
                    this.isCollapsed ? 'rotate-icon' : ''
                ];
            }
        },
        methods: {
            collapsedSider() {
                this.$emit('collapsedSider')
            },
            handleClick(){

            }
        }
    }
</script>

<style lang="less">
    .full-screen-btn-con .ivu-tooltip-rel{
        height: 64px;
        line-height: 64px;
    i{
        cursor: pointer;
    }
    float: right;
    }
</style>
