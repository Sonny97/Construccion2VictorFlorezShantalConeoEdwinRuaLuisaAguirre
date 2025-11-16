package app.domain.model;

import java.time.LocalDate;

public class ClinicalOrder {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDate date;
    private String orderType;
    private String orderDescription;

    // SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    // GETTERS
    public Long getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getOrderDescription() {
        return orderDescription;
    }
}