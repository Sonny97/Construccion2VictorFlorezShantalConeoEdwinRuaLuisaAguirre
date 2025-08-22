package app.domain.services;

import app.domain.ports.UserPort;
import app.domain.model.User;

public class DeleteUsers {

    private UserPort userPort;
    
    public void delete(User user) throws Exception {
		if (userPort.findByUserName(user) == null) {
			throw new Exception("usuario no encontrado, revisa el dato que digitaste en el usuario");
		}
        userPort.delete(user);
    }
}
