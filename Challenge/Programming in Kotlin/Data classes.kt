/*
    * Challenge
    * Change the class from the previous challenge to be a 'data' class instead
    * and make sure the class is immutable
    * Create a copy of certain object you created, and compare it for data equality and identity
    * De structure the object, and use the properties to print out its data*/

data class Movie(
    val name: String? = "",
    val genre: String? = "",
    val rating: Double = 0.0
)

fun main() {

    val favoriteMovie = Movie("Avengers:End Game", "Action", 8.6)
    val mostPlayedMovie = favoriteMovie.copy(name = "I am Number 4", genre = "Action", rating = 7.4)

    val (titleMovie, genreMovie, ratingMovie) = mostPlayedMovie

    println(favoriteMovie)
    println(mostPlayedMovie)
    println("The title of movie is $titleMovie and its genre is $genreMovie and for rating is $ratingMovie")


    println(favoriteMovie == mostPlayedMovie)
    println(favoriteMovie === mostPlayedMovie)
}
