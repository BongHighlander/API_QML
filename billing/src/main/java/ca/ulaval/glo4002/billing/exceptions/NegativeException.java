package ca.ulaval.glo4002.billing.exceptions;

public class NegativeException extends BillingException {

  private static final long serialVersionUID = 1L;
  private static final String ERROR = "is negative";
  private String description;
  private String entity;

  public NegativeException() {
  }

  public NegativeException(String variable, String value) {
    super();
    this.description = variable + " " + ERROR + " : " + value;
    this.entity = variable;
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
