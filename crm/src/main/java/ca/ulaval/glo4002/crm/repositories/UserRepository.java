package ca.ulaval.glo4002.crm.repositories;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.ulaval.glo4002.crm.domain.users.User;

@RepositoryRestResource
public interface UserRepository extends Repository<User, Integer> {
    User findOne(Integer id);
}
