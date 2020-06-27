package ms.zem.nybooksplus.data

import ms.zem.nybooksplus.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "9HKL6x0eXcQZn998Kl1Dhf4MALy2An5k",
        @Query("list") list : String = "hardcover-fiction"
    ): Call<List<BookBodyResponse>>

}