package ca.ulaval.glo4002.billing.domain.clients;

public interface ClientRepository {

  Client getClient(ClientId id);
}
