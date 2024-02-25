fun main() {
    cardType("Mastercard", 0, 150_000)
}

fun cardType(card: String = "Мир", amountOfPreviousTransfers: Int = 0, transferAmount: Int) {
    val maximumTransferDay = 150_000
    val maximumTransferMonth = 600_000
    if (amountOfPreviousTransfers > maximumTransferMonth || transferAmount > maximumTransferDay) {
        return println("Карта заблокирована")
    } else {
        val commission = when {
            card == "Mastercard" &&
                    (amountOfPreviousTransfers + transferAmount) > 75_000 -> (0.006 * ((amountOfPreviousTransfers + transferAmount) - 75_000) + 20)

            card == "Mastercard" &&
                    (amountOfPreviousTransfers + transferAmount) <= 75_000 -> 0

            card == "Visa" -> (if (0.0075 * transferAmount < 35) 35 else (0.0075 * transferAmount))

            else -> 0
        }
        return println("Сумма комиссии составит " + commission)
    }
}
