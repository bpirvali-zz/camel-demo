package com.bp.samples.controller;

import com.bp.samples.model.Order;
import com.bp.samples.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by behzad.pirvali on 12/5/16.
 */
@Controller
public class HomeController {

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/")
    public String index(
            @RequestParam(value="name", required=false, defaultValue="User") String name,
            Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping(value = "/view-orders")
    public String orderHome(Model model) {
        List<Order> orders = orderRepository.getOrders();
        model.addAttribute("orders", orders);
        return "view-orders";
    }
}
