package ca.ulaval.glo4002.billing.domain.products;

public interface ProductRepository {

  Product getProduct(ProductId id);
}
