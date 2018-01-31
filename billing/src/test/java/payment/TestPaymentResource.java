package payment;

import ca.ulaval.glo4002.billing.dto.PaymentDto;
import ca.ulaval.glo4002.billing.interfaces.rest.PaymentResource;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class TestPaymentResource {

  @Mock
  private PaymentDto paymentDto;

  @Mock
  private PaymentResource paymentResource;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Test
  public void givenPaymentResource_whenPay_thenPayIsCalled() {
    paymentResource.pay(paymentDto);
    Mockito.verify(paymentResource).pay(paymentDto);
  }
}
