package app.adapter.in.builder;

import app.domain.model.Visit;
import java.time.LocalDate;

public class VisitBuilder {

    public Visit build(long patientId, long nurseId, LocalDate date, String reason, String observations) {
        Visit visit = new Visit();
        visit.setPatientId(patientId);
        visit.setNurseId(nurseId);
        visit.setDate(date);
        visit.setReason(reason);
        visit.setObservations(observations);
        return visit;
    }

} 

