package pl.jkanclerz.creditcard;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal balance;
    private BigDecimal credit;

    public CreditCard(String cardNumber) {

    }

    public void assignLimit(BigDecimal creditAmount) {
        if (credit != null) {
            throw new CantReassignCreditExceptions();
        }

        if (creditAmount.compareTo(BigDecimal.valueOf(100)) < 0) {
            throw new CreditBelowLimitException();
        }
        this.balance = creditAmount;
        this.credit = creditAmount;

    }

    public BigDecimal getCurrentBalance() {
        return balance;
    }

    public void withdraw(BigDecimal value) {
        this.balance = balance.subtract(value);
    }
}
