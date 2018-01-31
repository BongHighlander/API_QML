package ca.ulaval.glo4002.billing.dto;

public class ReceiptDto {

  private String id;
  private String url;

  public ReceiptDto(String id, String url) {
    this.id = id;
    this.url = url;
  }

  public String getId() {
    return this.id;
  }

  public String getUrl() {
    return this.url;
  }
}
