package pl.jkanclerz.creditcard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAllowsToAssignCreditLimit() {
        //Arrange
        CreditCard card = new CreditCard("1234-5678");
        //Act
        card.assignLimit(BigDecimal.valueOf(1000));
        //Assert // Then

        assertEquals(BigDecimal.valueOf(1000), card.getCurrentBalance());
    }

    @Test
    void itAllowsToAssignDifferentCreditLimit() {
        //Arrange
        CreditCard card1 = new CreditCard("1234-5678");
        CreditCard card2 = new CreditCard("1234-5679");
        //Act
        card1.assignLimit(BigDecimal.valueOf(1000));
        card2.assignLimit(BigDecimal.valueOf(1100));
        //Assert // Then
        assertEquals(BigDecimal.valueOf(1000), card1.getCurrentBalance());
        assertEquals(BigDecimal.valueOf(1100), card2.getCurrentBalance());
    }

    @Test
    void itDenyCreditLimitBelow100v1() {
        CreditCard card1 = new CreditCard("1234-5678");

        try {
            card1.assignLimit(BigDecimal.valueOf(10));
            fail("Should throw exception");
        } catch (CreditBelowLimitException e) {
            assertTrue(true);
        }
    }

    @Test
    void itDenyCreditLimitBelow100() {
        CreditCard card1 = new CreditCard("1234-5678");
        CreditCard card2 = new CreditCard("1234-5678");
        CreditCard card3 = new CreditCard("1234-5678");

        assertThrows(
                CreditBelowLimitException.class,
                () -> card1.assignLimit(BigDecimal.valueOf(10)));

        assertThrows(
                CreditBelowLimitException.class,
                () -> card2.assignLimit(BigDecimal.valueOf(99)));

        assertDoesNotThrow(() -> card3.assignLimit(BigDecimal.valueOf(100)));

        try {
            card1.assignLimit(BigDecimal.valueOf(10));
            fail("Should throw exception");
        } catch (CreditBelowLimitException e) {
            assertTrue(true);
        }
    }

    @Test
    void itCantAssignLimitTwice() {
        CreditCard card1 = new CreditCard("1234-5678");
        card1.assignLimit(BigDecimal.valueOf(1000));

        assertThrows(
                CantReassignCreditExceptions.class,
                () -> card1.assignLimit(BigDecimal.valueOf(1100))
        );
    }

    @Test
    void itAllowsToWithdraw() {
        CreditCard card1 = new CreditCard("1234-5678");
        card1.assignLimit(BigDecimal.valueOf(1000));
        card1.withdraw(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(900), card1.getCurrentBalance());
    }
}
