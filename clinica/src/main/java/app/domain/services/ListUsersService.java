package app.domain.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import app.infrastructure.persistence.entities.UserEntity;
import app.infrastructure.persistence.mapper.UserMapper;
import app.infrastructure.persistence.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import app.infrastructure.persistence.mapper.UserMapper;
import app.domain.model.User;

public class ListUsersService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        
        return userEntities.stream()
                .map(UserMapper::toDomain) 
                .collect(Collectors.toList());
    }
}
