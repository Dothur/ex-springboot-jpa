package com.example.jpa;


import com.example.jpa.dto.StudentDto;
import com.example.jpa.entities.StudentEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

//@Controller
@RestController
// 모든 메소드에 ResponseBody 를 붙이는 용도
public class AppController {
    // 사용자의 입력을 받는 요소
    private final AppService service;

    public AppController(AppService service) {
        this.service = service;
    }

    @GetMapping("create")
    public String create() {
        this.service.createStudent(
                "alex",
                35,
                "010-1111-2222",
                "alex@gmail.com"
        );
        return "done";
    }

    @GetMapping("read")
    public String readOne(){
        this.service.readStudent(1L);
        return "done-read-one";
    }

    @GetMapping("read-all")
    public List<StudentDto> readAll() {
        this.service.readStudentAll();
//        return "done-read-all";
        return this.service.readStudentAll();
    }

    @GetMapping("update")
    public String update(){
        this.service.updateStudent(1L, "alexy");
        return "done-update";
    }
    @GetMapping("delete")
    public String delete(){
        this.service.deleteStudent(1L);
        return "done-delete";
    }
    @GetMapping("find")
    public String find(){
        this.service.findAllByTest();
        return "done-find";
    }


//    // RequestMapping 과 같이 사용
//    @RequestMapping("students")
//    public void students() {
//        List<Object> result = service.readStudentAll();
//    }
//
//    @GetMapping("view")
//    public String home() {
//        return "home";
//    }
//
//    @GetMapping("body")
//    public String body() {
//        return "body";
//    }
}
