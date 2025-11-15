package app.adapter.rest.mapper;

import app.adapter.rest.request.DoctorRequest;
import app.adapter.rest.response.DoctorResponse;
import app.domain.model.Doctor;
import app.domain.model.Employee;
import app.infrastructure.persistence.entities.DoctorEntity;
import app.infrastructure.persistence.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class DoctorRestMapper {

    public Doctor toDomain(DoctorRequest request, Employee employee) {
        if (request == null)
            return null;

        Doctor doctor = new Doctor();
        doctor.setEmployee(employee);
        doctor.setSpecialization(request.getSpecialization());
        doctor.setLicenseNumber(request.getLicenseNumber());
        return doctor;
    }

    public DoctorResponse toResponse(Doctor doctor) {
        if (doctor == null)
            return null;

        DoctorResponse response = new DoctorResponse();
        response.setId(doctor.getId());

        if (doctor.getEmployee() != null) {
            response.setFirstName(doctor.getEmployee().getFirstName());
            response.setLastName(doctor.getEmployee().getLastName());
            response.setEmail(doctor.getEmployee().getEmail());
            response.setPhoneNumber(doctor.getEmployee().getPhoneNumber());
            response.setFullName(doctor.getFullName());
        }

        response.setSpecialization(doctor.getSpecialization());
        response.setLicenseNumber(doctor.getLicenseNumber());
        return response;
    }

    public DoctorEntity toEntity(Doctor doctor, UserEntity userEntity) {
        if (doctor == null)
            return null;

        DoctorEntity entity = new DoctorEntity();
        entity.setId(doctor.getId());
        entity.setUser(userEntity);
        entity.setSpecialization(doctor.getSpecialization());
        entity.setLicenseNumber(doctor.getLicenseNumber());
        return entity;
    }

    public Doctor toDomain(DoctorEntity entity) {
        if (entity == null)
            return null;

        Doctor doctor = new Doctor();
        doctor.setId(entity.getId());
        doctor.setSpecialization(entity.getSpecialization());
        doctor.setLicenseNumber(entity.getLicenseNumber());

        // Convert UserEntity to Employee domain model
        if (entity.getUser() != null) {
            Employee employee = convertUserEntityToEmployee(entity.getUser());
            doctor.setEmployee(employee);
        }

        return doctor;
    }

    private Employee convertUserEntityToEmployee(UserEntity userEntity) {
        Employee employee = new Employee();
        employee.setId(userEntity.getId());
        employee.setUserName(userEntity.getUserName());
        employee.setPassword(userEntity.getPassword());
        employee.setFirstName(userEntity.getFirstName());
        employee.setLastName(userEntity.getLastName());
        employee.setEmail(userEntity.getEmail());
        employee.setDocumentId(userEntity.getDocumentId());
        employee.setBirthDate(userEntity.getBirthDate());
        employee.setGender(userEntity.getGender());
        employee.setAddress(userEntity.getAddress());
        employee.setPhoneNumber(userEntity.getPhoneNumber());

        // Convert String role to Enum Role - manejar 'MEDIC'
        if (userEntity.getRole() != null) {
            try {
                // Si el rol en BD es 'MEDIC', mapear a Role.MEDIC
                String roleFromDb = userEntity.getRole();
                if ("MEDIC".equals(roleFromDb)) {
                    employee.setRole(app.domain.model.emuns.Role.MEDIC);
                } else {
                    employee.setRole(app.domain.model.emuns.Role.valueOf(roleFromDb));
                }
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Unknown role: " + userEntity.getRole());
            }
        }

        return employee;
    }
}