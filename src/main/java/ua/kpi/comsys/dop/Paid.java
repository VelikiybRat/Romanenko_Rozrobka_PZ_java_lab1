package ua.kpi.comsys.dop;

import java.math.BigDecimal;

/**
 * Статус оплаченого замовлення.
 *
 * Зберігає ідентифікатор платежу та його суму.
 * Виконує перевірку даних під час створення об'єкта.
 */
public record Paid(String paymentId, BigDecimal amount)
        implements OrderStatus {

    /**
     * Компактний конструктор record.
     *
     * @throws IllegalArgumentException якщо дані некоректні.
     */
    public Paid {
        // Перевірка ідентифікатора платежу.
        if (paymentId == null || paymentId.isBlank()) {
            throw new IllegalArgumentException(
                    "Payment ID cannot be null or blank"
            );
        }

        // Перевірка суми платежу.
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero"
            );
        }
    }
}