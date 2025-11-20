package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.UserEntity;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByDocumentId(Long documentId);

	public UserEntity findByUserName(String userName);
    List<UserEntity> findAll();
    void deleteById(Long id);
    void deleteByUserName(String userName);
    List<UserEntity> findByRole(String role); 
}
