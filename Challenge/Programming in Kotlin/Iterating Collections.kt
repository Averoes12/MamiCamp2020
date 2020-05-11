fun main() {

    /*
    * Challenge:
    * Simulate a game where you have to find the X in a matrix
    * Step 1 Choose Matrix Size e.g 3*3
    * Step 2 Create the matrix
    * Step 3 Create a nested loop, which will fill the matrix with '.' symbols for all the elements,
    * instead of 1 of your choice, which will be an 'X' symbol
    * Step 4 Find the element, and print out its cordinates. Then stop the loop from further looping*/

    
    val matriks = MutableList<MutableList<String>>(4){ MutableList(4) {"."}}

    matriks[1][3] = "X"

    var result = ""

    println("Searching x in .....")
    row@ for (row in 0..matriks.lastIndex){
        column@ for(column in 0..matriks.lastIndex){
            if (matriks[row][column] == "X"){
                print("x \t")
                result = "Finding x at  $row : $column"
                continue@column
            }else{
                print(". \t")
            }
        }
        println()
    }
    println(result)
}