package ca.ulaval.glo4002.billing.dto;

public class PaymentMethodDto {

  private String account;
  private String source;

  public PaymentMethodDto() {
  }

  public PaymentMethodDto(String account, String source) {
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
