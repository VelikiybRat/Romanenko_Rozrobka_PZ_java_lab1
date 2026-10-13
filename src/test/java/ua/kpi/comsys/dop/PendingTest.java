
package ua.kpi.comsys.dop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тести для запису Pending.
 */
class PendingTest {

    /**
     * Два записи Pending без полів повинні бути рівними.
     */
    @Test
    void testPendingEquality() {
        Pending first = new Pending();
        Pending second = new Pending();

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    /**
     * Перевірка належності до OrderStatus.
     */
    @Test
    void testPendingImplementsOrderStatus() {
        Pending pending = new Pending();

        assertInstanceOf(OrderStatus.class, pending);
    }
}