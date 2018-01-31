package ca.ulaval.glo4002.billing.assembler;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.dto.BillDto;

public class BillAssembler {

  public static final String BILLS_PATH = "/bills/";

  public BillDto create(Bill bill) {
    return new BillDto(bill.getBillId().toString(), bill.getTotal(), bill.getDueTerm(),
        BILLS_PATH + bill.getBillId().toString());
  }

}
