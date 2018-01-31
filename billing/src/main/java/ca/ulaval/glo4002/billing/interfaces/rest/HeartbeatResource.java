package ca.ulaval.glo4002.billing.interfaces.rest;

import ca.ulaval.glo4002.billing.dto.HeartbeatDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("/heartbeat")
public class HeartbeatResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public HeartbeatDto getHeartbeat(@QueryParam("token") String token) {
    long timestamp = new Date().toInstant().toEpochMilli();
    return new HeartbeatDto(timestamp, token);
  }
}
