package app.domain.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.application.exceptions.BusinessException;



import app.domain.model.User;

import app.domain.ports.UserPort;


public class CreateUsers {
    
    @Autowired
    private UserPort userPort;

    public void create(User user) throws Exception {
        if (userPort.findByDocument(user) != null) {
			throw new Exception("ya existe una persona registrada con esa cedula");
		}

		if (userPort.findByUserName(user) != null) {
			throw new Exception("ya existe una persona registrada con ese nombre de usuario");
		}
        userPort.save(user);
    }
}
