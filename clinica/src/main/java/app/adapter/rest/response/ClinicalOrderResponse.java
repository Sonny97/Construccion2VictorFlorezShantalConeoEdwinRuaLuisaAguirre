package app.adapter.rest.response;

public class ClinicalOrderResponse {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private String date;
    private String orderType;
    private String orderDescription;

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public void setDate(String date) { this.date = date; }
    public void setOrderType(String orderType) { this.orderType = orderType; }
    public void setOrderDescription(String orderDescription) { this.orderDescription = orderDescription; }

    // GETTERS
    public Long getId() { return id; }
    public Long getPatientId() { return patientId; }
    public Long getDoctorId() { return doctorId; }
    public String getDate() { return date; }
    public String getOrderType() { return orderType; }
    public String getOrderDescription() { return orderDescription; }
}
<<<<<<< HEAD

=======
>>>>>>> ecabbdda117d0ae81b100168f1a84e192646d5f1
