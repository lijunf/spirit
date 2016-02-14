package com.lucien.spirit.module.person.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.lucien.spirit.core.model.BaseModel;

/**
 * 用户实体
 * 
 * @Filename : Person.java
 * @Package : com.lucien.spirit.model
 * @Description : TODO
 * @author : lijunf
 * @CreateDate : 2016年1月21日
 */
@Entity
@Table(name = "crm_person")
public class Person extends BaseModel {

    private static final long serialVersionUID = 7111654406869954106L;

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    @Size(min = 1, max = 30)
    @NotNull
    private String name;

    @Column(name = "AGE")
    @Min(1)
    @Max(200)
    @NotNull
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
