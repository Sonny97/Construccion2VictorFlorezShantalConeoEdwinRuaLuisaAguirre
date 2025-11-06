package app.application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.model.emuns.Role;
import app.domain.services.CreateUsers;
import app.domain.services.DeleteUsers;
import app.domain.services.ListUsersService;
import java.util.List;

@Service
public class HumanResourcesUseCase {
    
    @Autowired
    private CreateUsers createService;

    @Autowired
    private DeleteUsers deleteService;
    
    @Autowired
    private ListUsersService listService; 

    // CREAR EMPLEADO CON ROL ESPECÍFICO
    public void createEmployee(Employee employee, Role role) throws Exception {
        employee.setRole(role);
        createService.create(employee);
    }

    // CREAR MÉDICO
    public void createMedic(Employee employee) throws Exception {
        createEmployee(employee, Role.MEDIC);
    }

    // CREAR ENFERMERA
    public void createNurse(Employee employee) throws Exception {
        createEmployee(employee, Role.NURSE);
    }

    // CREAR ADMINISTRATIVO
    public void createAdministrative(Employee employee) throws Exception {
        createEmployee(employee, Role.ADMINISTRATIVE);
    }

    // CREAR USUARIO DE RH
    public void createHumanResources(Employee employee) throws Exception {
        createEmployee(employee, Role.HUMAN_RESOURCES);
    }

    public void deleteUser(String username) throws Exception {
        deleteService.deleteByUsername(username);
    }
    
    public List<Employee> listAllEmployees() throws Exception {
        return listService.listAllUsers();
    }
}