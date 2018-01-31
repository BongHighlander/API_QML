package ca.ulaval.glo4002.billing.exceptions;

public class InvoiceForClientNotFoundException extends BillingException {

  private static final long serialVersionUID = 1L;
  private static final String ERROR = "not found";
  private static final String ENTITY = "Invoice";
  private static final String ERROR_DESCRIPTION = "No invoice exists for the client";

  private String description;

  public InvoiceForClientNotFoundException(String clientId) {
    this.description = ERROR_DESCRIPTION + " " + clientId;
  }

  @Override
  public String getError() {
    return ERROR;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public String getEntity() {
    return ENTITY;
  }
}
