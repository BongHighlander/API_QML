package ca.ulaval.glo4002.billing.repository;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.billing.domain.payments.Payment;
import ca.ulaval.glo4002.billing.domain.payments.PaymentId;
import ca.ulaval.glo4002.billing.domain.payments.PaymentRepository;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerProvider;

public class HibernatePaymentRepository implements PaymentRepository {

  private EntityManager entityManager;

  public HibernatePaymentRepository() {
    this.entityManager = new EntityManagerProvider().getEntityManager();
  }

  @Override
  public PaymentId insert(Payment payment) {
    entityManager.getTransaction().begin();
    entityManager.persist(payment);
    entityManager.getTransaction().commit();
    return new PaymentId(payment.getId());
  }
}
