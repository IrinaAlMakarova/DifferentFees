fun main() {
    cardType("Mastercard", 0, 150_000)
}

fun cardType(card: String = "Мир", amountOfPreviousTransfers: Int = 0, transferAmount: Int) {
    val maximumTransferDay = 150_000
    val maximumTransferMonth = 600_000
    if ((amountOfPreviousTransfers + transferAmount) > maximumTransferMonth || transferAmount > maximumTransferDay) {
        return println("Карта заблокирована")
    } else {
        val commission = when {
            // За переводы с карты Mastercard комиссия не взимается, пока не превышен месячный лимит в 75 000 руб.
            // Если лимит превышен, комиссия составит 0,6% + 20 руб.
            card == "Mastercard" &&
                    amountOfPreviousTransfers > 75_000 -> (0.006 * transferAmount + 20)
            
            card == "Mastercard" &&
                    amountOfPreviousTransfers <= 75_000 &&
                    (amountOfPreviousTransfers + transferAmount) > 75_000 -> (0.006 * ((amountOfPreviousTransfers + transferAmount) - 75_000) + 20)

            card == "Mastercard" &&
                    amountOfPreviousTransfers <= 75_000 &&
                    (amountOfPreviousTransfers + transferAmount) <= 75_000 -> 0

            //За переводы с карты Visa комиссия составит 0,75%, минимальная сумма комиссии 35 руб.
            card == "Visa" -> (if (0.0075 * transferAmount < 35) 35 else (0.0075 * transferAmount))

            else -> 0
        }
        return println("Сумма комиссии составит " + commission)
    }
}
