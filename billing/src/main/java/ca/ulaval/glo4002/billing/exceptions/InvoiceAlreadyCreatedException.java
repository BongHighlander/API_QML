package ca.ulaval.glo4002.billing.exceptions;

public class InvoiceAlreadyCreatedException extends BillingException {

  private static final long serialVersionUID = 1L;
  private static final String ERROR = "wrong status";
  private static final String DESCRIPTION_ERROR = "already accepted";
  private String description;
  private String entity;

  public InvoiceAlreadyCreatedException() {
  }

  public InvoiceAlreadyCreatedException(String className) {
    super();
    this.description = className + " " + DESCRIPTION_ERROR;
    this.entity = className;
  }

  public String getError() {
    return ERROR;
  }

  public String getDescription() {
    return this.description;
  }

  public String getEntity() {
    return this.entity;
  }
}
