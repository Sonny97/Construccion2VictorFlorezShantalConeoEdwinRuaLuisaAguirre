package app.domain.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.application.exceptions.BusinessException;



import app.domain.model.Employee;

import app.domain.ports.UserPort;

@Service
public class CreateUsers {
    
    @Autowired
    private UserPort userPort;

    public void create(Employee employee) throws Exception {
    if (userPort.findByDocument(employee.getDocumentId()) != null) {
        throw new Exception("Ya existe una persona registrada con esa cédula: " + employee.getDocumentId());
    }

    if (userPort.findByUserName(employee.getUserName()) != null) {
        throw new Exception("Ya existe una persona registrada con ese nombre de usuario: " + employee.getUserName());
    }
    
    userPort.save(employee);
}
}
