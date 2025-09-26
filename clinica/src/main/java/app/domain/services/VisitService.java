package app.domain.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Visit;

@Service
public class VisitService {
    public Visit registerVisit(Long patientId, Long nurseId, String bloodPressure, 
                              Double temperature, Integer heartRate, Integer respiratoryRate, 
                              Double oxygenSaturation, String medications, String procedures, 
                              String observations) {
        Patient patient = patientService.getPatientById(patientId);
        User nurse = userService.getUserById(nurseId);
        
        if (patient == null || nurse == null) {
            return null;
        }
        
        Visit visit = new Visit();
        visit.setId(nextId++);
        visit.setPatient(patient);
        visit.setNurse(nurse);
        visit.setBloodPressure(bloodPressure);
        visit.setTemperature(temperature);
        visit.setHeartRate(heartRate);
        visit.setRespiratoryRate(respiratoryRate);
        visit.setOxygenSaturation(oxygenSaturation);
        visit.setMedications(medications);
        visit.setProcedures(procedures);
        visit.setObservations(observations);
        
        visits.add(visit);
        return visit;
    }
}
