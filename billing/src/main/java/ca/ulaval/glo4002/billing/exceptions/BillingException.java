package ca.ulaval.glo4002.billing.exceptions;

public abstract class BillingException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BillingException() {
  }

  public BillingException(String className, String id) {
    super();
  }

  public abstract String getError();

  public abstract String getDescription();

  public abstract String getEntity();

}
