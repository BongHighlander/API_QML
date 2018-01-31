package ca.ulaval.glo4002.billing.dto;

import ca.ulaval.glo4002.billing.domain.products.ProductId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(value = { "links" }, ignoreUnknown = true)
public class ProductDto {

  private BigDecimal price;
  private String name;
  private ProductId productId;
  private int quantity;

  public ProductDto() {
  }

  public ProductDto(BigDecimal price, String name, ProductId productId, int quantity) {
    this.price = price;
    this.name = name;
    this.productId = productId;
    this.quantity = quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  public ProductId getProductId() {
    return productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
