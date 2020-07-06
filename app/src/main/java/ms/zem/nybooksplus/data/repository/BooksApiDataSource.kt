package ms.zem.nybooksplus.data.repository

import ms.zem.nybooksplus.data.APIServices
import ms.zem.nybooksplus.data.BooksResult
import ms.zem.nybooksplus.data.model.Book
import ms.zem.nybooksplus.data.response.BookBodyResponse
import ms.zem.nybooksplus.data.response.toBook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksApiDataSource : BooksRepository{

    override fun getBooks(bookResultCallBack: (result: BooksResult) -> Unit) {
        APIServices.service.getBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val mybooks: MutableList<Book> = mutableListOf()
                        response.body()?.let {
                            for (result in it.bookResults) {
                                mybooks.add(result.toBook())
                            }
                        }
                        bookResultCallBack(BooksResult.Success(mybooks))
                    }
                    else -> {
                        bookResultCallBack(BooksResult.ApiError(response.code()))
                    }
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                bookResultCallBack(BooksResult.ServerError)
            }
        })
    }
}