package br.com.alura.mvc.mudi.api;

import br.com.alura.mvc.mudi.dto.NewOfferRequest;
import br.com.alura.mvc.mudi.model.Offer;
import br.com.alura.mvc.mudi.model.ProductOrder;
import br.com.alura.mvc.mudi.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/offers")
public class OffersRest {
    
    @Autowired
    private ProductOrderRepository orderRepository;
    
    @PostMapping
    public Offer createOffer(@Valid @RequestBody NewOfferRequest request){
        Optional<ProductOrder> fetchedOrder = orderRepository.findById(request.getOrderId());
        if(!fetchedOrder.isPresent()){
            return null;
        }
        ProductOrder order = fetchedOrder.get();
        Offer offer = request.toOffer();
        offer.setOrder(order);
        order.getOffers().add(offer);
        orderRepository.save(order);
        return offer;
    }
}
