
package ua.kpi.comsys.dop;

import java.time.LocalDate;

/**
 * Статус відправленого замовлення.
 *
 * Містить трек-номер посилки та дату її відправлення.
 */
public record Shipped(String trackingCode, LocalDate dispatchDate)
        implements OrderStatus {

    /**
     * Компактний конструктор для перевірки даних.
     *
     * @throws IllegalArgumentException якщо дані некоректні.
     */
    public Shipped {
        // Перевіряємо трек-номер.
        if (trackingCode == null || trackingCode.isBlank()) {
            throw new IllegalArgumentException(
                    "Tracking code cannot be null or blank"
            );
        }

        // Перевіряємо дату відправлення.
        if (dispatchDate == null) {
            throw new IllegalArgumentException(
                    "Dispatch date cannot be null"
            );
        }
    }
}