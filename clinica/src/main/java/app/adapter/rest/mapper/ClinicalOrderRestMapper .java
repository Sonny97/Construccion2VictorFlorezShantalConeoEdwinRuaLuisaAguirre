package app.adapter.rest.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.domain.model.ClinicalOrder;
import app.adapter.in.rest.request.CreateClinicalOrderRequest; 
import app.adapter.rest.response.ClinicalOrderResponse; 

@Component
public class ClinicalOrderRestMapper {

    @Autowired
    private ClinicalOrderBuilder clinicalOrderBuilder;

    public ClinicalOrder toDomain(CreateClinicalOrderRequest req) throws Exception {
        return clinicalOrderBuilder.build(
            req.getId(),
            req.getPatientId(),
            req.getDoctorId(),
            req.getDate(),
            req.getOrderType(),
            req.getOrderDescription()
        );
    }

    public ClinicalOrderResponse toResponse(ClinicalOrder order) {
        ClinicalOrderResponse res = new ClinicalOrderResponse();
        res.setId(order.getId());
        res.setPatientId(order.getPatientId());
        res.setDoctorId(order.getDoctorId());
        res.setDate(order.getDate() != null ? order.getDate().toString() : null);
        res.setOrderType(order.getOrderType());
        res.setOrderDescription(order.getOrderDescription());
        return res;
    }
}

