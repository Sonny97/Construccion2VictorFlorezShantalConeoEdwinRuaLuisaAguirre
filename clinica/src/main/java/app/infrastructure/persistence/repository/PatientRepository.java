package app.infrastructure.persistence.repository;


import app.infrastructure.persistence.entities.UserEntity;
import app.infrastructure.persistence.entities.EnfermeraEntity;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<EnfermeraEntity, Long> {
    public EnfermeraEntity findByDocumentId(long documentId);

	public EnfermeraEntity findByUserName(String userName);
    List<EnfermeraEntity> findAll();
}
