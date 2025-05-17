package com.xworkz.news.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "register_details")

@NamedQuery(name = "findByEmailInRegister", query = "select entity from RegisterEntity entity where entity.email=:emailValue")
@NamedQuery(name = "findByPhNoInRegister", query = "select entity from RegisterEntity entity where entity.phNo=:getPhoneNumber")

public class RegisterEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "f_Name")
    private String fname;

    @Column(name = "l_Name")
    private String lname;

    @Column(name = "email")
    private String email;

    @Column(name = "ph_No")
    private String phNo;

    @Column(name = "Date_of_birth")
    private String dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "city")
    private String city;

    @Column(name = "pinCode")
    private Integer pinCode;

    @Column(name = "password")
    private String password;

    @Column(name="otp")
    private String otp;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_Content_Type")
    private String fileContentType;

}
