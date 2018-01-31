package ca.ulaval.glo4002.billing.dto;

import ca.ulaval.glo4002.billing.domain.clients.ClientAddress;
import ca.ulaval.glo4002.billing.domain.clients.ClientCategory;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "_links" }, ignoreUnknown = true)
public class ClientDto {

  private int id;
  private ClientCategory category;
  private String creationDate;
  private DueTerm defaultTerm;
  private String fullName;
  private String email;
  private ClientAddress address;

  public String getCreationDate() {
    return creationDate;
  }

  public String getFullName() {
    return fullName;
  }

  public String getEmail() {
    return email;
  }

  public int getId() {
    return id;
  }

  public ClientCategory getCategory() {
    return category;
  }

  public DueTerm getDefaultTerm() {
    return defaultTerm;
  }

  public ClientAddress getAddress() {
    return address;
  }
}
