# Лабораторна робота №1 — Data-Oriented Programming у Java

**Дисципліна:** Розробка програмного забезпечення на платформі Java (2026)
**Тема:** Сучасні можливості Java та підхід Data-Oriented Programming (DOP)
**Виконав:** Романенко Іван Євгенович
**Група:** ІО-45
**Заклад освіти:** КПІ ім. Ігоря Сікорського

## Мета роботи

Навчитися моделювати незмінні дані за допомогою `record` і `sealed interface`, відокремлювати дані від алгоритмів відповідно до підходу Data-Oriented Programming, застосовувати `switch` expressions з pattern matching і форматувати результати за допомогою Text Blocks.

## Визначення варіанта

Варіант визначається остачею від ділення номера залікової книжки на 3.

- Номер залікової книжки: **4509**.
- Формула: `V = N mod 3`.
- Обчислення: `4509 mod 3 = 0`.

**Варіант 0 — система управління замовленнями.**

## Опис реалізації

Проєкт моделює чотири стани замовлення через `sealed interface OrderStatus` і публічні незмінні записи (`record`):

| Тип | Призначення | Дані |
|---|---|---|
| `Pending` | Замовлення очікує обробки | Додаткових полів немає |
| `Paid` | Замовлення оплачено | `paymentId`, `amount` |
| `Shipped` | Замовлення відправлено | `trackingCode`, `dispatchDate` |
| `Cancelled` | Замовлення скасовано | `reason` |

Усі типи розташовано в пакеті `ua.kpi.comsys.dop`. У конструкторах записів `Paid`, `Shipped` і `Cancelled` перевіряються обов'язкові значення та допустимість даних. Некоректні дані спричиняють `IllegalArgumentException`.

Бізнес-логіку винесено до класу `OrderProcessor`, метод `process(OrderStatus status)` якого:

- відхиляє `null` винятком `IllegalArgumentException("Status cannot be null")`;
- використовує вичерпний `switch` expression із pattern matching для всіх чотирьох типів, без `default`;
- повертає повідомлення у форматі, заданому методичкою, використовуючи Java Text Blocks.

Приклад обробки оплаченої заявки:

```java
import java.math.BigDecimal;

OrderProcessor processor = new OrderProcessor();
Paid status = new Paid("PAY123", new BigDecimal("250.50"));
System.out.print(processor.process(status));
```

Вивід:

```text
Order Status: PAID
Details: Payment ID: PAY123, Amount: $250.50
```


## Структура проєкту

```text
Romanenko_Lab1_java/
├── src/
│   ├── main/java/ua/kpi/comsys/dop/
│   │   ├── OrderStatus.java
│   │   ├── Pending.java
│   │   ├── Paid.java
│   │   ├── Shipped.java
│   │   ├── Cancelled.java
│   │   └── OrderProcessor.java
│   └── test/java/ua/kpi/comsys/dop/
│       ├── PendingTest.java
│       ├── PaidTest.java
│       ├── ShippedTest.java
│       ├── CancelledTest.java
│       └── OrderProcessorTest.java
├── pom.xml
├── .gitignore
└── README.md
```

## Середовище та технології

- **Java:** JDK 25; цільовий рівень компіляції — Java 23 (`maven.compiler.release=23`).
- **Система збирання:** Apache Maven.
- **Тестування:** JUnit Jupiter 5.11.4.
- **Покриття:** JaCoCo Maven Plugin 0.8.15.
- **Середовище розробки:** IntelliJ IDEA.
- **Контроль версій:** Git.

## Запуск перевірок

Для виконання тестів потрібні JDK 23 або новіший і Maven. Із кореня проєкту виконайте:

```bash
mvn clean verify
```

Якщо Maven не доступний у системній змінній `PATH`, у **IntelliJ IDEA** відкрийте панель **Maven → Lifecycle**, послідовно запустіть **clean** і **verify**.

- `test` запускає тести JUnit;
- `verify` додатково створює звіт JaCoCo;
- звіт покриття відкривається з файлу `target/site/jacoco/index.html` після успішного виконання `verify`.

Папка `target/` містить результати збирання й не повинна зберігатися у Git.

## Результати тестування та покриття

У проєкті реалізовано **19 тестових методів у 5 класах**:

| Тестовий клас | Кількість тестів | Що перевіряється |
|---|---:|---|
| `PendingTest` | 2 | Створення й належність до `OrderStatus` |
| `PaidTest` | 5 | Коректні дані та валідація платежу |
| `ShippedTest` | 4 | Коректні дані та валідація відправлення |
| `CancelledTest` | 3 | Коректні дані та валідація причини скасування |
| `OrderProcessorTest` | 5 | Чотири гілки обробки та виняток для `null` |
| **Разом** | **19** | |

**Підтверджені результати Maven (`verify`):** 19 тестів, 0 failures, 0 errors, 0 skipped, `BUILD SUCCESS`.

**Звіт JaCoCo для пакета `ua.kpi.comsys.dop`:**

| Метрика | Покрито | Покриття |
|---|---:|---:|
| Інструкції | 150 / 150 | 100% |
| Гілки | 24 / 24 | 100% |
| Рядки | 30 / 30 | 100% |
| Методи | 6 / 6 | 100% |
| Класи | 5 / 5 | 100% |

У `Pending` немає умовних гілок, тому для нього покриття гілок позначається як `N/A`. Зазначені показники отримано для поточної версії коду; після змін потрібно повторно виконати `verify`. Повне структурне покриття не гарантує відсутності всіх можливих логічних помилок.

## Висновок

У межах лабораторної роботи реалізовано незмінну предметну модель статусів замовлень та окремий обробник бізнес-логіки згідно з принципами DOP. Використано `record`, `sealed interface`, pattern matching, `switch` expressions і Text Blocks. Коректність обробки передбачених станів і валідації перевірено автоматизованими тестами JUnit 5; покриття коду виміряно інструментом JaCoCo.
