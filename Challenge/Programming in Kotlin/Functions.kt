
fun main() {

    fun fullName(firstName:String, lastName:String = "")= println("${firstName.length + lastName.length}")
    fun fullName(firstName:String,middleNames:List<String> ,lastName:String = ""){

        var middleNamesLength = 0
        for (middleName in middleNames){
            middleNamesLength += middleName.length
        }

        return println("${firstName.length + middleNamesLength + lastName.length}")
    }

    fullName("Fawwaz", listOf("Daffa"), "Al Sayyaf")
    fullName("John", "Doe")
}