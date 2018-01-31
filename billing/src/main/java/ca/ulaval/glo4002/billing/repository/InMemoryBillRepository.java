package ca.ulaval.glo4002.billing.repository;

import java.util.Hashtable;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.bills.BillRepository;

public class InMemoryBillRepository implements BillRepository {

  private Hashtable<BillId, Bill> bills;

  public InMemoryBillRepository() {
    bills = new Hashtable<>();
  }

  @Override
  public void insert(Bill bill) {
    if (!bills.containsKey(bill.getBillId())) {
      bills.put(bill.getBillId(), bill);
    }
  }

  @Override
  public Bill find(BillId billId) {
    return bills.get(billId);
  }

}
