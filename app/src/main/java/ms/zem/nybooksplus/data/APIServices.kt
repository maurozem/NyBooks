package ms.zem.nybooksplus.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object APIServices {

    fun initRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/books/v3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service: NYTServices = initRetrofit().create(NYTServices::class.java)

}