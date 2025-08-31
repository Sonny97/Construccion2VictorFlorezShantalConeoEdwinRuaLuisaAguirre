package app.domain.services;

import app.domain.ports.PatientPort;
import app.domain.model.Patient;

public class UpdatePatient {
    private PatientPort patientPort;

    public void update(Patient patient) throws Exception {
        if (patientPort.findById(patient.getId()) == null) {
			throw new Exception("Paciente no encontrado");
		}
        
        patientPort.save(patient);
    }
}
