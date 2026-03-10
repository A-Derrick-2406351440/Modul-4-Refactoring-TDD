package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private Model model;

    @InjectMocks
    private PaymentController paymentController;

    @Test
    void testPaymentDetailPage() {
        String viewName = paymentController.paymentDetailPage(model);
        assertEquals("paymentDetail", viewName);
    }

    @Test
    void testPaymentDetailByIdPage() {
        Payment payment = mock(Payment.class);
        when(paymentService.getPayment("123")).thenReturn(payment);
        String viewName = paymentController.paymentDetailByIdPage("123", model);
        assertEquals("paymentDetail", viewName);
        verify(model).addAttribute("payment", payment);
    }

    @Test
    void testAdminPaymentListPage() {
        when(paymentService.getAllPayment()).thenReturn(new ArrayList<>());
        String viewName = paymentController.adminPaymentListPage(model);
        assertEquals("adminPaymentList", viewName);
        verify(model).addAttribute("payments", new ArrayList<>());
    }

    @Test
    void testAdminPaymentDetailByIdPage() {
        Payment payment = mock(Payment.class);
        when(paymentService.getPayment("123")).thenReturn(payment);
        String viewName = paymentController.adminPaymentDetailByIdPage("123", model);
        assertEquals("adminPaymentDetail", viewName);
        verify(model).addAttribute("payment", payment);
    }
}