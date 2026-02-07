package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;

    // Route: GET http://localhost:8080/students
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.trim().isEmpty()) {
            students = service.searchByName(keyword.trim());
        } else {
            students = service.getAll();
        }

        model.addAttribute("dsSinhVien", students);
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        return "students";
    }

    // Trang chi tiết: GET /students/{id}
    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            // đơn giản: quay về danh sách nếu không tìm thấy
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "student-detail";
    }

    // Trang thêm mới: GET /students/new
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("mode", "create");
        return "student-form";
    }

    // Xử lý lưu thêm mới: POST /students
    @PostMapping
    public String handleCreate(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age) {
        service.createStudent(name, email, age);
        return "redirect:/students";
    }

    // Trang chỉnh sửa: GET /students/{id}/edit
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        model.addAttribute("mode", "edit");
        return "student-form";
    }

    // Xử lý lưu chỉnh sửa: POST /students/{id}
    @PostMapping("/{id}")
    public String handleUpdate(
            @PathVariable String id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age) {
        service.updateStudent(id, name, email, age);
        return "redirect:/students/" + id;
    }

    // Xóa: POST /students/{id}/delete
    @PostMapping("/{id}/delete")
    public String handleDelete(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/students";
    }
}

