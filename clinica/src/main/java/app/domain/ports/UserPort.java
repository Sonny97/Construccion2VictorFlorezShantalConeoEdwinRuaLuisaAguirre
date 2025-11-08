package app.domain.ports;

import app.domain.model.Employee;

public interface UserPort {
    Employee findByDocument(Long documentId) throws Exception;
    Employee findByUserName(String userName) throws Exception; // Cambiado a String
    Employee findById(Long id) throws Exception;
    void save(Employee employee) throws Exception;
	void deleteById(Long id) throws Exception;
    void delete(Employee employee) throws Exception;
}