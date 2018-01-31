package ca.ulaval.glo4002.crm.repositories;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.ulaval.glo4002.crm.domain.products.Product;

@RepositoryRestResource
public interface ProductRepository extends Repository<Product, Integer> {
    Product findOne(Integer id);
}
