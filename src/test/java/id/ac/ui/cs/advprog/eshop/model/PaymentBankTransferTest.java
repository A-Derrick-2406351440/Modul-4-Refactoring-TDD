package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentBankTransferTest {
    List<Order> orders;
    List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        this.products.add(product1);

        this.orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Budi");
        orders.add(order1);
    }

    @Test
    void testCreatePaymentBankTransferSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "1234567890");

        Payment payment = new PaymentBankTransfer("payment-id-123", orders.get(0), "BANK", paymentData);

        assertEquals("payment-id-123", payment.getId());
        assertEquals("BANK", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransferFailBankNameEmpty() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", ""); // Kosong
        paymentData.put("referenceCode", "1234567890");

        Payment payment = new PaymentBankTransfer("payment-id-123", orders.get(0), "BANK", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransferFailReferenceCodeNull() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", null);

        Payment payment = new PaymentBankTransfer("payment-id-123", orders.get(0), "BANK", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}