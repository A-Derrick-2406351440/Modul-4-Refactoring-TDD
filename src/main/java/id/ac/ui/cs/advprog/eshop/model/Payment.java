package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id,  Order order, String method, Map<String, String>paymentData){
        this(order, method, paymentData);
        this.id = id;
    }

    public Payment(Order order, String method, Map<String, String>paymentData){
        this.id = UUID.randomUUID().toString();
        this.method = method;
        this.order = order;
        setPaymentData(paymentData);
    }

    private void setPaymentData(Map<String, String>paymentData){
        if (method.equals("VOUCHER")){
            int numOfNumerics = 0;
            for (int i=0; i<paymentData.get("voucherCode").length(); i++){
                if (Character.isDigit(paymentData.get("voucherCode").charAt(i))){
                    numOfNumerics+=1;
                }
            }
            if (paymentData.get("voucherCode").length()!=16 ||
                    !paymentData.get("voucherCode").startsWith("ESHOP") ||
                    numOfNumerics!=8){
                throw new IllegalArgumentException();
            }
        }else if (method.equals("BANK")){
            if (paymentData.get("bankName").isBlank() ||
                    paymentData.get("referenceCode").isBlank()){
                throw new IllegalArgumentException();
            }
        }
        this.paymentData = paymentData;
    }
}