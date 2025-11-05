package app.adapter.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.ports.UserPort;
import app.infrastructure.persistence.entities.UserEntity;
import app.infrastructure.persistence.mapper.UserMapper;
import app.infrastructure.persistence.repository.UserRepository;

@Service
public class UserAdapter implements UserPort{

    
	@Autowired
	private UserRepository userRepository;

    @Override
	public Employee findByDocument(Employee employee) throws Exception {
		UserEntity userEntity = userRepository.findByDocument(employee.getDocumentId());
		return UserMapper.toDomain(userEntity);
	}

	@Override
	public Employee findByUserName(Employee employee) throws Exception {
		UserEntity userEntity = userRepository.findByUserName(employee.getUserName());
		return UserMapper.toDomain(userEntity);
	}

	@Override
	public void save(Employee employee) throws Exception {
		userRepository.save(UserMapper.toEntity(employee));
	}
    @Override
	public void delete(Employee employee) throws Exception {
		userRepository.delete(UserMapper.toEntity(employee));
	}

}
