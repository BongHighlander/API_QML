package billing;

import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.exceptions.NegativeException;
import ca.ulaval.glo4002.billing.repository.CrmClientRepository;
import ca.ulaval.glo4002.billing.repository.CrmProductRepository;
import ca.ulaval.glo4002.billing.repository.InMemoryBillRepository;
import ca.ulaval.glo4002.billing.services.BillService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestBillService {

  private final int GOOD_ID = 1;
  private final int BAD_ID = -1;
  private final boolean GOOD_ENTITY_FLAG = true;
  private final String PRODUCT_NAME = "name";
  private final BigDecimal PRODUCT_PRICE = new BigDecimal(1);
  private final int PRODUCT_QUANTITY = 1;
  private final ProductId VALID_PRODUCT_ID = new ProductId(GOOD_ID);
  private final ClientId VALID_CLIENT_ID = new ClientId(GOOD_ID);
  private final Client VALID_CLIENT = new Client(VALID_CLIENT_ID);
  private final Product VALID_PRODUCT = new Product(VALID_PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QUANTITY);

  private BillService service;
  private OrderDto order;

  @Mock
  private CrmClientRepository crmClientRepository;

  @Mock
  private CrmProductRepository crmProductRepository;

  @Mock
  private InMemoryBillRepository inMemoryBillRepository;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void init() {
    service = new BillService(crmClientRepository, crmProductRepository, inMemoryBillRepository);
  }

  @Test
  public void givenClientId_whenClientExists_thenReturnClient() {
    Mockito.when(crmClientRepository.getClient(VALID_CLIENT_ID)).thenReturn(VALID_CLIENT);
    Client returnedClient = service.getClient(VALID_CLIENT_ID);
    assertNotNull(returnedClient);
  }

  @Test
  public void givenClientId_whenClientDoesNotExists_thenReturnNull() {
    final ClientId INVALID_CLIENT_ID = new ClientId(BAD_ID);
    Client client = service.getClient(INVALID_CLIENT_ID);
    assertNull(client);
  }

  @Test
  public void givenOrder_whenDueTermIsValid_thenReturnTrue() {
    boolean dueTerm = service.dueTermIsValid(DueTerm.IMMEDIATE);
    assertTrue(dueTerm);
  }

  @Test
  public void givenOrder_whenDueTermIsNotValid_thenReturnFalse() {
    final DueTerm INVALID_DUE_TERM = null;
    boolean dueTerm = service.dueTermIsValid(INVALID_DUE_TERM);
    assertFalse(dueTerm);
  }

  @Test
  public void givenOrder_whenDueTermIsAbsent_thenUseClientDueTerm() {
    final DueTerm NULL_DUE_TERM = null;
    ClientId goodClientId = new ClientId(GOOD_ID);
    Client client = new Client(goodClientId, null, null, DueTerm.DAYS30, "", "", null);
    Mockito.when(crmClientRepository.getClient(goodClientId)).thenReturn(client);
    DueTerm dueTerm = service.chooseDueTerm(client.getDefaultTerm(), NULL_DUE_TERM);
    assertEquals(DueTerm.DAYS30, dueTerm);
  }

  @Test
  public void givenOrder_whenProductIsValid_thenReturnProduct() {
    Mockito.when(crmProductRepository.getProduct(VALID_PRODUCT_ID)).thenReturn(VALID_PRODUCT);
    Product returnedProduct = service.getProduct(VALID_PRODUCT_ID);
    assertNotNull(returnedProduct);
  }

  @Test
  public void givenOrder_whenProductIsNotValid_thenReturnFalse() {
    final ProductId INVALID_PRODUCT_ID = new ProductId(BAD_ID);
    Product returnedProduct = service.getProduct(INVALID_PRODUCT_ID);
    assertNull(returnedProduct);
  }

  @Test(expected = NegativeException.class)
  public void givenProducts_whenQuantityHasNegativeValue_thenNegativeException() {
    final int NEGATIVE_VALUE = -1;
    final boolean validDto = true;
    ProductDto productDto = createProductDto(GOOD_ENTITY_FLAG);
    productDto.setQuantity(NEGATIVE_VALUE);
    order = createOrderDto(validDto);
    order.getProductDtos().add(productDto);
    service.hasNegativeValues(order);
  }

  @Test(expected = NegativeException.class)
  public void givenProducts_whenTotalHasNegativeValue_thenNegativeException() {
    final int NEGATIVE_VALUE = -100;
    final boolean VALID_DTO_FLAG = true;
    ProductDto productDto = createProductDto(GOOD_ENTITY_FLAG);
    productDto.setPrice(new BigDecimal(NEGATIVE_VALUE));
    order = createOrderDto(VALID_DTO_FLAG);
    order.getProductDtos().add(productDto);
    service.hasNegativeValues(order);
  }

  @Test
  public void givenValidOrderDto_whenOrderIsValid_thenReturnTrue() {
    final boolean VALID_DTO_FLAG = true;
    Mockito.when(crmClientRepository.getClient(VALID_CLIENT_ID)).thenReturn(VALID_CLIENT);
    Mockito.when(crmProductRepository.getProduct(VALID_PRODUCT_ID)).thenReturn(VALID_PRODUCT);
    order = createOrderDto(VALID_DTO_FLAG);
    assertTrue(service.orderIsValid(order, VALID_CLIENT));
  }

  @Test
  public void givenInvalidOrderDto_whenOrderIsNotValid_thenReturnFalse() {
    final boolean INVALID_DTO_FLAG = false;
    final Client INVALID_CLIENT = null;
    order = createOrderDto(INVALID_DTO_FLAG);
    assertFalse(service.orderIsValid(order, INVALID_CLIENT));
  }

  @Test
  public void givenProductList_whenProductDoesNotExist_thenReturnFalse() {
    final boolean BAD_ENTITY_FLAG = false;
    final List<ProductDto> PRODUCT_DTOS_WITH_INVALID = new ArrayList<>(
        Arrays.asList(createProductDto(GOOD_ENTITY_FLAG), createProductDto(BAD_ENTITY_FLAG)));

    boolean allProductDtosAreValid = service.eachProductsExist(PRODUCT_DTOS_WITH_INVALID);
    assertFalse(allProductDtosAreValid);
  }

  @Test
  public void givenValidOrderDto_whenCreate_thenBillCreationSuccess() {
    final boolean VALID_DTO_FLAG = true;
    order = createOrderDto(VALID_DTO_FLAG);
    Mockito.when(crmProductRepository.getProduct(VALID_PRODUCT_ID)).thenReturn(VALID_PRODUCT);
    Mockito.when(crmClientRepository.getClient(VALID_CLIENT_ID)).thenReturn(VALID_CLIENT);
    BillDto createdBillDto = service.create(order);
    assertNotNull(createdBillDto);
  }

  private ProductDto createProductDto(boolean isValid) {
    final ProductId PRODUCT_ID = new ProductId(isValid ? GOOD_ID : BAD_ID);
    return new ProductDto(PRODUCT_PRICE, PRODUCT_NAME, PRODUCT_ID, PRODUCT_QUANTITY);
  }

  private OrderDto createOrderDto(boolean isValid) {
    List<ProductDto> productDtos = new ArrayList<>();
    productDtos.add(createProductDto(GOOD_ENTITY_FLAG));
    ClientId clientId = new ClientId(isValid ? GOOD_ID : BAD_ID);
    order = new OrderDto(clientId, new Date(), DueTerm.DAYS30, productDtos);
    return order;
  }
}
