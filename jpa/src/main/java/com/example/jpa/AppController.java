package com.example.jpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 모든 메소드에 ResponseBody 를 븉이는 용도
//@RestController
@Controller
public class AppController {
    // 사용자의 입력을 받는 요소

    // 서비스 객체를 생성자 주입 ( IoC 컨테이너의 Bean 객체로 부터 서비스 클래스를 가져온다 )
    private final AppService service;

    @GetMapping("create")
    public @ResponseBody String create(){
        this.service.createStudent("lee",25,"010-1231-2313","asd@naver.com");
        return "Done";
    }

    @GetMapping("read-all")
    public @ResponseBody String readAll(){
        this.service.readStudentAll();
        return "done-read-all";
    }

    @GetMapping("read")
    public @ResponseBody String readOne(){
        this.service.readStudent(1L);
        return "done-read-one";
    }

    @GetMapping("update")
    public @ResponseBody String update(){
        this.service.updateStudent(1L,"mincheol");
        return "done-update";
    }

    @GetMapping("delete")
    public @ResponseBody String delete(Long id){
        this.service.deleteStudent(1L);
        return "done-delete";
    }

    @GetMapping("find")
    public @ResponseBody String find(){
        this.service.findAllByTest();
        return "done-find";
    }
    public AppController(AppService service){
        this.service = service;
    }

    // RequestMapping 과 같이 사용
//    @RequestMapping("students")
//    public void students(){
//        List<Object> result = service.readStudentAll();
//    }
//
//    // html을 리턴
//    @GetMapping("home")
//    public String home(){
//        return "home";
//    }
//
//    // html 이 아니라 body 라는 문자열 데이터를 반환한다.
//    // html은 웹 브라우저에서 자동으로 만들어준다.
//    @GetMapping("body")
//    public @ResponseBody String body(){
//        return "body"; // html이 아니라 body라는 문자열 데이터를 반환한다.
//    }
}
