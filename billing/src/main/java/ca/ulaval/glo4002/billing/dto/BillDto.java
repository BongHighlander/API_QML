package ca.ulaval.glo4002.billing.dto;

import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

import java.math.BigDecimal;

public class BillDto {

  private String id;
  private BigDecimal total;
  private DueTerm dueTerm;
  private String url;

  public BillDto(String billId, BigDecimal total, DueTerm dueTerm, String url) {
    this.id = billId;
    this.total = total;
    this.dueTerm = dueTerm;
    this.url = url;
  }

  public String getId() {
    return id;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public DueTerm getDueTerm() {
    return dueTerm;
  }

  public String getUrl() {
    return url;
  }

}
