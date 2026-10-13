
package ua.kpi.comsys.dop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Модульні тести для запису Cancelled.
 *
 * Перевіряють правильність збереження
 * причини скасування та її валідацію.
 */
class CancelledTest {

    /**
     * Перевірка коректної причини скасування.
     */
    @Test
    void testValidCancelled() {
        String reason = "Customer request";

        Cancelled cancelled = new Cancelled(reason);

        assertEquals(reason, cancelled.reason());
    }

    /**
     * Причина скасування не може бути null.
     */
    @Test
    void testNullReason() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cancelled(null)
        );
    }

    /**
     * Причина не може бути порожньою
     * або складатися лише з пробілів.
     */
    @Test
    void testBlankReason() {
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> new Cancelled("")
                ),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> new Cancelled("   ")
                )
        );
    }
}