package app.infrastructure.persistence.mapper;

import app.domain.model.User;
import app.domain.model.emuns.Role;
import app.infrastructure.persistence.entities.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(User user) {
        if (user == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setRole(user.getRole().name());
        entity.setUserName(user.getUserName());
        entity.setPassword(user.getPassword());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setDocumentId(user.getDocumentId());
        entity.setEmail(user.getEmail());
        entity.setBirthDate(user.getBirthDate());
        entity.setGender(user.getGender());
        entity.setAddress(user.getAddress());
        entity.setPhoneNumber(Integer.parseInt(user.getPhoneNumber()));        
        return entity;
    }

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;
        User user = new User();
        user.setId(entity.getId());
        user.setRole(Role.valueOf(entity.getRole()));
        user.setUserName(entity.getUserName());
        user.setPassword(entity.getPassword());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setDocumentId(entity.getDocumentId());
        user.setEmail(entity.getEmail());
        user.setBirthDate(entity.getBirthDate());
        user.setGender(entity.getGender());
        user.setAddress(entity.getAddress());
        user.setPhoneNumber(entity.getPhoneNumber());

        return user;
    }
    
}
