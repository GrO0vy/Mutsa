package com.example.mybatis.model;

import lombok.*;

//@Getter
//@Setter
//@RequiredArgsConstructor
//@ToString
//@EqualsAndHashCode
//위를 다 합친 것이 @Data 이다.
@Data
public class Student {
    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String email;
}
