package ca.ulaval.glo4002.billing.services;

import ca.ulaval.glo4002.billing.entitymanager.EntityManagerFactoryProvider;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

abstract class BillingService {

  public void prepareDatabase() {
    EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityManagerProvider.setEntityManager(entityManager);
  }
}
