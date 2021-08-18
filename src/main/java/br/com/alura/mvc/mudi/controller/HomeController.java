package br.com.alura.mvc.mudi.controller;
import br.com.alura.mvc.mudi.model.OrderStatus;
import br.com.alura.mvc.mudi.model.ProductOrder;
import br.com.alura.mvc.mudi.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ProductOrderRepository orderRepository;

    @GetMapping
    public String home(Model model){
        Sort sort = Sort.by("deliveryDate").descending();
        PageRequest pagination = PageRequest.of(0, 10, sort);
        List<ProductOrder> orders = orderRepository.findByStatus(OrderStatus.DELIVERED, pagination);
        model.addAttribute("orders", orders);
        return "home";
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/home";
    }

}
