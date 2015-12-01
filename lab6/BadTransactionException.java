/* BadTransactionException.java */

/**
 *  Implements an exception that should be thrown for nonexistent accounts.
 **/
public class BadTransactionException extends Exception {

  public int money;  // The invalid account number.

  /**
   *  Creates an exception object for nonexistent account "badAcctNumber".
   **/
  public BadTransactionException(int badmoney) {
    super("Invalid amount of money: " + badmoney);

    money = badmoney;
  }
}
