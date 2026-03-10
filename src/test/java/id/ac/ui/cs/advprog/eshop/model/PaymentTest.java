package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Payment> payments;

    private List<Order> orders;

    private List<Product> products;

    @BeforeEach
    void setUp(){
        this.products = new ArrayList<>();
        this.orders =  new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductQuantity(1);
        product2.setProductName("Sampo Cap Usep");
        this.products.add(product1);
        this.products.add(product2);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products,
                1708560000L, "Safira Sudarajat");

        Order order2 = new Order("12938476-011a-4c06-b534-44eb1396d69b", this.products,
                1808680000L, "Anton Sutanto");
        orders.add(order1);
        orders.add(order2);
    }


    @Test
    void testCreatePaymentVoucherFail8Numerical(){
        Map<String, String> paymentDataVoucher = new  HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP0000000baba");
        assertThrows(IllegalArgumentException.class, ()-> {
            new Payment("a3e3e3e3-9a7f-4603-92c2-eaf529271cc9",orders.get(1),
                    "VOUCHER", paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentVoucherFail16(){
        Map<String, String> paymentData = new  HashMap<>();
        paymentData.put("voucherCode", "ESHOP0000000000");
        assertThrows(IllegalArgumentException.class, ()-> {new
                Payment("a3e3e3e3-9a7f-4603-92c2-eaf529271cc9",orders.get(1),
                "VOUCHER", paymentData);
        });
    }

    @Test
    void testCreatePaymentVoucherFailESHOPStart(){
        Map<String, String> paymentDataVoucher = new  HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHO000000000000");
        assertThrows(IllegalArgumentException.class, ()-> {
            new Payment("a3e3e3e3-9a7f-4603-92c2-eaf529271cc9",orders.get(1),
                    "VOUCHER", paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentFailBankName(){
        Map<String, String> paymentDataBank = new HashMap<>();
        paymentDataBank.put("bankName", "");
        paymentDataBank.put("referenceCode", "0");
        assertThrows(IllegalArgumentException.class, ()-> {
            new Payment("a3e3e3e3-9a7f-4603-92c2-eaf529271cc9",orders.get(1),
                    "BANK", paymentDataBank);
        });
    }

    @Test
    void testCreatePaymentFailReferenceCode(){
        Map<String, String> paymentDataBank = new  HashMap<>();
        paymentDataBank.put("bankName", "BCA");
        paymentDataBank.put("referenceCode", "");
        assertThrows(IllegalArgumentException.class, ()-> {
            new Payment("a3e3e3e3-9a7f-4603-92c2-eaf529271cc9",orders.get(1),
                    "BANK", paymentDataBank);
        });
    }

    @Test
    void testCreatePaymentSuccessVoucher(){
        Map<String, String> paymentData= new  HashMap<>();
        paymentData.put("voucherCode", "ESHOP00000000AAA");
        Payment payment1 = new Payment("a3e3e3e3-9a7f-4603-92c2-eaf529271cc9",orders.get(1),
                "VOUCHER", paymentData);
        assertSame(this.orders.get(1), payment1.getOrder());
        assertEquals(paymentData, payment1.getPaymentData());
        assertEquals("a3e3e3e3-9a7f-4603-92c2-eaf529271cc9", payment1.getId());
        assertEquals("VOUCHER", payment1.getMethod());
    }
}