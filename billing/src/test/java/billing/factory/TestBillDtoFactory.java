package billing.factory;

import ca.ulaval.glo4002.billing.assembler.BillAssembler;
import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.repository.BillIdGenerator;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestBillDtoFactory {

  Bill bill;
  DueTerm dueTerm;
  List<Product> products;
  ProductDto productDto;
  BillAssembler billDtoFactory;

  private long id = 1;

  @Before
  public void init() {
    final BigDecimal PRICE = new BigDecimal(5);
    final String NAME = "";
    final int QUANTITY = 3;
    final int CLIENT_ID = 1;
    final int PRODUCT_ID = 1;

    ProductId productId = new ProductId(PRODUCT_ID);
    BillId billId = BillIdGenerator.getInstance().getId();
    Client client = new Client();
    Date creationDate = new Date();
    productDto = new ProductDto(PRICE, NAME, productId, QUANTITY);
    fillItems(QUANTITY);
    bill = new Bill(billId, client, creationDate, DueTerm.DAYS30, products);
    billDtoFactory = new BillAssembler();
  }

  @Test
  public void givenFactory_whenCreateBillDto_thenBillDtoIsValid() {
    BillDto billDto = billDtoFactory.create(bill);
    assertTrue(validDto(billDto));
  }

  @Test
  public void givenFactory_whenGetTotal_thenPriceIsRight() {
    final int EXPECTED_TOTAL = 9;
    BigDecimal expectedTotal = new BigDecimal(EXPECTED_TOTAL);
    BigDecimal total = bill.getTotal();
    assertEquals(expectedTotal, total);
  }

  private boolean validDto(BillDto billDto) {
    boolean dtoIsValid = true;
    BigDecimal total = bill.getTotal();

    if (!billDto.getTotal().equals(total)) {
      dtoIsValid = false;
    }
    if (!billDto.getId().equals(bill.getBillId().toString())) {
      dtoIsValid = false;
    }
    if (billDto.getDueTerm() != DueTerm.DAYS30) {
      dtoIsValid = false;
    }
    if (!billDto.getUrl().equals("/bills/" + bill.getBillId().toString())) {
      dtoIsValid = false;
    }
    return dtoIsValid;
  }

  private void fillItems(int nbItems) {
    BigDecimal price = new BigDecimal(1);
    String note = "note";
    ProductId productId = new ProductId(1);
    int quantity = 1;
    products = new ArrayList<>();

    for (int i = 0; i < nbItems; i++) {
      price.add(new BigDecimal(1));
      quantity++;
      Product product = new Product(productId, note, price, quantity);
      products.add(product);
    }
  }
}
