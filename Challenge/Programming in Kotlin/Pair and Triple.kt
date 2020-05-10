fun main(){

    val creditCard = Triple(28012,433,"MasterCard")
    val bankAccount = Pair(24000,"Diligent")

    val (balance, bankName) = bankAccount
    val (cardNumber, securityCode, typeCard) = creditCard

    println("Information Bank Account \n" +
            "Bank: $bankName Bank \n" +
            "Balance: $balance \n" +
            "Card Number: $cardNumber \n" +
            "Security Code: $securityCode \n" +
            "Card Type: $typeCard")

}