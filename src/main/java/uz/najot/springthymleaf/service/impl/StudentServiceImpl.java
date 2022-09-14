package uz.najot.springthymleaf.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import uz.najot.springthymleaf.model.StudentModel;
import uz.najot.springthymleaf.service.StudentService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService, RowMapper<StudentModel> {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<StudentModel> getAllStudentsByGroupId(Integer groupId) {
        return jdbcTemplate.query("select id, first_name, last_name, phone, email from public.students where group_id = ?",
                this, groupId);
    }

    @Override
    public StudentModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new StudentModel(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5)
        );
    }
}
