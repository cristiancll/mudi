package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.dto.NewOrderRequest;
import br.com.alura.mvc.mudi.model.ProductOrder;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.ProductOrderRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("order")
public class OrderController {
    
    @Autowired
    private ProductOrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("form")
    public String form(NewOrderRequest request){
        return "order/form";
    }
    
    @PostMapping("new")
    public String newOrder(@Valid NewOrderRequest request, BindingResult result){
        if(!result.hasErrors()){

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByUsername(username);
            ProductOrder order = request.toProductOrder();
            order.setUser(user);
            orderRepository.save(order);
        }
        return "redirect:/home";
    }
}
