package ca.ulaval.glo4002.billing.domain.bills;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BillId implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "BILL_ID")
  private long billId;

  public BillId() {
    // For Hibernate. Do not use.
  }

  public BillId(long id) {
    this.billId = id;
  }

  @Override
  public String toString() {
    return Long.toString(billId);
  }

  public long getId() {
    return billId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BillId billId = (BillId) o;

    return this.billId == billId.billId;
  }

  @Override
  public int hashCode() {
    return (int) (billId ^ (billId >>> Integer.SIZE));
  }
}
