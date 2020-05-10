fun main(){

    val password:String? = "0jsjs9"
    val passwordLength = password!!.length ?: 0

    val message = if(password!!.isEmpty()){
        "Fill password"
        }else if (passwordLength >=0 && passwordLength <= 4){
        "Good Password"
    }else if (passwordLength >=0 && passwordLength <= 6){
        "Strong Password"
    }else {
        "Very Strong Password"
    }

    println(message)
}