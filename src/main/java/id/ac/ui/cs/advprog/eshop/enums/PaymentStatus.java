package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    SUCCESS("SUCCESS"),
    REJECTED("REJECTED"),
    WAITING_PAYMENT("WAITING_PAYMENT");
    private final String value;

    private PaymentStatus(String value){
        this.value = value;
    }
}