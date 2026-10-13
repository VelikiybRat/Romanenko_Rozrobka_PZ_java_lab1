package ua.kpi.comsys.dop;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Модульні тести для запису Paid.
 *
 * Перевіряють коректне створення об'єкта
 * та валідацію його полів.
 */
class PaidTest {

    /**
     * Перевірка створення платежу з коректними даними.
     */
    @Test
    void testValidPaid() {
        BigDecimal amount = new BigDecimal("250.50");
        Paid paid = new Paid("PAY123", amount);

        assertAll(
                () -> assertEquals("PAY123", paid.paymentId()),
                () -> assertEquals(amount, paid.amount())
        );
    }

    /**
     * Ідентифікатор платежу не може бути null.
     */
    @Test
    void testNullPaymentId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Paid(null, BigDecimal.TEN)
        );
    }

    /**
     * Ідентифікатор не може бути порожнім
     * або містити лише пробіли.
     */
    @Test
    void testBlankPaymentId() {
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> new Paid("", BigDecimal.TEN)
                ),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> new Paid("   ", BigDecimal.TEN)
                )
        );
    }

    /**
     * Сума платежу не може бути null.
     */
    @Test
    void testNullAmount() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Paid("PAY123", null)
        );
    }

    /**
     * Сума платежу повинна бути більшою за нуль.
     */
    @Test
    void testNonPositiveAmount() {
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> new Paid("PAY123", BigDecimal.ZERO)
                ),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> new Paid(
                                "PAY123",
                                new BigDecimal("-10")
                        )
                )
        );
    }
}