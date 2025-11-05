package app.domain.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.application.exceptions.BusinessException;



import app.domain.model.Employee;

import app.domain.ports.UserPort;


public class CreateUsers {
    
    @Autowired
    private UserPort userPort;

    public void create(Employee employee) throws Exception {
        if (userPort.findByDocument(employee) != null) {
			throw new Exception("ya existe una persona registrada con esa cedula");
		}

		if (userPort.findByUserName(employee) != null) {
			throw new Exception("ya existe una persona registrada con ese nombre de usuario");
		}
        userPort.save(employee);
    }
}
