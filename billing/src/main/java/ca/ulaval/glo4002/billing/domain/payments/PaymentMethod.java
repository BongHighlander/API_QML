package ca.ulaval.glo4002.billing.domain.payments;

import javax.persistence.*;

@Entity(name = "PaymentMethod")
public class PaymentMethod {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "account")
  private String account;

  @Column(name = "source")
  private String source;

  public PaymentMethod() {
  }

  public PaymentMethod(String account, String source) {
    this.account = account;
    this.source = source;
  }

  public String getAccount() {
    return this.account;
  }

  public String getSource() {
    return this.source;
  }
}
