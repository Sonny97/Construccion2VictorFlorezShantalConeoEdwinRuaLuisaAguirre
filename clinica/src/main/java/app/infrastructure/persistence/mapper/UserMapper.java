package app.infrastructure.persistence.mapper;

import app.domain.model.Employee;
import app.domain.model.emuns.Role;
import app.infrastructure.persistence.entities.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(Employee employee) {
        if (employee == null) return null;
        UserEntity entity = new UserEntity();//
        //entity.setId(employee.getId());
        entity.setRole(employee.getRole().name());
        entity.setUserName(employee.getUserName());
        entity.setPassword(employee.getPassword());
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setDocumentId(employee.getDocumentId());
        entity.setEmail(employee.getEmail());
        entity.setBirthDate(employee.getBirthDate());
        entity.setGender(employee.getGender());
        entity.setAddress(employee.getAddress());
        entity.setPhoneNumber(employee.getPhoneNumber());        
        return entity;
    }

    public static Employee toDomain(UserEntity entity) {
        if (entity == null) return null;
        Employee employee = new Employee();
        employee.setId(entity.getId());
        employee.setRole(parseRole(entity.getRole()));
        employee.setUserName(entity.getUserName());
        employee.setPassword(entity.getPassword());
        employee.setFirstName(entity.getFirstName());
        employee.setLastName(entity.getLastName());
        employee.setDocumentId(entity.getDocumentId());
        employee.setEmail(entity.getEmail());
        employee.setBirthDate(entity.getBirthDate());
        employee.setGender(entity.getGender());
        employee.setAddress(entity.getAddress());
        employee.setPhoneNumber(entity.getPhoneNumber());

        return employee;
    }

    private static Role parseRole(String role) {
        if (role == null) return null;
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
    

