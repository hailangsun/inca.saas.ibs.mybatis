package com.inca.saas.ibs.entity;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.inca.saas.ibs.support.SuperEntity;


@TableName(value ="pub_user")
public class User extends SuperEntity<User>{

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -1021203475848952666L;

	public User() {
		super();
	}
	
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

	public User(Long id, String userCode, String userOpcode, String userName, Integer dept, String idCard,
			Date birthDate) {
		super();
		this.userCode = userCode;
		this.userOpcode = userOpcode;
		this.userName = userName;
		this.dept = dept;
		this.idCard = idCard;
		this.birthDate = birthDate;
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

	@Override
	public String toString() {
		return "User [userCode=" + userCode + ", userOpcode=" + userOpcode + ", userName=" + userName + ", dept=" + dept
				+ ", idCard=" + idCard + ", birthDate=" + birthDate + "]";
	}

	

}
