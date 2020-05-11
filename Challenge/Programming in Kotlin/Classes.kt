/*
    * Challenge
    * Create a class which  represents a Movie, or a Video Game, or a Music.
    * Add a properties like "name", "genre", "length"
    * Add methods format the properties in a string for you to print out
    * Create a few object of the class type & print out their data*/

class Movie(
    private var name:String? ="",
    private var genre: String? = "",
    private var rating:Double = 0.0
){

    fun getName() = name

    fun setName(name: String?){
        this.name = name
    }

    fun getGenre() = genre

    fun setGenre(genre: String?){
        this.genre = genre
    }

    fun getRating() = rating

    fun setRating(rating: Double){
        this.rating = rating
    }

    fun getDetailMovie(){
        println("Title: $name, Genre: $genre, Rating: $rating")
    }
}

fun main(){

    val favoriteMovie = Movie("Avengers:End Game", "Action", 8.6)

    println("I Like action movie such as ${favoriteMovie.getName()}")

    favoriteMovie.getDetailMovie()
}
