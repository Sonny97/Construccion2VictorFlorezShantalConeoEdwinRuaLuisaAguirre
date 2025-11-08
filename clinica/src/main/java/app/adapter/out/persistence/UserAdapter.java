package app.adapter.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.ports.UserPort;
import app.infrastructure.persistence.entities.UserEntity;
import app.infrastructure.persistence.mapper.UserMapper;
import app.infrastructure.persistence.repository.UserRepository;

@Service
public class UserAdapter implements UserPort {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Employee findByDocument(Long documentId) throws Exception {
		UserEntity userEntity = userRepository.findByDocumentId(documentId);
		return userEntity != null ? UserMapper.toDomain(userEntity) : null;
	}

	@Override
	public Employee findByUserName(String userName) throws Exception { // Cambiado a String
		UserEntity userEntity = userRepository.findByUserName(userName);
		return userEntity != null ? UserMapper.toDomain(userEntity) : null;
	}

	@Override
	public Employee findById(Long id) throws Exception {
		UserEntity userEntity = userRepository.findById(id).orElse(null);
		return userEntity != null ? UserMapper.toDomain(userEntity) : null;
	}

	@Override
	public void save(Employee employee) throws Exception {
		userRepository.save(UserMapper.toEntity(employee));
	}

	@Override
	public void deleteById(Long id) throws Exception {
		System.out.println("🗑️ UserAdapter - DELETE by ID: " + id);

		if (id == null) {
			throw new Exception("ID cannot be null for deletion");
		}

		// Verificar que el usuario existe antes de eliminar
		if (!userRepository.existsById(id)) {
			throw new Exception("Usuario no encontrado con ID: " + id);
		}

		userRepository.deleteById(id);
		System.out.println("✅ UserAdapter - Delete completed for ID: " + id);
	}

	@Override
	public void delete(Employee employee) throws Exception {
		userRepository.delete(UserMapper.toEntity(employee));
	}

	// Método adicional para compatibilidad si lo necesitas temporalmente
	public Employee findByUserName(Employee employee) throws Exception {
		if (employee == null || employee.getUserName() == null) {
			return null;
		}
		return findByUserName(employee.getUserName());
	}
}