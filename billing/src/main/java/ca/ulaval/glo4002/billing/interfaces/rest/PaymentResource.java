package ca.ulaval.glo4002.billing.interfaces.rest;

import ca.ulaval.glo4002.billing.dto.PaymentDto;
import ca.ulaval.glo4002.billing.dto.ReceiptDto;
import ca.ulaval.glo4002.billing.services.PaymentService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/payments")
public class PaymentResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response pay(PaymentDto paymentDto) {
    PaymentService paymentService = new PaymentService();
    ReceiptDto receiptDto = paymentService.pay(paymentDto);
    return Response.status(Response.Status.CREATED).entity(receiptDto).build();
  }
}
