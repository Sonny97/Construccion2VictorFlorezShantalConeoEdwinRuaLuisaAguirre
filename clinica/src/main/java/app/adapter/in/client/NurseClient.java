package app.adapter.in.client;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import app.domain.model.Patient;
import app.domain.model.Visit;


import app.adapter.in.builder.UserBuilder;
import app.application.useCase.PatientUseCase;

public class NurseClient {
    private static Scanner scanner = new Scanner(System.in);

    @Autowired
    private PatientUseCase patientUseCase;

    @Autowired
    // private UserBuilder userBuilder;


    public void session() {
        boolean inSession = true;

        while (inSession) {
            System.out.println("\n📋 Menú de Enfermería");
            System.out.println("1. Registro de visitas de pacientes");
            System.out.println("2. Historial de visitas de pacientes");
            System.out.println("3. Salir del sistema");
            System.out.print("Seleccione una opción: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> registerPatientVisit();
                case "2" -> viewVisitHistory();
                case "3" -> {
                    System.out.println("Cerrando sesión de Enfermera...");
                    inSession = false;
                    break;
                }
                
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void registerPatientVisit() {
        System.out.print("Ingrese ID del paciente: ");
        Long patientId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Ingrese ID de la enfermera: ");
        Long nurseId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("📊 Registro de signos vitales:");
        System.out.print("Presión arterial: ");
        String bloodPressure = scanner.nextLine();
        System.out.print("Temperatura: ");
        double temperature = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Frecuencia cardíaca: ");
        int heartRate = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Frecuencia respiratoria: ");
        int respiratoryRate = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Saturación de oxígeno: ");
        double oxygenSaturation = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("💊 Medicamentos administrados:");
        System.out.print("Ingrese medicamentos (separados por coma): ");
        String medications = scanner.nextLine();

        System.out.println("🩺 Procedimientos realizados:");
        System.out.print("Ingrese procedimientos (separados por coma): ");
        String procedures = scanner.nextLine();

        System.out.println("📝 Observaciones relevantes:");
        System.out.print("Ingrese observaciones: ");
        String observations = scanner.nextLine();

        Visit visit = visitService.registerVisit(patientId, nurseId, bloodPressure,
                temperature, heartRate, respiratoryRate, oxygenSaturation,
                medications, procedures, observations);

        if (visit != null) {
            System.out.println("✅ Visita registrada con ID: " + visit.getId());
        } else {
            System.out.println("❌ Error al registrar la visita. Verifique los IDs.");
        }
    }


    private void viewVisitHistory() {
        System.out.print("Ingrese Cedula del paciente: ");
        Long patientId = scanner.nextLong();
        scanner.nextLine();

        List<Visit> visits = visitService.getVisitsByPatientId(patientId);
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            System.out.println("❌ Paciente no encontrado.");
            return;
        }

        System.out.println("📋 Historial de visitas de: " + patient.getFirstName() + " " + patient.getLastName());

        for (Visit v : visits) {
            System.out.println("──────────────────────────────");
            System.out.println("Fecha: " + v.getVisitDate());
            System.out.println("Enfermera: " + v.getNurse().getFirstName() + " " + v.getNurse().getLastName());
            System.out.println("Presión arterial: " + v.getBloodPressure());
            System.out.println("Temperatura: " + v.getTemperature());
            System.out.println("Frecuencia cardíaca: " + v.getHeartRate());
            System.out.println("Frecuencia respiratoria: " + v.getRespiratoryRate());
            System.out.println("Saturación de oxígeno: " + v.getOxygenSaturation());
            System.out.println("Medicamentos: " + v.getMedications());
            System.out.println("Procedimientos: " + v.getProcedures());
            System.out.println("Observaciones: " + v.getObservations());
        }

        if (visits.isEmpty()) {
            System.out.println("⚠️ No hay visitas registradas para este paciente.");
        }
    }
}
