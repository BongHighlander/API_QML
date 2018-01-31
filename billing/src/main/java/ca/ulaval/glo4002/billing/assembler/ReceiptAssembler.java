package ca.ulaval.glo4002.billing.assembler;

import ca.ulaval.glo4002.billing.domain.payments.PaymentId;
import ca.ulaval.glo4002.billing.dto.ReceiptDto;

public class ReceiptAssembler {

  public ReceiptDto create(PaymentId paymentId) {
    return new ReceiptDto(paymentId.toString(), "payments/" + paymentId.toString());
  }
}
