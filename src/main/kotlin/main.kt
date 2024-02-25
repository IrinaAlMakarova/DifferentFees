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
                    amountOfPreviousTransfers > 75_000 -> (0.006 * transferAmount + 20)

            card == "Mastercard" &&
                    amountOfPreviousTransfers <= 75_000 &&
                    (amountOfPreviousTransfers + transferAmount) > 75_000 -> (0.006 * ((amountOfPreviousTransfers + transferAmount) - 75_000) + 20)

            card == "Mastercard" &&
                    amountOfPreviousTransfers <= 75_000 &&
                    (amountOfPreviousTransfers + transferAmount) <= 75_000 -> 0

            card == "Visa" &&
                    amountOfPreviousTransfers > maximumTransferMonth -> (if (0.0075 * transferAmount < 35) 35 else (0.0075 * transferAmount))

            card == "Visa" &&
                    amountOfPreviousTransfers <= maximumTransferMonth &&
                    (amountOfPreviousTransfers + transferAmount) > maximumTransferMonth -> (if (0.0075 * ((amountOfPreviousTransfers + transferAmount) - maximumTransferMonth) < 35) 35 else (0.0075 * ((amountOfPreviousTransfers + transferAmount) - maximumTransferMonth)))

            card == "Visa" &&
                    amountOfPreviousTransfers <= maximumTransferMonth &&
                    (amountOfPreviousTransfers + transferAmount) <= maximumTransferMonth -> 35

            else -> 0
        }
        return println(commission)
    }
}
