package uz.najot.springthymleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.najot.springthymleaf.service.StudentService;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("{groupId}")
    public String getGroups(@PathVariable Integer groupId, Model model){
        model.addAttribute("students",studentService.getAllStudentsByGroupId(groupId));
        return "students";

    }
}
