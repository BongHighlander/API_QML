package ca.ulaval.glo4002.billing.domain.bills;

public interface BillRepository {

  void insert(Bill bill);

  Bill find(BillId billId);

}
