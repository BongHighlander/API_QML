package ca.ulaval.glo4002.billing.services;

import ca.ulaval.glo4002.billing.assembler.BillAssembler;
import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillRepository;
import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.domain.products.ProductRepository;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.exceptions.NegativeException;
import ca.ulaval.glo4002.billing.factory.BillFactory;
import ca.ulaval.glo4002.billing.repository.CrmClientRepository;
import ca.ulaval.glo4002.billing.repository.CrmProductRepository;
import ca.ulaval.glo4002.billing.repository.HibernateBillRepository;

import java.math.BigDecimal;
import java.util.List;

public class BillService extends BillingService {

  private ClientRepository clientRepository;
  private ProductRepository productRepository;
  private BillRepository billRepository;
  private BillFactory billFactory;
  private BillAssembler billDtoFactory;

  public BillService() {
    prepareDatabase();
    this.clientRepository = new CrmClientRepository();
    this.productRepository = new CrmProductRepository();
    this.billRepository = new HibernateBillRepository();
    this.billFactory = new BillFactory();
    this.billDtoFactory = new BillAssembler();
  }

  public BillService(ClientRepository clientRepository, ProductRepository productRepository,
      BillRepository billRepository) {
    this.clientRepository = clientRepository;
    this.productRepository = productRepository;
    this.billRepository = billRepository;
    this.billFactory = new BillFactory();
    this.billDtoFactory = new BillAssembler();
  }

  public BillDto create(OrderDto order) {
    BillDto billDto = null;
    Client client = getClient(order.getClientId());

    if (orderIsValid(order, client)) {
      Bill bill = billFactory.create(order, client);
      billRepository.insert(bill);
      billDto = billDtoFactory.create(bill);
    }

    return billDto;
  }

  public boolean orderIsValid(OrderDto order, Client client) {
    boolean orderIsValid = false;
    if (clientExists(client) && eachProductsExist(order.getProductDtos())) {
      if (!hasNegativeValues(order)) {
        order.setDueTerm(chooseDueTerm(client.getDefaultTerm(), order.getDueTerm()));
        orderIsValid = true;
      }
    }
    return orderIsValid;
  }

  public boolean hasNegativeValues(OrderDto order) {
    BigDecimal total = new BigDecimal(0);
    List<ProductDto> listeProducts = order.getProductDtos();

    for (ProductDto productDto : listeProducts) {
      total = total.add(productDto.getPrice());
      throwIfQuantityIsNegative(productDto);
    }

    throwIfTotalIsNegative(total);
    return false;
  }

  private void throwIfTotalIsNegative(BigDecimal total) {
    final String TOTAL = "Total";

    if (total.signum() < 0) {
      throw new NegativeException(TOTAL, "" + total.toString());
    }
  }

  private void throwIfQuantityIsNegative(ProductDto productDto) {
    final String QUANTITY = "Quantity";

    if (productDto.getQuantity() < 0) {
      throw new NegativeException(QUANTITY, "" + productDto.getQuantity());
    }
  }

  public boolean dueTermIsValid(DueTerm dueTerm) {
    return dueTerm != null;
  }

  public DueTerm chooseDueTerm(DueTerm clientDueTerm, DueTerm orderDueTerm) {
    DueTerm dueTerm;
    if (dueTermIsValid(orderDueTerm)) {
      dueTerm = orderDueTerm;
    } else {
      dueTerm = clientDueTerm;
    }
    return dueTerm;
  }

  public Client getClient(ClientId clientId) {
    return clientRepository.getClient(clientId);
  }

  public boolean clientExists(Client client) {
    return client != null;
  }

  public Product getProduct(ProductId productId) {
    return productRepository.getProduct(productId);
  }

  public boolean productExists(Product product) {
    return product != null;
  }

  public boolean eachProductsExist(List<ProductDto> productDtos) {
    boolean eachProductsExist = true;
    for (ProductDto productDto : productDtos) {
      Product product = getProduct(productDto.getProductId());
      if (!productExists(product)) {
        eachProductsExist = false;
      }
    }
    return eachProductsExist;
  }
}
