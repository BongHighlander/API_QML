package billing;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.repository.BillIdGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBillIdGenerator {

  @Test
  public void givenBillIdGenerator_whenCreateNewId_thenNewIdIsDifferent() {
    BillIdGenerator billIdGenerator = BillIdGenerator.getInstance();
    BillId firstId = billIdGenerator.getId();
    BillId secondId = billIdGenerator.getId();
    assertNotEquals(firstId.toString(), secondId.toString());
  }

}
