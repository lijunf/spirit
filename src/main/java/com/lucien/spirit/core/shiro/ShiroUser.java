package com.lucien.spirit.core.shiro;

import java.io.Serializable;

/**
 * @Title: ShiroUser.java 
 * @Package com.lucien.spirit.core.shiro 
 * @Description: TODO 
 * @author lucien   
 * @date 2016年1月28日 下午11:17:52 
 * @version V1.0
 */
public class ShiroUser implements Serializable {

	private static final long serialVersionUID = 844463378615021620L;

	/**
	 * 用户id
	 */
	private Long id;
	
	/**
	 * 用户名
	 */
	private String name;
	
	/**
	 * 密码错误次数
	 */
	private int errorNo;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	public ShiroUser() {
		
	}

	public ShiroUser(Long id, String name, String realName, int errorNo) {
		super();
		this.id = id;
		this.name = name;
		this.realName = realName;
		this.errorNo = errorNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getErrorNo() {
		return errorNo;
	}

	public void setErrorNo(int errorNo) {
		this.errorNo = errorNo;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShiroUser other = (ShiroUser) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
	
}
