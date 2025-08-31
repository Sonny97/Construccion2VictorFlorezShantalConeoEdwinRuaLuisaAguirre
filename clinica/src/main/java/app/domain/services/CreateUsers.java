package app.domain.services;

import app.domain.ports.UserPort;
import app.domain.model.User;

public class CreateUsers {

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

// CASOS DE USO
// PERSONAL ADMINISTRATIVO
// RECURSOS HUMANOS
// MEDICOS
// ENFERMERAS
