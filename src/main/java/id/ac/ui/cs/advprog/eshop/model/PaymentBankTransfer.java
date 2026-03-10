package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import java.util.Map;

public class PaymentBankTransfer extends Payment {

    public PaymentBankTransfer(Order order, String method, Map<String, String> paymentData) {
        super(order, method, paymentData);
    }

    public PaymentBankTransfer(String id, Order order, String method, Map<String, String> paymentData) {
        super(id, order, method, paymentData);
    }

    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        super.setPaymentData(paymentData);

        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        if (bankName != null && !bankName.trim().isEmpty() &&
                referenceCode != null && !referenceCode.trim().isEmpty()) {
            this.status = PaymentStatus.SUCCESS.getValue();
        } else {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }
}