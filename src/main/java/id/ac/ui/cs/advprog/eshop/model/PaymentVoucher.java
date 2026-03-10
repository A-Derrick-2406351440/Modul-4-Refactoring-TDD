package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import java.util.Map;

public class PaymentVoucher extends Payment {
    public PaymentVoucher(Order order, String method, Map<String, String> paymentData) {
        super(order, method, paymentData);
    }

    public PaymentVoucher(String id, Order order, String method, Map<String, String> paymentData) {
        super(id, order, method, paymentData);
    }

    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        super.setPaymentData(paymentData);

        String voucherCode = paymentData.get("voucherCode");
        int numOfNumerics = 0;

        if (voucherCode != null) {
            for (int i = 0; i < voucherCode.length(); i++) {
                if (Character.isDigit(voucherCode.charAt(i))) {
                    numOfNumerics += 1;
                }
            }
        }

        if (voucherCode != null && voucherCode.length() == 16 &&
                voucherCode.startsWith("ESHOP") && numOfNumerics == 8) {
            this.status = PaymentStatus.SUCCESS.getValue();
        } else {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }
}