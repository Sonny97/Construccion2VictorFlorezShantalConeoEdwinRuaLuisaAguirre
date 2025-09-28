public class NurseUseCase {
    private VisitService visitService;
    private VisitBuilder visitBuilder;

    public NurseUseCase(VisitService visitService, VisitBuilder visitBuilder) {
        this.visitService = visitService;
        this.visitBuilder = visitBuilder;
    }

    public Visit registerPatientVisit(long patientId, long nurseId, String bloodPressure,
                                     double temperature, int pulse, double oxygenLevel,
                                     String medications, String procedures, String observations) {
        Visit visit = visitBuilder.build(patientId, nurseId, bloodPressure, temperature, pulse,
                                         oxygenLevel, medications, procedures, observations);
        return visitService.registerVisit(visit.getPatientId(), visit.getNurseId(), visit.getBloodPressure(),
                                          visit.getTemperature(), visit.getPulse(), visit.getOxygenLevel(),
                                          visit.getMedications(), visit.getProcedures(), visit.getObservations());
    }
}
