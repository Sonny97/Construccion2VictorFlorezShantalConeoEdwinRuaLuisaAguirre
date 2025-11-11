package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    // Buscar paciente por número de documento
    PatientEntity findByDocumentId(Long documentId);

    // Buscar pacientes por nombre completo (coincidencia parcial)
    @Query("SELECT p FROM PatientEntity p WHERE LOWER(CONCAT(p.firstName, ' ', p.lastName)) LIKE LOWER(CONCAT('%', :fullName, '%'))")
    List<PatientEntity> findByFullNameContainingIgnoreCase(@Param("fullName") String fullName);

    // Verificar si existe un paciente con el documento
    boolean existsByDocumentId(Long documentId);
}