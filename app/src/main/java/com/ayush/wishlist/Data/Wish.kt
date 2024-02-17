package com.ayush.wishlist.Data


data class Wish(
    val id : Long = 0L ,
    val title : String = "",
    val description : String = ""
)

object DummyWish {
    val wishList = listOf(
        Wish(title = "GOOGLE watch Pixel 2" ,
            description = "an Android Watch Bla Bla bla"),
        Wish(title = "Apple iPhone 13" ,
            description = "the latest iPhone model"),
        Wish(title = "Nintendo Switch" ,
            description = "a gaming console from Nintendo"),
        Wish(title = "Sony WH-1000XM4" ,
            description = "a popular pair of noise-canceling headphones")
    )
}
