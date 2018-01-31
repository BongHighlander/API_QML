package ca.ulaval.glo4002.billing.domain.invoices;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;

public interface InvoiceRepository {

  Invoice find(InvoiceId invoiceId);

  InvoiceId insert(Invoice invoice);

  Invoice findLast(ClientId clientId);

  void delete(Invoice invoice);

}
