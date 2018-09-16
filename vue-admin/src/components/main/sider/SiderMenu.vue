<template>
    <Sider ref="sider"  hide-trigger collapsible :collapsed-width="78" v-model="isCollapsed">
        <Menu active-name="1-2" theme="dark" width="auto" :class="menuitemClasses">
            <MenuItem name="1-1">
                <Icon type="md-home" />
                <span>Option 1</span>
            </MenuItem>
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
</template>

<script>
    export default {
        name: 'SiderMenu',
        data() {
            return {
                isCollapsed: false
            }
        },
        computed: {
            menuitemClasses() {
                return [
                    'menu-item',
                    this.isCollapsed ? 'collapsed-menu' : ''
                ]
            }
        },
        methods:{
            toggleCollapse(){
                this.$refs.sider.toggleCollapse()
            }
        }
    }
</script>

<style scoped>
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
</style>
