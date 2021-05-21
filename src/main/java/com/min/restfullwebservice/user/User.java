package com.min.restfullwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"password"})
//두번쨰 방법) 클래스 블록으로 클래스 전역에 password라는 필드를 @JsonIgnore하는것. 별반 다르진않은듯.
@JsonFilter("UserInfo")
//세번쨰 방법) @JsonFilter
public class User {

    private Integer id;

    @Size(min=2,message = "이름은 2글자 이상 입력해주세요.")
    private String name;
    @Past //과거날짜만 가능하다는 제약
    private Date joinDate;

    //@JsonIgnore
    private String password;
    //@JsonIgnore
    private String ssn;
    //첫번쨰 방법) @JsonIgnore를 추가하면 응답시에 json데이터가 무시가됨.(
}
