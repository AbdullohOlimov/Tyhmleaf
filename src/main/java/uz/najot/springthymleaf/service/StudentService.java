package uz.najot.springthymleaf.service;

import uz.najot.springthymleaf.model.StudentModel;

import java.util.List;

public interface StudentService {
    List<StudentModel> getAllStudentsByGroupId(Integer groupId);
}
