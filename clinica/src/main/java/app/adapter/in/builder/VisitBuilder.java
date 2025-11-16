// package app.adapter.in.builder;

// import app.domain.model.Visit;
// import org.springframework.stereotype.Component;
// import java.util.Date;

// @Component
// public class VisitBuilder {

//     // 🔥 MÉTODO PRINCIPAL que usa el controller
//     public Visit build(Long patientId, Long nurseId, String bloodPressure,
//                       Double temperature, Integer pulse, Double oxygenLevel,
//                       String medications, String procedures, String observations) {
        
//         Visit visit = new Visit();
//         visit.setPatientId(patientId);
//         visit.setNurseId(nurseId);
//         visit.setVisitDate(new Date()); // Fecha actual automática
//         visit.setBloodPressure(bloodPressure);
//         visit.setTemperature(temperature != null ? temperature : 0.0);
//         visit.setPulse(pulse != null ? pulse : 0);
//         visit.setOxygenLevel(oxygenLevel != null ? oxygenLevel : 0.0);
//         visit.setMedications(medications);
//         visit.setProcedures(procedures);
//         visit.setObservations(observations);
        
//         System.out.println("🔨 VisitBuilder - Built visit for patient: " + patientId);
//         return visit;
//     }

//     // 🔥 MÉTODO ALTERNATIVO con tipos primitivos (para compatibilidad)
//     public Visit build(long patientId, long nurseId, String bloodPressure,
//                     //   double temperature, int pulse, double oxygenLevel,
//                       String medications, String procedures, String observations) {
        
//         return build((Long) patientId, (Long) nurseId, bloodPressure, 
//                     temperature, pulse, oxygenLevel, medications, procedures, observations);
//     }
// }