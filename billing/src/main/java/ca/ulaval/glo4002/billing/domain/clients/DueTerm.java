package ca.ulaval.glo4002.billing.domain.clients;

public enum DueTerm {
  IMMEDIATE, DAYS30, DAYS90;

  public static int toInt(DueTerm dueTerm) {
    final int THIRTY_DAYS = 30;
    final int NINETY_DAYS = 90;
    int day = 0;

    switch (dueTerm) {
    case DAYS30:
      day = THIRTY_DAYS;
      break;
    case DAYS90:
      day = NINETY_DAYS;
      break;
    default:
      break;
    }
    return day;
  }
}
