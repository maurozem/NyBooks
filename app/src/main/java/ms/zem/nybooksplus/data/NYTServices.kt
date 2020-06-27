package ms.zem.nybooksplus.data

import ms.zem.nybooksplus.data.model.Book
import retrofit2.Call
import retrofit2.http.GET

interface NYTServices {

    @GET("lists.json")
    fun listRepos(): Call<List<Book>>

}