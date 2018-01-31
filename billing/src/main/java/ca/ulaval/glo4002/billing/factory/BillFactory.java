package ca.ulaval.glo4002.billing.factory;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.repository.BillIdGenerator;

public class BillFactory {

  public Bill create(OrderDto order, Client client) {
    BillIdGenerator billIdGenerator = BillIdGenerator.getInstance();
    ProductsFactory productsFactory = new ProductsFactory();

    return new Bill(billIdGenerator.getId(), client, order.getDate(), order.getDueTerm(),
        productsFactory.create(order.getProductDtos()));
  }

}
