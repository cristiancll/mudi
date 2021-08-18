package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.OrderStatus;
import br.com.alura.mvc.mudi.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private ProductOrderRepository orderRepository;

    @GetMapping("order")
    public String home(Model model, Principal principal){
        model.addAttribute("orders", orderRepository.findAllByUser(principal.getName()));
        return "user/home";
    }
    @GetMapping("order/{status}")
    public String filtered(@PathVariable("status") String status, Model model, Principal principal){
        model.addAttribute("orders", orderRepository.findByStatusAndUser(OrderStatus.valueOf(status.toUpperCase()), principal.getName()));
        model.addAttribute("status", status);
        return "user/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/user/home";
    }
    
    
}
