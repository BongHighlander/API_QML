package ca.ulaval.glo4002.billing.exceptions;

import ca.ulaval.glo4002.billing.dto.ErrorsDto;
import ca.ulaval.glo4002.billing.dto.ExceptionDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BillingExceptionMapper implements ExceptionMapper<BillingException> {

  @Override
  public Response toResponse(BillingException exception) {
    ExceptionDto exceptionDto = new ExceptionDto(exception.getError(), exception.getDescription(),
        exception.getEntity());

    ErrorsDto errors = new ErrorsDto();
    errors.addError(exceptionDto);
    return Response.status(Status.BAD_REQUEST).entity(errors).build();
  }

}
