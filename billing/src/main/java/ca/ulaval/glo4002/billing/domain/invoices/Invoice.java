package ca.ulaval.glo4002.billing.domain.invoices;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

@Entity(name = "Invoice")
public class Invoice {

  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private InvoiceId id;

  @Column(name = "effectiveDate")
  private Date effectiveDate;

  @Column(name = "expectedPayment")
  private Date expectedPayment;

  @JoinColumn(name = "BILL_ID", unique = true)
  @OneToOne(cascade = CascadeType.ALL)
  private Bill bill;

  @Column(name = "paidAmount")
  private BigDecimal paidAmount;

  public Invoice() {
  }

  public Invoice(Bill bill) {
    this.id = new InvoiceId(bill.getBillId().getId());
    this.effectiveDate = bill.getCreationDate();
    this.bill = bill;
    this.expectedPayment = calculateExpectedPayment();
    this.paidAmount = new BigDecimal(0);
  }

  public InvoiceId getId() {
    return this.id;
  }

  public Date getEffectiveDate() {
    return this.effectiveDate;
  }

  public DueTerm getDueTerm() {
    return this.bill.getDueTerm();
  }

  public Date getExpectedPayment() {
    return this.expectedPayment;
  }

  public Bill getBill() {
    return this.bill;
  }

  public void addPayment(float amount) {
    BigDecimal amountToAdd = new BigDecimal(amount);
    this.paidAmount.add(amountToAdd);
  }

  public BigDecimal getPaidAmount() {
    return this.paidAmount;
  }

  private Date calculateExpectedPayment() {
    Calendar dueDate = Calendar.getInstance();
    int daysToAdd = DueTerm.toInt(bill.getDueTerm());
    dueDate.setTime(effectiveDate);
    dueDate.add(Calendar.DATE, daysToAdd);
    return dueDate.getTime();
  }
}
