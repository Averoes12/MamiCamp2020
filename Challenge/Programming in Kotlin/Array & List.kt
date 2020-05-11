fun main() {

    val favoriteGames = arrayOf("NFS Payback", "One Piece", "Lost Saga", "Point Blank", "PUBG Mobile")
//    Transform array to list
    val myGame = favoriteGames.toMutableList()

    favoriteGames[3] = "RF Online"
    myGame.addAll(4, listOf("Heroes Evolved", "Mobile Legends"))
    myGame.remove("Lost Saga")

    println(favoriteGames[1])
    println(myGame)
//    Check element in list
    println("Lost Saga" in myGame)

}