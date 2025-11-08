package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.UserEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByDocumentId(Long documentId);

	public UserEntity findByUserName(String userName);
    List<UserEntity> findAll();
    

      // Este método ya existe por JpaRepository, pero lo declaramos para claridad
    void deleteById(Long id);
    
    // Método adicional si quieres eliminar por username
    void deleteByUserName(String userName);
}
