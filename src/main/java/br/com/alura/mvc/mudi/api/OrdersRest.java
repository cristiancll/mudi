package br.com.alura.mvc.mudi.api;

import br.com.alura.mvc.mudi.model.OrderStatus;
import br.com.alura.mvc.mudi.model.ProductOrder;
import br.com.alura.mvc.mudi.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersRest {
    
    
    @Autowired
    private ProductOrderRepository orderRepository;
    
    @GetMapping("waiting")
    public List<ProductOrder> getOrdersWaitingOffers(){
        Sort sort = Sort.by("id").descending();
        PageRequest pagination = PageRequest.of(0, 10, sort);
        return orderRepository.findByStatus(OrderStatus.WAITING, pagination);
    }
}
