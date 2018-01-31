package ca.ulaval.glo4002.billing.domain.invoices;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InvoiceId implements Serializable {

  private static final long serialVersionUID = 2L;

  @Column(name = "INVOICE_ID")
  private long invoiceId;

  public InvoiceId() {
    // For Hibernate. Do not use.
  }

  public InvoiceId(long id) {
    this.invoiceId = id;
  }

  @Override
  public String toString() {
    return Long.toString(invoiceId);
  }

  public long getId() {
    return this.invoiceId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    InvoiceId invoiceId = (InvoiceId) o;

    return this.invoiceId == invoiceId.invoiceId;
  }

  @Override
  public int hashCode() {
    return (int) (invoiceId ^ (invoiceId >>> Integer.SIZE));
  }
}
