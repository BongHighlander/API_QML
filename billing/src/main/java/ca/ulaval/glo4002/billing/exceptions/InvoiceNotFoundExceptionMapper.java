package ca.ulaval.glo4002.billing.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvoiceNotFoundExceptionMapper implements ExceptionMapper<InvoiceNotFoundException> {

  @Override
  public Response toResponse(InvoiceNotFoundException exception) {
    final String EMPTY_JSON = "{}";
    return Response.status(Response.Status.NOT_FOUND).entity(EMPTY_JSON).build();
  }
}
