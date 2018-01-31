package ca.ulaval.glo4002.billing.dto;

import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

import java.util.Date;

public class InvoiceDto {

  private String id;
  private Date effectiveDate;
  private Date expectedPayment;
  private DueTerm dueTerm;
  private String url;

  public InvoiceDto(String id, Date effectiveDate, Date expectedPayment, DueTerm dueTerm, String url) {
    this.id = id;
    this.effectiveDate = effectiveDate;
    this.expectedPayment = expectedPayment;
    this.dueTerm = dueTerm;
    this.url = url;
  }

  public String getId() {
    return this.id;
  }

  public String getEffectiveDate() {
    return this.effectiveDate.toInstant().toString();
  }

  public String getExpectedPayment() {
    return this.expectedPayment.toInstant().toString();
  }

  public DueTerm getDueTerm() {
    return this.dueTerm;
  }

  public String getUrl() {
    return this.url;
  }

}
