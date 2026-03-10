package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
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
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private Model model;

    @InjectMocks
    private OrderController orderController;

    @Test
    void testCreateOrderPage() {
        String viewName = orderController.createOrderPage(model);
        assertEquals("createOrder", viewName);
    }

    @Test
    void testHistoryOrderPage() {
        String viewName = orderController.historyOrderPage(model);
        assertEquals("historyOrder", viewName);
    }

    @Test
    void testHistoryOrderPost() {
        when(orderService.findAllByAuthor("Budi")).thenReturn(new ArrayList<>());
        String viewName = orderController.historyOrderPost("Budi", model);
        assertEquals("historyOrder", viewName);
        verify(model).addAttribute("orders", new ArrayList<>());
    }

    @Test
    void testPaymentOrderPage() {
        Order order = mock(Order.class);
        when(orderService.findById("123")).thenReturn(order);
        String viewName = orderController.paymentOrderPage("123", model);
        assertEquals("paymentOrder", viewName);
        verify(model).addAttribute("order", order);
    }

    @Test
    void testPaymentOrderPost() {
        String viewName = orderController.paymentOrderPost("123", model);
        assertEquals("paymentSuccess", viewName);
        verify(model).addAttribute("paymentId", "123");
    }

    @Test
    void testCreateOrderPost() {
        String viewName = orderController.createOrderPost("Budi", "Sampo", 2, "Sabun", 1);
        assertEquals("redirect:/order/history", viewName);
        verify(orderService, times(1)).createOrder(any(Order.class));
    }

    @Test
    void testCreateOrderPostWithOneProduct() {
        String viewName = orderController.createOrderPost("Budi", "Sampo", 2, null, 0);
        assertEquals("redirect:/order/history", viewName);
        verify(orderService, times(1)).createOrder(any(Order.class));
    }

    @Test
    void testCreateOrderPostWithEmptyProductName2() {
        String viewName = orderController.createOrderPost("Budi", "Sampo", 2, "   ", 1);
        assertEquals("redirect:/order/history", viewName);
        verify(orderService, times(1)).createOrder(any(Order.class));
    }

    @Test
    void testCreateOrderPostWithZeroQuantityProduct2() {
        String viewName = orderController.createOrderPost("Budi", "Sampo", 2, "Sabun", 0);
        assertEquals("redirect:/order/history", viewName);
        verify(orderService, times(1)).createOrder(any(Order.class));
    }
}