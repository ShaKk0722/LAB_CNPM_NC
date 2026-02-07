package vn.edu.hcmut.cse.adsoftweng.lab.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    public Student createStudent(String name, String email, int age) {
        Student student = new Student(UUID.randomUUID().toString(), name, email, age);
        return repository.save(student);
    }

    public Student updateStudent(String id, String name, String email, int age) {
        Student existing = getById(id);
        if (existing == null) {
            return null;
        }
        existing.setName(name);
        existing.setEmail(email);
        existing.setAge(age);
        return repository.save(existing);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
