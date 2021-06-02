package xyz.ruhshan.cachingdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.ruhshan.cachingdemo.entity.Student;

@RestController
public class StudentController {
    @GetMapping("student/{id}")
    public Student getStudent(@PathVariable("id") int id){
        return Student.builder().id(0).name("John Doe").address("221B Gotham Street, Metrocity, Neverland").build();
    }
}
