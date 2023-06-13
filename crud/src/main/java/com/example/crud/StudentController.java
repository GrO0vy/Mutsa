package com.example.crud;

import com.example.crud.model.StudentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
    // StudentService를 Controller에서 사용
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/create-view")
    public String createView() {
        return "create";
    }

    @PostMapping("/create")
    public String create(@RequestParam("name") String name, @RequestParam("email") String email) {
        System.out.println(name);
        System.out.println(email);

        StudentDTO studentDTO = studentService.createStudent(name, email);
        System.out.println(studentDTO.toString());
        return "redirect:/create-view";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("students", studentService.readStudentAll());
        return "home";
    }

    @GetMapping("/{id}/read")
    public String read(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", studentService.readStudent(id));
        return "read";
    }

    @GetMapping("/{id}/update-view")
    public String updateView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", studentService.readStudent(id));
        return "update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("email") String email, Model model) {
        studentService.updateStudent(id, name, email);
        model.addAttribute("student", studentService.readStudent(id));
        return String.format("redirect:%d/read/", id);
    }

    @GetMapping("/{id}/delete-view")
    public String deleteView(@PathVariable("id") Long id , Model model){
        model.addAttribute("student",studentService.readStudent(id));
        return "delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return "redirect:/home";
    }
}
