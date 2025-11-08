package app.adapter.rest.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.adapter.in.builder.EmployeeBuilder;
import app.adapter.rest.request.EmployeeRequest;
import app.adapter.rest.response.EmployeeResponse;
import app.domain.model.Employee;
import app.domain.model.emuns.Role;

@Component
public class EmployeeRestMapper {

    @Autowired
    private EmployeeBuilder employeeBuilder;

    public Employee toDomain(EmployeeRequest req) throws Exception {
        return employeeBuilder.build(
             req.getUserName(),
            req.getPassword(),
            req.getFirstName(),
            req.getLastName(),
            req.getEmail(),
            req.getDocumentId(),
            req.getBirthDate(),
            req.getGender(),
            req.getAddress(),
            req.getPhoneNumber(),
            null
        );
    }

    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse res = new EmployeeResponse();
        res.setId(employee.getId());
        res.setUserName(employee.getUserName());
        res.setFirstName(employee.getFirstName());
        res.setLastName(employee.getLastName());
        res.setEmail(employee.getEmail());
        res.setDocumentId(employee.getDocumentId());
        res.setBirthDate(employee.getBirthDate());
        res.setGender(employee.getGender());
        res.setAddress(employee.getAddress());
        res.setPhoneNumber(employee.getPhoneNumber());
        res.setRole(employee.getRole() != null ? employee.getRole().name() : null);
        return res;
    }
}