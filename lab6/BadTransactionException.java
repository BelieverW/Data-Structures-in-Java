/* BadTransactionException.java */

/**
 *  Implements an exception that should be thrown for nonsensible amount of money.
 **/
public class BadTransactionException extends Exception {

  public int money;  // The invalid amount of money.

  /**
   *  Creates an exception object for nonsensible amount of money "badmoney".
   **/
  public BadTransactionException(int badmoney) {
    super("Invalid amount of money: " + badmoney);

    money = badmoney;
  }
}
