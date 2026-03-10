package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.PaymentVoucher;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment;
        if (method.equals(PaymentMethod.VOUCHER.getValue())){
            payment = createPaymentVoucher(order, method, paymentData);
        }else{
            payment = new Payment(order, method, paymentData);
        }
        return paymentRepository.save(payment);
    }

    public Payment createPaymentVoucher(Order order, String method, Map<String, String>paymentData){
        return new PaymentVoucher(order,method,paymentData);
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.getAllPayments();
    }

    @Override
    public void setStatus(Payment payment, String status) {
        payment.setStatus(status);
        if (payment.getStatus().equals(PaymentStatus.SUCCESS.getValue())){
            payment.getOrder().setStatus(OrderStatus.SUCCESS.getValue());
        }else if(payment.getStatus().equals(PaymentStatus.REJECTED.getValue())){
            payment.getOrder().setStatus(OrderStatus.FAILED.getValue());
        }
    }

    @Override
    public Payment getPayment(String id) {
        return paymentRepository.findById(id);
    }
}