package br.com.alura.mvc.mudi.dto;

import br.com.alura.mvc.mudi.model.Offer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewOfferRequest {
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Long orderId;
    
    @Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
    @NotNull 
    private String value;
    
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
    @NotNull
    private String deliveryDate;
    
    private String comment;

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Offer toOffer() {
        Offer offer = new Offer();
        offer.setComment(this.comment);
        offer.setDeliveryDate(LocalDate.parse(this.deliveryDate, formatter));
        offer.setValue(new BigDecimal(this.value));
        return offer;
    }
}
