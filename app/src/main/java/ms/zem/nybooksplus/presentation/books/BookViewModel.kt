package ms.zem.nybooksplus.presentation.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ms.zem.nybooksplus.R
import ms.zem.nybooksplus.data.APIServices
import ms.zem.nybooksplus.data.model.Book
import ms.zem.nybooksplus.data.response.BookBodyResponse
import ms.zem.nybooksplus.data.response.toBook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel : ViewModel() {

    private val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val books: LiveData<List<Book>> = booksLiveData
    val flipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks(){
        APIServices.service.getBooks().enqueue(object : Callback<BookBodyResponse>{
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val mybooks : MutableList<Book> = mutableListOf()
                        response.body()?.let {
                            for (result in it.bookResults){
                                mybooks.add(result.toBook())
                            }
                        }
                        booksLiveData.value = mybooks
                        flipperLiveData.value = Pair(FLIPPER_BOOKS, null)
                    }
                    response.code() == 401 -> {
                        flipperLiveData.value = Pair(FLIPPER_ERROR, R.string.error401)
                    }
                    else -> {
                        flipperLiveData.value = Pair(FLIPPER_ERROR, R.string.error400)
                    }
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                flipperLiveData.value = Pair(FLIPPER_ERROR, R.string.error500)
            }
        })
    }

    companion object{
        private const val FLIPPER_BOOKS = 1
        private const val FLIPPER_ERROR = 2
    }

}