package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.infrastructure.persistence.mapper.UserMapper;
import app.domain.model.User;
import app.infrastructure.persistence.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    // ❌ Ya no necesitamos @Autowired en UserMapper porque es estático

    public User authenticate(String username, String password) throws Exception {
        var userEntity = userRepository.findByUserName(username);
        
        if (userEntity != null && userEntity.getPassword().equals(password)) {
            return UserMapper.toDomain(userEntity); // ✅ Llamada estática
        }
        return null;
    }
}