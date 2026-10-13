
package ua.kpi.comsys.dop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Модульні тести для класу OrderProcessor.
 *
 * Перевіряють усі гілки switch expression,
 * правильність форматування Text Blocks
 * та обробку значення null.
 */
class OrderProcessorTest {

    private final OrderProcessor processor =
            new OrderProcessor();

    /**
     * Перевірка статусу очікування обробки.
     */
    @Test
    void testPending() {
        String expected = """
                Order Status: PENDING
                Details: Order is currently pending processing.
                """;

        String actual = processor.process(new Pending());

        assertEquals(expected, actual);
    }

    /**
     * Перевірка форматування оплаченого замовлення.
     */
    @Test
    void testPaid() {
        Paid paid = new Paid(
                "PAY123",
                new BigDecimal("250.50")
        );

        String expected = """
                Order Status: PAID
                Details: Payment ID: PAY123, Amount: $250.50
                """;

        String actual = processor.process(paid);

        assertEquals(expected, actual);
    }

    /**
     * Перевірка форматування відправленого замовлення.
     */
    @Test
    void testShipped() {
        Shipped shipped = new Shipped(
                "TR12345",
                LocalDate.of(2026, 10, 13)
        );

        String expected = """
                Order Status: SHIPPED
                Details: Tracking Code: TR12345, Dispatch Date: 2026-10-13
                """;

        String actual = processor.process(shipped);

        assertEquals(expected, actual);
    }

    /**
     * Перевірка форматування скасованого замовлення.
     */
    @Test
    void testCancelled() {
        Cancelled cancelled =
                new Cancelled("Customer request");

        String expected = """
                Order Status: CANCELLED
                Details: Reason: Customer request
                """;

        String actual = processor.process(cancelled);

        assertEquals(expected, actual);
    }

    /**
     * Перевірка обробки null.
     *
     * Метод повинен генерувати IllegalArgumentException
     * з точним текстом із методички.
     */
    @Test
    void testNullStatus() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> processor.process(null)
        );

        assertEquals(
                "Status cannot be null",
                exception.getMessage()
        );
    }
}