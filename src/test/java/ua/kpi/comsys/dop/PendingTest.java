
package ua.kpi.comsys.dop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Модульні тести для запису Pending.
 *
 * Перевіряють створення статусу очікування
 * та його належність до ієрархії OrderStatus.
 */
class PendingTest {

    /**
     * Перевірка створення запису Pending.
     */
    @Test
    void testPendingCreation() {
        Pending pending = new Pending();

        assertNotNull(pending);
    }

    /**
     * Перевірка реалізації інтерфейсу OrderStatus.
     */
    @Test
    void testPendingImplementsOrderStatus() {
        OrderStatus status = new Pending();

        assertInstanceOf(Pending.class, status);
    }
}