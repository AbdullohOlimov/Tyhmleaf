package uz.najot.springthymleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
