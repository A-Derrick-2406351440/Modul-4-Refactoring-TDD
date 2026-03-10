package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public String createOrderPage(Model model) {
        return "createOrder";
    }

    @GetMapping("/history")
    public String historyOrderPage(Model model) {
        return "historyOrder";
    }

    @PostMapping("/history")
    public String historyOrderPost(@RequestParam("author") String author, Model model) {
        List<Order> orders = orderService.findAllByAuthor(author);
        model.addAttribute("orders", orders);
        return "historyOrder";
    }

    @GetMapping("/pay/{orderId}")
    public String paymentOrderPage(@PathVariable String orderId, Model model) {
        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        return "paymentOrder";
    }

    @PostMapping("/pay/{orderId}")
    public String paymentOrderPost(@PathVariable String orderId, Model model) {
        model.addAttribute("paymentId", orderId);
        return "paymentSuccess";
    }

    @PostMapping("/create")
    public String createOrderPost(@RequestParam("author") String author,
                                  @RequestParam("productName1") String productName1,
                                  @RequestParam("productQuantity1") int productQuantity1,
                                  @RequestParam(value = "productName2", required = false) String productName2,
                                  @RequestParam(value = "productQuantity2", defaultValue = "0") int productQuantity2) {

        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("P1-" + System.currentTimeMillis());
        product1.setProductName(productName1);
        product1.setProductQuantity(productQuantity1);
        products.add(product1);

        if (productName2 != null && !productName2.trim().isEmpty() && productQuantity2 > 0) {
            Product product2 = new Product();
            product2.setProductId("P2-" + System.currentTimeMillis());
            product2.setProductName(productName2);
            product2.setProductQuantity(productQuantity2);
            products.add(product2);
        }

        Order order = new Order(String.valueOf(System.currentTimeMillis()), products, System.currentTimeMillis(), author);
        orderService.createOrder(order);

        return "redirect:/order/history";
    }
}