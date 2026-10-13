package ua.kpi.comsys.dop;

/**
 * Інтерфейс для представлення статусів замовлення.
 *
 * Дозволяє реалізацію лише чотирьом типам:
 * Pending, Paid, Shipped та Cancelled.
 */
public sealed interface OrderStatus
        permits Pending, Paid, Shipped, Cancelled {
}