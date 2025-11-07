package app.domain.services;

import app.domain.ports.UserPort;
import app.domain.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUsers {

    @Autowired
    private UserPort userPort;
    
    public void deleteByUsername(String username) throws Exception {
        Employee employee = new Employee();
        employee.setUserName(username);
        
        if (userPort.findByUserName(employee) == null) {
            throw new Exception("Usuario no encontrado, revisa el dato que digitaste: " + username);
        }
        userPort.delete(employee);
    }
}
