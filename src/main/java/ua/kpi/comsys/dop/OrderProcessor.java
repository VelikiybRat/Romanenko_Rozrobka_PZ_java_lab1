
package ua.kpi.comsys.dop;

/**
 * Клас для обробки статусів замовлень.
 *
 * Бізнес-логіка відокремлена від класів даних
 * відповідно до принципів Data-Oriented Programming.
 */
public class OrderProcessor {

    /**
     * Обробляє статус замовлення та формує повідомлення.
     *
     * @param status статус замовлення
     * @return відформатований текст статусу
     * @throws IllegalArgumentException якщо status == null
     */
    public String process(OrderStatus status) {

        // Перевірка вхідного параметра.
        if (status == null) {
            throw new IllegalArgumentException(
                    "Status cannot be null"
            );
        }

        // Обробка всіх можливих статусів замовлення.
        return switch (status) {

            // Замовлення очікує обробки.
            case Pending pending -> """
                    Order Status: PENDING
                    Details: Order is currently pending processing.
                    """;

            // Замовлення оплачено.
            case Paid paid -> """
                    Order Status: PAID
                    Details: Payment ID: %s, Amount: $%s
                    """.formatted(paid.paymentId(), paid.amount());

            // Замовлення відправлено.
            case Shipped shipped -> """
                    Order Status: SHIPPED
                    Details: Tracking Code: %s, Dispatch Date: %s
                    """.formatted(
                    shipped.trackingCode(),
                    shipped.dispatchDate()
            );

            // Замовлення скасовано.
            case Cancelled cancelled -> """
                    Order Status: CANCELLED
                    Details: Reason: %s
                    """.formatted(cancelled.reason());
        };
    }
}