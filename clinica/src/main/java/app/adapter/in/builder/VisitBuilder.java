package app.adapter.in.builder;
import app.domain.model.Visit;

public class VisitBuilder {
    public Visit build(long patientId, long nurseId, String bloodPressure,
                      double temperature, int pulse, double oxygenLevel,
                      String medications, String procedures, String observations) {
        Visit visit = new Visit();
        visit.setPatientId(patientId);
        visit.setNurseId(nurseId);
        visit.setBloodPressure(bloodPressure);
        visit.setTemperature(temperature);
        visit.setPulse(pulse);
        visit.setOxygenLevel(oxygenLevel);
        visit.setMedications(medications);
        visit.setProcedures(procedures);
        visit.setObservations(observations);
        return visit;
    }
}

