package app.domain.ports;

import org.h2.engine.User;

import com.google.common.base.Optional;

 import app.domain.model.Patient;

public interface PatientPort {
	 public User findById(User user) throws Exception;
	 public User findByIdNumber(User user) throws Exception;
	//  public void save(User user) throws Exception;
     public void update(Patient patient) throws Exception;

	Optional<Patient> findById(long id);
    void save(Patient patient);
}
