package com.ercan.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@Data
public class Person {
    @Id
    private Long id;
    private Date createDate;
    private Date modifyDate;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Date birthDate;
    private String country;
    private String phoneNumber;


    public Person(){
        setCreateDate(new Date());
    }


    @SneakyThrows
    @Override
    public String toString() {
//        final StringBuffer sb = new StringBuffer("");
//        sb.append("Person{");
//        sb.append("id:").append(id);
//        sb.append(", firstName:").append(firstName).append('\'');
//        sb.append(", lastName:").append(lastName).append('\'');
//        sb.append(", email:").append(email).append('\'');
//        sb.append(", gender:").append(gender).append('\'');
//        sb.append(", birthDate:").append(birthDate).append('\'');
//        sb.append(", country:").append(country).append('\'');
//        sb.append(", phoneNumber:").append(phoneNumber);
//        sb.append('}');
//        return sb.toString();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
