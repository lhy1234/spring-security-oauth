package com.ayang.app.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


import javax.validation.constraints.Past;

/**
 * <p>
 * 
 * </p>
 *
 * @author lihaoyang123
 * @since 2018-11-15
 */
@TableName("tb_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "用户名不能为空")
    @Length(max = 20,message = "用户名最长为{max}位")
    private String username;
    private String password;

    @Email
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @Past(message = "出生日期只能是过去的日期")
    private Date birthday;
    @TableField("is_del")
    private Integer isDel;
    @TableField("is_locked")
    private Integer isLocked;






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", email=" + email +
        ", birthday=" + birthday +
        ", isDel=" + isDel +
        ", isLocked=" + isLocked +
        "}";
    }
}
