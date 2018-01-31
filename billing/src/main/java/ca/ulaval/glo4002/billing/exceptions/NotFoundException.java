package ca.ulaval.glo4002.billing.exceptions;

public class NotFoundException extends BillingException {

  private static final long serialVersionUID = 1L;
  private static final String ERROR = "not found";
  private String description;
  private String entity;

  public NotFoundException() {
  }

  public NotFoundException(String className, String id) {
    super();
    this.description = className + " " + id + " " + ERROR;
    this.entity = className;
  }

  public String getError() {
    return this.ERROR;
  }

  public String getDescription() {
    return this.description;
  }

  public String getEntity() {
    return this.entity;
  }

}
