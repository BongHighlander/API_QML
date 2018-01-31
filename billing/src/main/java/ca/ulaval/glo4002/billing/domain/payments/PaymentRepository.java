package ca.ulaval.glo4002.billing.domain.payments;

public interface PaymentRepository {

  PaymentId insert(Payment payment);
}
