package ca.ulaval.glo4002.billing.dto;

public class ExceptionDto {

  private String error;
  private String description;
  private String entity;

  public ExceptionDto(String error, String description, String entity) {
    this.error = error;
    this.description = description;
    this.entity = entity;
  }

  public String getError() {
    return error;
  }

  public String getDescription() {
    return description;
  }

  public String getEntity() {
    return entity;
  }
}
