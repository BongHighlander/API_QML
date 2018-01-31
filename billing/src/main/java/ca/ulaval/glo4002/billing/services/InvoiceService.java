package ca.ulaval.glo4002.billing.services;

import ca.ulaval.glo4002.billing.assembler.InvoiceAssembler;
import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.bills.BillRepository;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceId;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceRepository;
import ca.ulaval.glo4002.billing.dto.InvoiceDto;
import ca.ulaval.glo4002.billing.exceptions.InvoiceNotFoundException;
import ca.ulaval.glo4002.billing.factory.InvoiceFactory;
import ca.ulaval.glo4002.billing.repository.HibernateBillRepository;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;

public class InvoiceService extends BillingService {

  private InvoiceRepository invoiceRepository;
  private BillRepository billRepository;
  private InvoiceFactory invoiceFactory;
  private InvoiceAssembler invoiceDtoFactory;

  public InvoiceService() {
    prepareDatabase();
    this.invoiceRepository = new HibernateInvoiceRepository();
    this.billRepository = new HibernateBillRepository();
    this.invoiceFactory = new InvoiceFactory();
    this.invoiceDtoFactory = new InvoiceAssembler();
  }

  public InvoiceService(InvoiceRepository invoiceRepository, BillRepository billRepository) {
    this.invoiceRepository = invoiceRepository;
    this.billRepository = billRepository;
    this.invoiceFactory = new InvoiceFactory();
    this.invoiceDtoFactory = new InvoiceAssembler();
  }

  public InvoiceDto create(BillId billId) {
    InvoiceDto invoiceDto = null;
    Bill bill = billRepository.find(billId);
    if (bill != null) {
      Invoice invoice = invoiceFactory.create(bill);
      InvoiceId invoiceId = invoiceRepository.insert(invoice);
      invoiceDto = invoiceDtoFactory.create(invoiceId, invoice);
    }

    if (invoiceDto == null) {
      throw new InvoiceNotFoundException();
    }

    return invoiceDto;
  }

  public boolean invoiceExists(InvoiceId invoiceId) {
    boolean invoiceExists = false;
    Invoice invoice = invoiceRepository.find(invoiceId);

    if (invoice != null) {
      invoiceExists = true;
    }

    return invoiceExists;
  }

  public void delete(InvoiceId invoiceId) {
    Invoice invoice = invoiceRepository.find(invoiceId);
    if (invoice == null) {
      throw new InvoiceNotFoundException();
    }
    invoiceRepository.delete(invoice);
  }
}
