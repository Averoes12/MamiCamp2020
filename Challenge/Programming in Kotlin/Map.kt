import com.sun.xml.internal.bind.v2.runtime.reflect.opt.FieldAccessor_Short

fun main() {

    /*
    * Create a map where each key is the name of a pet you have/had, and the value is animal type that pet is.
    * e.g "Max" , "Dog"
    * Add the values to the map, then removing a value, and finally, iterate over the map,
    * printing out the entries*/

    val myPet = mutableMapOf<String, String>()

    myPet["Mini Landak"] = "Tobi"
    myPet["Hamster"] = "Popi"
    myPet["Persia Cat"]  = "Uni"
    myPet["Fish"] = "Anto"

    myPet.remove("Fish")

    println(myPet)

    for ((pet, name) in myPet) {
        println("Name : $name\n" +
                "Spesies : $pet \n")
    }
}