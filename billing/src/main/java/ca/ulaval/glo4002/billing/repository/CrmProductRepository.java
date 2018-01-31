package ca.ulaval.glo4002.billing.repository;

import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.domain.products.ProductRepository;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.exceptions.NotFoundException;
import ca.ulaval.glo4002.billing.interfaces.BillingProperties;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class CrmProductRepository implements ProductRepository {

  private static final String CRM_PRODUCTS_URL = "crmProductsUrl";

  @Override
  public Product getProduct(ProductId id) {
    ProductDto productDto = null;
    try {
      URL url = new URL(BillingProperties.getInstance().getProperty(CRM_PRODUCTS_URL) + id.toString());

      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

      productDto = objectMapper.readValue(url, ProductDto.class);
    } catch (IOException e1) {
      throw new NotFoundException(Product.class.getSimpleName(), id.toString());
    }
    return new Product(id, productDto.getName(), productDto.getPrice(), productDto.getQuantity());
  }

}
