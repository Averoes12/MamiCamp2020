fun main() {

//Print number from 1 to 15 with while loop
/*
* Create an array or list of names
* Using a do-while loop, an iterator, print the names in a reverse order*/

    var i = 1
    while (i < 16){
        println("Number $i")
        i++
    }

    val listOfNames = arrayOf("James", "John", "Clara", "Vita", "Max")

    var name = listOfNames.lastIndex
    do {
        println("${listOfNames[name]} have $name number")
        name--
    }while (name >= 0)
}