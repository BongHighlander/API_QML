package ca.ulaval.glo4002.billing.repository;

import ca.ulaval.glo4002.billing.domain.bills.BillId;

import java.util.concurrent.atomic.AtomicLong;

public class BillIdGenerator {

  private static BillIdGenerator billIdGenerator = null;
  private static AtomicLong id;

  private BillIdGenerator() {
    this.id = new AtomicLong();
  }

  public BillId getId() {
    return new BillId(id.incrementAndGet());
  }

  public static BillIdGenerator getInstance() {
    if (billIdGenerator == null) {
      billIdGenerator = new BillIdGenerator();
    }

    return billIdGenerator;
  }

}
