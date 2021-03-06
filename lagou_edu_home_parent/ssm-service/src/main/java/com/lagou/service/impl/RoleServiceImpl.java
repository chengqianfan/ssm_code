package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    /*
        查询所有角色（条件）
    */
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    /*
        添加角色
     */
    @Override
    public void saveRole(Role role) {
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        roleMapper.saveRole(role);
    }

    @Override
    public Role findRoleById(int id) {
        Role role = roleMapper.findRoleById(id);
        return role;
    }

    /*
        修改角色
     */
    @Override
    public void updateRole(Role role) {
        Date date = new Date();
        role.setUpdatedTime(date);

        roleMapper.updateRole(role);
    }

    /*
        根据角色ID查询该角色关联的菜单信息ID [1,2,3,5]
    */
    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);

        return menuByRoleId;
    }

    /*
        为角色分配菜单
    */
    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        //1. 清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2. 为角色分配菜单

        for (Integer mid : roleMenuVo.getMenuIdList()) {

            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");


            roleMapper.roleContextMenu(role_menu_relation);
        }

    }

    /*
        删除角色
    */
    @Override
    public void deleteRole(Integer roleid) {

        // 调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleid);

        roleMapper.deleteRole(roleid);
    }

    @Override
    public List<Integer> findResourceListByRoleId(Integer roleId) {
        List<Integer> resourceListByRoleId = roleMapper.findResourceListByRoleId(roleId);
        return resourceListByRoleId;
    }

    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {
        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());
        for (Integer rid : roleResourceVo.getResourceIdList()) {

            Role_resource_relation role_resource_relation = new Role_resource_relation();
            role_resource_relation.setResourceId(rid);
            role_resource_relation.setRoleId(roleResourceVo.getRoleId());

            //封装数据
            role_resource_relation.setCreatedBy("system");
            role_resource_relation.setUpdatedBy("system");


            roleMapper.roleContextResource(role_resource_relation);
        }
    }
}
