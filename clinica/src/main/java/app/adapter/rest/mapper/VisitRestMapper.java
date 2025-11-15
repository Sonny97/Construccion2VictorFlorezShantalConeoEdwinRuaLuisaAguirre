package app.adapter.rest.mapper;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.adapter.in.builder.VisitBuilder;
import app.adapter.in.rest.request.CreateVisitRequest;
import app.adapter.rest.response.VisitResponse;
import app.domain.model.Visit;
=======
import org.springframework.stereotype.Component;
import app.adapter.in.rest.request.VisitRequest;
import app.adapter.rest.response.VisitResponse;
import app.domain.model.Visit;
import java.util.Date;
>>>>>>> ecabbdda117d0ae81b100168f1a84e192646d5f1

@Component
public class VisitRestMapper {

<<<<<<< HEAD
    @Autowired
    private VisitBuilder visitBuilder;

    public Visit toDomain(CreateVisitRequest req) throws Exception {
        return visitBuilder.build(
            req.getPatientId(),
            req.getNurseId(),
            req.getDate(),
            req.getReason(),
            req.getObservations()
            
        );
    }

    public VisitResponse toResponse(Visit visit) {
        VisitResponse res = new VisitResponse();
        res.setId(visit.getId());
        res.setPatientId(visit.getPatientId());
        res.setNurseId(visit.getNurseId());
        res.setDate(visit.getDate() != null ? visit.getDate().toString() : null);
        res.setReason(visit.getReason());
        res.setObservations(visit.getObservations());
        return res;
    }
}
=======
    public Visit toDomain(VisitRequest request) {
        if (request == null) {
            return null;
        }
        
        // 🔥 CREAR VISIT DIRECTAMENTE - más simple sin builder
        Visit visit = new Visit();
        visit.setPatientId(request.getPatientId());
        visit.setNurseId(request.getNurseId());
        visit.setBloodPressure(request.getBloodPressure());
        visit.setTemperature(request.getTemperature() != null ? request.getTemperature() : 0.0);
        visit.setPulse(request.getPulse() != null ? request.getPulse() : 0);
        visit.setRespiratoryRate(request.getRespiratoryRate() != null ? request.getRespiratoryRate() : 0);
        visit.setOxygenLevel(request.getOxygenLevel() != null ? request.getOxygenLevel() : 0.0);
        visit.setMedications(request.getMedications());
        visit.setProcedures(request.getProcedures());
        visit.setObservations(request.getObservations());
        visit.setVisitDate(new Date()); // Fecha actual automáticamente
        
        System.out.println("🔨 VisitRestMapper - Created visit for patient: " + request.getPatientId());
        return visit;
    }

    public VisitResponse toResponse(Visit visit) {
        if (visit == null) {
            return null;
        }
        
        VisitResponse response = new VisitResponse();
        response.setId((long) visit.getId());
        response.setPatientId(visit.getPatientId());
        response.setNurseId(visit.getNurseId());
        response.setVisitDate(visit.getVisitDate());  // ← Ahora funciona
        response.setBloodPressure(visit.getBloodPressure());
        response.setTemperature(visit.getTemperature());
        response.setPulse(visit.getPulse());
        response.setRespiratoryRate(visit.getRespiratoryRate());
        response.setOxygenLevel(visit.getOxygenLevel());
        response.setMedications(visit.getMedications());
        response.setProcedures(visit.getProcedures());
        response.setObservations(visit.getObservations());
        
        return response;
    }
}
>>>>>>> ecabbdda117d0ae81b100168f1a84e192646d5f1
