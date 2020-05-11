
fun main() {

    /*
    * Create a list of strings, describing items on your table. If items appear more than once, add them multiples item, likes two pens
    * Turn that list into a set, and check if the duplicates appear
    * Remove an item you don't really need on your desk, e.g car keys
    *Iterate over the items, and print them out*/

    val itemsOnDesk = arrayOf("Bottle", "Mug", "Laptop", "Phone", "Charger", "Earphone")

    val myItems = itemsOnDesk.toMutableSet()
    myItems.remove("Mug")

    for (items in myItems){
        println("I have $items on my desk")
    }
}