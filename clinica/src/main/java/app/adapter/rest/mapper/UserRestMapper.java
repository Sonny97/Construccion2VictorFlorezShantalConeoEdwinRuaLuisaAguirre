package app.adapter.rest.mapper;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.builder.UserBuilder;
import app.adapter.rest.request.CreateUserRequest;
import app.adapter.rest.response.UserResponse;
import app.domain.model.Employee;

@Component
public class UserRestMapper {

    @Autowired
    private UserBuilder userBuilder;

     public Employee toDomain(CreateUserRequest req) throws Exception {
        return userBuilder.build(
            req.getUserName(),
            req.getPassword(),
            req.getFirstName(),
            req.getLastName(),
            req.getEmail(),
            req.getDocumentId(),
            req.getBirthDate(),
            req.getGender(),
            req.getAddress(),
            req.getPhoneNumber()
        );
    }

    public UserResponse toResponse(Employee employee) {
        UserResponse res = new UserResponse();
        res.setId(employee.getId());
        res.setUserName(employee.getUserName());
        res.setPassword(employee.getPassword());
        res.setFirstName(employee.getFirstName());
        res.setLastName(employee.getLastName());
        res.setEmail(employee.getEmail());
        res.setDocumentId(employee.getDocumentId());
        res.setBirthDate(employee.getBirthDate());
        res.setGender(employee.getGender());
        res.setAddress(employee.getAddress());
        res.setPhoneNumber(employee.getPhoneNumber());
        res.setRole(employee.getRole() != null ? String.valueOf(employee.getRole()) : null);
        return res;
    }
}
