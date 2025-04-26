package com.loong.x.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色菜单关联(SysRolesMenus)实体类
 *
 * @author makejava
 * @since 2024-04-22 01:47:23
 */
@Data
public class SysRolesMenus implements Serializable {
    private static final long serialVersionUID = 196468961127604673L;

    private String id;
    /**
     * 角色ID
     */
    private Integer roleId;

    @TableField(value = "menu_1")
    private Integer menu1;
    @TableField(value = "menu_2")
    private Integer menu2;
    @TableField(value = "menu_3")
    private Integer menu3;
    @TableField(value = "menu_4")
    private Integer menu4;
    @TableField(value = "menu_5")
    private Integer menu5;
    @TableField(value = "menu_6")
    private Integer menu6;
    @TableField(value = "menu_7")
    private Integer menu7;
    @TableField(value = "menu_8")
    private Integer menu8;
    @TableField(value = "menu_11")
    private Integer menu11;
    @TableField(value = "menu_12")
    private Integer menu12;
    @TableField(value = "menu_13")
    private Integer menu13;
    @TableField(value = "menu_14")
    private Integer menu14;
    @TableField(value = "menu_21")
    private Integer menu21;
    @TableField(value = "menu_22")
    private Integer menu22;
    @TableField(value = "menu_23")
    private Integer menu23;
    @TableField(value = "menu_24")
    private Integer menu24;
    @TableField(value = "menu_31")
    private Integer menu31;
    @TableField(value = "menu_32")
    private Integer menu32;
    @TableField(value = "menu_33")
    private Integer menu33;
    @TableField(value = "menu_34")
    private Integer menu34;
    @TableField(value = "menu_41")
    private Integer menu41;
    @TableField(value = "menu_42")
    private Integer menu42;
    @TableField(value = "menu_43")
    private Integer menu43;
    @TableField(value = "menu_44")
    private Integer menu44;
    @TableField(value = "menu_51")
    private Integer menu51;
    @TableField(value = "menu_52")
    private Integer menu52;
    @TableField(value = "menu_53")
    private Integer menu53;
    @TableField(value = "menu_54")
    private Integer menu54;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenu1() {
        return menu1;
    }

    public void setMenu1(Integer menu1) {
        this.menu1 = menu1;
    }

    public Integer getMenu2() {
        return menu2;
    }

    public void setMenu2(Integer menu2) {
        this.menu2 = menu2;
    }

    public Integer getMenu3() {
        return menu3;
    }

    public void setMenu3(Integer menu3) {
        this.menu3 = menu3;
    }

    public Integer getMenu4() {
        return menu4;
    }

    public void setMenu4(Integer menu4) {
        this.menu4 = menu4;
    }

    public Integer getMenu5() {
        return menu5;
    }

    public void setMenu5(Integer menu5) {
        this.menu5 = menu5;
    }

    public Integer getMenu6() {
        return menu6;
    }

    public void setMenu6(Integer menu6) {
        this.menu6 = menu6;
    }

    public Integer getMenu7() {
        return menu7;
    }

    public void setMenu7(Integer menu7) {
        this.menu7 = menu7;
    }

    public Integer getMenu8() {
        return menu8;
    }

    public void setMenu8(Integer menu8) {
        this.menu8 = menu8;
    }

    public Integer getMenu11() {
        return menu11;
    }

    public void setMenu11(Integer menu11) {
        this.menu11 = menu11;
    }

    public Integer getMenu12() {
        return menu12;
    }

    public void setMenu12(Integer menu12) {
        this.menu12 = menu12;
    }

    public Integer getMenu13() {
        return menu13;
    }

    public void setMenu13(Integer menu13) {
        this.menu13 = menu13;
    }

    public Integer getMenu14() {
        return menu14;
    }

    public void setMenu14(Integer menu14) {
        this.menu14 = menu14;
    }

    public Integer getMenu31() {
        return menu31;
    }

    public void setMenu31(Integer menu31) {
        this.menu31 = menu31;
    }

    public Integer getMenu32() {
        return menu32;
    }

    public void setMenu32(Integer menu32) {
        this.menu32 = menu32;
    }

    public Integer getMenu33() {
        return menu33;
    }

    public void setMenu33(Integer menu33) {
        this.menu33 = menu33;
    }

    public Integer getMenu34() {
        return menu34;
    }

    public void setMenu34(Integer menu34) {
        this.menu34 = menu34;
    }

    public Integer getMenu51() {
        return menu51;
    }

    public void setMenu51(Integer menu51) {
        this.menu51 = menu51;
    }

    public Integer getMenu52() {
        return menu52;
    }

    public void setMenu52(Integer menu52) {
        this.menu52 = menu52;
    }

    public Integer getMenu53() {
        return menu53;
    }

    public void setMenu53(Integer menu53) {
        this.menu53 = menu53;
    }

    public Integer getMenu54() {
        return menu54;
    }

    public void setMenu54(Integer menu54) {
        this.menu54 = menu54;
    }

}

