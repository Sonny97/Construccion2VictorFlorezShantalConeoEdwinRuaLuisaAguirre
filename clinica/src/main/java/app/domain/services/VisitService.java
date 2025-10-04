package app.domain.services;

import app.domain.model.Visit;
import java.util.ArrayList;
import java.util.List;

public class VisitService {

    public List<Visit> getVisitsByPatientId(Long patientId) {
    List<Visit> resultado = new ArrayList<>();
    for (Visit visit : visits) {
        if (visit.getPatientId() == patientId) {
            resultado.add(visit);
        }
    }
    return resultado;
}
    
    private List<Visit> visits = new ArrayList<>();
    private int nextId = 1;

    public Visit registerVisit(long patientId, long nurseId, String bloodPressure,
                              double temperature, int pulse, double oxygenLevel,
                              String medications, String procedures, String observations) {

        Visit visit = new Visit();
        visit.setId(nextId++);
        visit.setPatientId(patientId);
        visit.setNurseId(nurseId);
        visit.setBloodPressure(bloodPressure);
        visit.setTemperature(temperature);
        visit.setPulse(pulse);
        visit.setOxygenLevel(oxygenLevel);
        visit.setMedications(medications);
        visit.setProcedures(procedures);
        visit.setObservations(observations);

        visits.add(visit);
        return visit;
    }
}
