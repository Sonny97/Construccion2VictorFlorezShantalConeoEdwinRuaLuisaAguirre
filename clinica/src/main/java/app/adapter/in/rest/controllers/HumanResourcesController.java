package app.adapter.in.rest.controllers;

import app.adapter.rest.mapper.EmployeeRestMapper;
import app.adapter.rest.request.EmployeeRequest;
import app.adapter.rest.response.EmployeeResponse;
import app.application.useCase.HumanResourcesUseCase;
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
        Employee employee = employeeRestMapper.toDomain(request);
        humanResourcesUseCase.createMedic(employee);
        return new ResponseEntity<>(employeeRestMapper.toResponse(employee), HttpStatus.CREATED);
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
    public ResponseEntity<EmployeeResponse> createAdministrative(@RequestBody EmployeeRequest request) throws Exception {
        Employee employee = employeeRestMapper.toDomain(request);
        humanResourcesUseCase.createAdministrative(employee);
        return new ResponseEntity<>(employeeRestMapper.toResponse(employee), HttpStatus.CREATED);
    }

    // CREAR USUARIO DE RH
    @PostMapping("/human-resources")
    public ResponseEntity<EmployeeResponse> createHumanResources(@RequestBody EmployeeRequest request) throws Exception {
        Employee employee = employeeRestMapper.toDomain(request);
        humanResourcesUseCase.createHumanResources(employee);
        return new ResponseEntity<>(employeeRestMapper.toResponse(employee), HttpStatus.CREATED);
    }

    // ELIMINAR USUARIO
    @DeleteMapping("/employees/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) throws Exception {
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