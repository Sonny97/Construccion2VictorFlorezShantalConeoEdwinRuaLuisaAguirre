package app.adapter.in.rest.controllers;

import app.adapter.rest.mapper.EmployeeRestMapper;
import app.adapter.rest.request.EmployeeRequest;
import app.adapter.rest.response.EmployeeResponse;
import app.application.usecase.HumanResourcesUseCase;
import app.domain.model.Employee;
import app.domain.model.emuns.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rh")
@PreAuthorize("hasRole('HUMAN_RESOURCES')")
public class HumanResourcesController {

    @Autowired
    private HumanResourcesUseCase humanResourcesUseCase;

    @Autowired
    private EmployeeRestMapper employeeRestMapper;

    

    // CREAR MÉDICO
    @PostMapping("/medics")
    public ResponseEntity<EmployeeResponse> createMedic(@RequestBody EmployeeRequest request) throws Exception {
        System.out.println("🎯 ENTRY POINT HIT - CREATE MEDIC");

        try {
            System.out.println("📦 Request received - Username: " + request.getUserName());

            Employee employee = employeeRestMapper.toDomain(request);
            humanResourcesUseCase.createMedic(employee);

            System.out.println("✅ SUCCESS - Medic created");
            return new ResponseEntity<>(employeeRestMapper.toResponse(employee), HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // CREAR ENFERMERA
    @PostMapping("/nurses")
    public ResponseEntity<EmployeeResponse> createNurse(@RequestBody EmployeeRequest request) throws Exception {
        Employee employee = employeeRestMapper.toDomain(request);
        humanResourcesUseCase.createNurse(employee);
        return new ResponseEntity<>(employeeRestMapper.toResponse(employee), HttpStatus.CREATED);
    }

    // CREAR ADMINISTRATIVO
    @PostMapping("/administrative")
    public ResponseEntity<EmployeeResponse> createAdministrative(@RequestBody EmployeeRequest request)
            throws Exception {
        Employee employee = employeeRestMapper.toDomain(request);
        humanResourcesUseCase.createAdministrative(employee);
        return new ResponseEntity<>(employeeRestMapper.toResponse(employee), HttpStatus.CREATED);
    }

    // CREAR USUARIO DE RH
    @PostMapping("/human-resources")
    public ResponseEntity<EmployeeResponse> createHumanResources(@RequestBody EmployeeRequest request)
            throws Exception {
        Employee employee = employeeRestMapper.toDomain(request);
        humanResourcesUseCase.createHumanResources(employee);
        return new ResponseEntity<>(employeeRestMapper.toResponse(employee), HttpStatus.CREATED);
    }

    // ELIMINAR USUARIO POR ID - path más específico
    @DeleteMapping("/employees/id/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) throws Exception {
        System.out.println("🎯 DELETE by ID endpoint hit - ID: " + id);
        humanResourcesUseCase.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    // ELIMINAR USUARIO POR USERNAME - path más específico
    @DeleteMapping("/employees/username/{username}")
    @ResponseBody
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) throws Exception {
        System.out.println("🎯 DELETE by username endpoint hit - Username: " + username);
        humanResourcesUseCase.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    // LISTAR TODOS LOS EMPLEADOS
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponse>> listAllEmployees() throws Exception {
        List<Employee> employees = humanResourcesUseCase.listAllEmployees();
        List<EmployeeResponse> response = employees.stream()
                .map(employeeRestMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

}