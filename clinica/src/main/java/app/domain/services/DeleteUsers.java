package app.domain.services;

import app.domain.ports.UserPort;
import app.domain.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUsers {

    @Autowired
    private UserPort userPort;

    // Método principal - por ID (RECOMENDADO)
    public void deleteById(Long id) throws Exception {
        System.out.println("🗑️ DeleteUsers - ID: " + id);

        Employee employee = userPort.findById(id);
        System.out.println("🔍 Employee found: " + (employee != null ? employee.getUserName() : "NULL"));

        if (employee == null) {
            throw new Exception("Usuario no encontrado con ID: " + id);
        }

        userPort.deleteById(id);
        System.out.println("✅ Delete completed for ID: " + id);
    }

    // Método por username (alternativo, menos seguro)
    public void deleteByUsername(String username) throws Exception {
        System.out.println("🗑️ DeleteUsers - Username: " + username);

        Employee employee = userPort.findByUserName(username);
        System.out.println("🔍 Employee found: " + (employee != null ? "ID: " + employee.getId() : "NULL"));

        if (employee == null) {
            throw new Exception("Usuario no encontrado: " + username);
        }

        userPort.deleteById(employee.getId());
        System.out.println("✅ Delete completed for username: " + username);
    }
}
