

fun main() {

    fun operateOnNumeber(a:Int, b:Int, operation:(Int, Int) -> Int) : Int{
        return operation(a,b)
    }


    //make a lambda
    val addLamda = {a:Int, b:Int ->
        a*b
    }

    operateOnNumeber(4,2, operation = addLamda)

    // passing lambda as parameter
    operateOnNumeber(5, 2 , operation = {a , b -> a * b})

    //passing function as parameter

    fun addFunction(a:Int, b:Int) = a+b

    operateOnNumeber(4, 6, ::addFunction)
    print(operateOnNumeber(8,2, ::addFunction))

}
