package app.domain.ports;

public interface PatientPort {
	public User findById(User user) throws Exception;
	public User findByIdNumber(User user) throws Exception;
	public void save(User user) throws Exception;
    public void update(Patient patient) throws Exception;
}
