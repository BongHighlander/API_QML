package billing;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.interfaces.rest.BillResource;

public class TestBillResource {

  private static final long BILL_ID = 1;

  @Mock
  private BillResource billResource;

  @Mock
  private OrderDto orderDto;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Test
  public void givenBillResource_whenCreateBill_thenCreateIsCalled() {
    billResource.create(orderDto);
    Mockito.verify(billResource).create(orderDto);
  }

  @Test
  public void givenBillResource_whenCreateInvoice_thenCreateInvoiceIsCalled() {
    billResource.createInvoice((int) BILL_ID);
    Mockito.verify(billResource).createInvoice((int) BILL_ID);
  }

  @Test
  public void givenBillResource_whenDeleteInvoice_thenDeleteInvoiceIsCalled() {
    billResource.deleteInvoice((int) BILL_ID);
    Mockito.verify(billResource).deleteInvoice((int) BILL_ID);
  }
}
