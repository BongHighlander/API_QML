package ca.ulaval.glo4002.billing.dto;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class OrderDto {

  private ClientId clientId;
  private Date creationDate;
  private DueTerm dueTerm;
  private List<ProductDto> items;

  @JsonCreator
  public OrderDto(@JsonProperty("clientId") ClientId clientId, @JsonProperty("creationDate") Date creationDate,
      @JsonProperty("dueTerm") DueTerm dueTerm, @JsonProperty("items") List<ProductDto> items) {
    this.clientId = clientId;
    this.creationDate = creationDate;
    this.dueTerm = dueTerm;
    this.items = items;
  }

  public ClientId getClientId() {
    return clientId;
  }

  public Date getDate() {
    return creationDate;
  }

  public DueTerm getDueTerm() {
    return dueTerm;
  }

  public List<ProductDto> getProductDtos() {
    return items;
  }

  public void setDueTerm(DueTerm dueTerm) {
    this.dueTerm = dueTerm;
  }
}
