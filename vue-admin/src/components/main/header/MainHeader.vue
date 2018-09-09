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
            <Col span="6">
                <div>&nbsp;</div>
            </Col>
            <Col span="6">
                <FullScreen v-model="isFullScreen"/>
                <!--<User/>-->
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
            }
        }
    }
</script>

<style scoped>

</style>
