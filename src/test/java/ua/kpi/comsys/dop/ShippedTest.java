
package ua.kpi.comsys.dop;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Модульні тести для запису Shipped.
 *
 * Перевіряють трек-номер відправлення,
 * дату відправлення та їхню валідацію.
 */
class ShippedTest {

    /**
     * Перевірка створення коректного відправлення.
     */
    @Test
    void testValidShipped() {
        LocalDate date = LocalDate.of(2026, 10, 13);

        Shipped shipped = new Shipped("TR12345", date);

        assertAll(
                () -> assertEquals(
                        "TR12345",
                        shipped.trackingCode()
                ),
                () -> assertEquals(
                        date,
                        shipped.dispatchDate()
                )
        );
    }

    /**
     * Трек-номер не може бути null.
     */
    @Test
    void testNullTrackingCode() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Shipped(
                        null,
                        LocalDate.of(2026, 10, 13)
                )
        );
    }

    /**
     * Трек-номер не може бути порожнім
     * або складатися лише з пробілів.
     */
    @Test
    void testBlankTrackingCode() {
        LocalDate date = LocalDate.of(2026, 10, 13);

        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> new Shipped("", date)
                ),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> new Shipped("   ", date)
                )
        );
    }

    /**
     * Дата відправлення не може бути null.
     */
    @Test
    void testNullDispatchDate() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Shipped("TR12345", null)
        );
    }

    /**
     * Перевірка трек-номера, який містить
     * лише символи табуляції або нового рядка.
     */
    @Test
    void testWhitespaceTrackingCode() {
        LocalDate date = LocalDate.of(2026, 10, 13);

        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> new Shipped("\t", date)
                ),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> new Shipped("\n", date)
                )
        );
    }
}