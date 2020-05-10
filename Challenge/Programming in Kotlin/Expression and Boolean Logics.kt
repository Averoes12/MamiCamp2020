fun main(){

    val username = "jhondoe"
    val password = "0987654321"
    val email = "jhondoe@gmail.com"

    if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
        println("You must filled the data!")
    }else if (username.length < 6){
        println("Username must at least 6 characters")
    }else if (password.length < 10){
        println("Password must at least 10 characters")
    }else if (!email.contains("@") || !email.contains(".")){
        println("Email must be contain \"@\" and \".\" ")
    }else{
        println("Registration Success")
    }
}