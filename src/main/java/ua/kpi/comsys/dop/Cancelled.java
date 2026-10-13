
package ua.kpi.comsys.dop;

/**
 * Статус скасованого замовлення.
 *
 * Зберігає причину скасування замовлення.
 */
public record Cancelled(String reason)
        implements OrderStatus {

    /**
     * Компактний конструктор для перевірки причини.
     *
     * @throws IllegalArgumentException якщо причина
     *         відсутня або містить лише пробіли.
     */
    public Cancelled {
        // Перевіряємо причину скасування.
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException(
                    "Cancellation reason cannot be null or blank"
            );
        }
    }
}