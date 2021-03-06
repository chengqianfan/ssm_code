package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;

import java.util.List;

public interface MenuService {

    /*
        查询所有父子菜单信息
     */

    public List<Menu> findSubMenuListByPid(int pid);


    /*
        查询所有菜单信息
     */
    public PageInfo findAllMenu(MenuVo menuVo);

    /*
        根据ID查询menu
     */
    public Menu findMenuById(Integer id);

    /*
        添加菜单信息
    */
    public void saveMenu(Menu menu);

    /*
        修改菜单信息
     */
    public void updateMenu(Menu menu);

    /*
        删除菜单信息
     */
    public void deleteMenu(int id);
}
