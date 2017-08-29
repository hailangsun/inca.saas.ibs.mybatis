package com.inca.saas.ibs.entity;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.inca.saas.ibs.support.SuperEntity;


@TableName("pub_user")
public class User extends SuperEntity<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1021203475848952666L;

	public User() {
		super();
	}

	private Long id;
	
	@TableField("user_code")
	private String userCode;

	//助记码
	@TableField("user_opcode")
	private String userOpcode;

	//姓名
	@TableField("user_name")
	private String userName;

	//部门
	@TableField("dept_id")
	private Integer dept;


	//身份证号
	@TableField("id_card")
	private String idCard;

	//出生日期
	@TableField("birth_date")
	private Date birthDate;

	//性别
	private Integer sex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserOpcode() {
		return userOpcode;
	}

	public void setUserOpcode(String userOpcode) {
		this.userOpcode = userOpcode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getDept() {
		return dept;
	}

	public void setDept(Integer dept) {
		this.dept = dept;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userCode=" + userCode + ", userOpcode=" + userOpcode + ", userName=" + userName
				+ ", dept=" + dept + ", idCard=" + idCard + ", birthDate=" + birthDate + ", sex=" + sex + "]";
	}


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
