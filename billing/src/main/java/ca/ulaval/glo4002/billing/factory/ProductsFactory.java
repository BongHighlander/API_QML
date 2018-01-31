package ca.ulaval.glo4002.billing.factory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.dto.ProductDto;

public class ProductsFactory {
  public List<Product> create(List<ProductDto> productDtos) {
    List<Product> products = new ArrayList<>();

    for (ProductDto productDto : productDtos) {
      ProductId productId = productDto.getProductId();
      String name = productDto.getName();
      BigDecimal unitPrice = productDto.getPrice();
      int quantity = productDto.getQuantity();

      Product product = new Product(productId, name, unitPrice, quantity);
      products.add(product);
    }

    return products;
  }
}
