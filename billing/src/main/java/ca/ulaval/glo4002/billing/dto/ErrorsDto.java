package ca.ulaval.glo4002.billing.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorsDto {

  private List<ExceptionDto> errors;

  public ErrorsDto() {
    this.errors = new ArrayList<>();
  }

  public void addError(ExceptionDto exceptionDto) {
    this.errors.add(exceptionDto);
  }

  public List<ExceptionDto> getErrors() {
    return this.errors;
  }
}
