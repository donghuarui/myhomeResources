package com.dhr.entity;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String majoy;



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

    public String getMajoy() {
        return majoy;
    }

    public void setMajoy(String majoy) {
        this.majoy = majoy;
    }
}
