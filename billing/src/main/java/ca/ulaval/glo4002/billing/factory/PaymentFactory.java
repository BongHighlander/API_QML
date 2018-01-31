package ca.ulaval.glo4002.billing.factory;

import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.payments.Payment;
import ca.ulaval.glo4002.billing.domain.payments.PaymentMethod;
import ca.ulaval.glo4002.billing.dto.PaymentDto;

public class PaymentFactory {

  public Payment create(PaymentDto paymentDto, Client client) {
    PaymentMethod paymentMethod = new PaymentMethod(paymentDto.getPaymentMethod().getAccount(),
        paymentDto.getPaymentMethod().getSource());
    Payment payment = new Payment(client, paymentDto.getAmount(), paymentMethod);
    return payment;
  }
}
