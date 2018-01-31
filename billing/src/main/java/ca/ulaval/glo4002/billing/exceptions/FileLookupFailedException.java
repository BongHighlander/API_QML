package ca.ulaval.glo4002.billing.exceptions;

public class FileLookupFailedException extends BillingException {

  private static final long serialVersionUID = 1L;
  private static final String ERROR = "IO Error on a file";
  private String description;
  private String entity;

  public FileLookupFailedException() {
  }

  public FileLookupFailedException(String className, String description) {
    super();
    this.entity = className;
    this.description = description;
  }

  public String getError() {
    return null;
  }

  public String getDescription() {
    return this.description;
  }

  public String getEntity() {
    return this.entity;
  }
}
