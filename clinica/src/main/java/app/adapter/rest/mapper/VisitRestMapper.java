package app.adapter.rest.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.adapter.in.builder.VisitBuilder;
import app.adapter.in.rest.request.CreateVisitRequest;
import app.adapter.rest.response.VisitResponse;
import app.domain.model.Visit;

@Component
public class VisitRestMapper {

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
