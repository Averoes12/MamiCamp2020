fun main() {

/*
* Create a range of 20 number, and iterate over it, printing out the number*/

    for(number in 0..20){
        print("$number ")
    }

    println("")
/*
* Iterate over the range in challenge 1 again, but print every third number*/

    for (number in 0..20 step 3){
        print("$number ")
    }

    println("")
/*
* Create  a decreasing range of 15 numbers, and print every second number*/

    for (number in 15 downTo 0 step 2){
        print("$number ")
    }
}

