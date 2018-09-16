<template>
    <div class="layout">
        <Layout>
            <!--<SiderMenu ref="sider"/>-->
            <Sider ref="sider" style="min-height: 700px;" hide-trigger collapsible :collapsed-width="78" v-model="isCollapsed">
                <Menu active-name="1-1" theme="dark" width="auto" :class="menuitemClasses">
                    <MenuItem name="1-1">
                        <Icon type="ios-home" />
                        <span>主页</span>
                    </MenuItem>
                    <Submenu name="1-2">
                        <template slot="title">
                            <Icon type="ios-settings" />
                           <span>系统设置</span>
                        </template>
                        <MenuItem name="2-1">
                            <Icon type="md-person" />
                            <span>用户管理</span>
                        </MenuItem>
                        <MenuItem name="2-2">Option 6</MenuItem>
                        <Submenu name="3">
                            <template slot="title">Submenu</template>
                            <MenuItem name="3-1">Option 7</MenuItem>
                            <MenuItem name="3-2">Option 8</MenuItem>
                        </Submenu>
                    </Submenu>
                    <MenuItem name="1-2">
                        <Icon type="ios-search"></Icon>
                        <span>Option 2</span>
                    </MenuItem>
                    <MenuItem name="1-3">
                        <Icon type="ios-settings"></Icon>
                        <span>Option 3</span>
                    </MenuItem>
                </Menu>
            </Sider>
            <Layout>
                <MainHeader @collapsedSider="collapsedSider"/>
                <Content :style="{margin: '20px', background: '#fff', minHeight: '260px'}">
                    Content
                </Content>
                <Footer class="layout-footer-center">2011-2016 &copy; TalkingData</Footer>
            </Layout>
        </Layout>
    </div>
</template>

<script>
    import SiderMenu from '../../components/main/sider/SiderMenu'
    import MainHeader from '../../components/main/header/MainHeader'
    export default {
        name: 'Main',
        components:{
            SiderMenu,
            MainHeader
        },
        data() {
            return {
                isCollapsed: false
            }
        },
        computed: {
            rotateIcon() {
                return [
                    'menu-icon',
                    this.isCollapsed ? 'rotate-icon' : ''
                ];
            },
            menuitemClasses() {
                return [
                    'menu-item',
                    this.isCollapsed ? 'collapsed-menu' : ''
                ]
            }
        },
        methods: {
            collapsedSider() {
                this.$refs.sider.toggleCollapse()
            }
        }
    }
</script>

<style scoped>
    .layout{
        width: 100%;
        height: 100%;
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
    }
    .layout-header-bar{
        background: #fff;
        box-shadow: 0 1px 1px rgba(0,0,0,.1);
    }
    .layout-logo-left{
        width: 90%;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        margin: 15px auto;
    }
    .menu-icon{
        transition: all .3s;
    }
    .rotate-icon{
        transform: rotate(-90deg);
    }
    .menu-item span{
        display: inline-block;
        overflow: hidden;
        width: 69px;
        text-overflow: ellipsis;
        white-space: nowrap;
        vertical-align: bottom;
        transition: width .2s ease .2s;
    }
    .menu-item i{
        transform: translateX(0px);
        transition: font-size .2s ease, transform .2s ease;
        vertical-align: middle;
        font-size: 16px;
    }
    .collapsed-menu span{
        width: 0px;
        transition: width .2s ease;
    }
    .collapsed-menu i{
        transform: translateX(5px);
        transition: font-size .2s ease .2s, transform .2s ease .2s;
        vertical-align: middle;
        font-size: 22px;
    }
    .layout-footer-center{
        text-align: center;
    }
</style>
