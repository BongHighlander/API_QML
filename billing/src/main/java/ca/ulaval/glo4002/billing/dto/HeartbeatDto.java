package ca.ulaval.glo4002.billing.dto;

public class HeartbeatDto {

  private long date;
  private String token;

  public HeartbeatDto(long timestamp, String token) {
    this.token = token;
    this.date = timestamp;
  }

  public String getToken() {
    return token;
  }

  public long getDate() {
    return date;
  }
}
