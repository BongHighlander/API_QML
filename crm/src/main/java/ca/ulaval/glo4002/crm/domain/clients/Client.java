package ca.ulaval.glo4002.crm.domain.clients;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private ClientCategory category;

    @Column
    private Instant creationDate;

    @Column
    @Enumerated(EnumType.STRING)
    private DueTerm defaultTerm;

    @Column
    private String fullName;

    @Column
    private String email;

    @Embedded
    private ClientAddress address;

    public Integer getId() {
        return id;
    }

    public ClientCategory getCategory() {
        return category;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public DueTerm getDefaultTerm() {
        return defaultTerm;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public ClientAddress getAddress() {
        return address;
    }
}
