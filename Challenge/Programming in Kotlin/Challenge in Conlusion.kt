fun main() {
    /*
    * 1. Change the value using of an element by accessing them using an index
    * 2. Add one element, and remove one element
    * 3. Print the changed element List to check that all the elements are there
    * 4. After removing an element, check if it's in the list, and print the result*/

    val city = arrayOf("London", "New York", "Hiroshima", "Nagasaki", "Seoul")
    city[2] = "Tokyo"

    val visitedCity = city.toMutableList()
    visitedCity.add(4, "Jakarta")
    visitedCity.remove("London")

    println("Visited City $visitedCity")
    println("London" in visitedCity)
}