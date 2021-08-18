package br.com.alura.mvc.mudi.dto;

import br.com.alura.mvc.mudi.model.OrderStatus;
import br.com.alura.mvc.mudi.model.ProductOrder;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewOrderRequest {

    @NotBlank
    private String productName;
    @NotBlank
    private String productUrl;
    @NotBlank
    private String imageUrl;
    private String description;

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public ProductOrder toProductOrder() {
        ProductOrder order = new ProductOrder();
        order.setProductName(this.productName);
        order.setProductUrl(this.productUrl);
        order.setImageUrl(this.imageUrl);
        order.setDescription(this.description);
        order.setStatus(OrderStatus.WAITING);
        return order;
    }
}
