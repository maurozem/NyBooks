package ms.zem.nybooksplus.presentation.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ms.zem.nybooksplus.R
import ms.zem.nybooksplus.data.BooksResult
import ms.zem.nybooksplus.data.model.Book
import ms.zem.nybooksplus.data.repository.BooksRepository
import java.lang.IllegalArgumentException

class BookViewModel(val repository: BooksRepository) : ViewModel() {

    private val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val books: LiveData<List<Book>> = booksLiveData
    val flipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks(){
        repository.getBooks {result ->
            when(result){
                is BooksResult.Success -> {
                    booksLiveData.value = result.books
                    flipperLiveData.value = Pair(FLIPPER_BOOKS, null)
                }
                is BooksResult.ApiError -> {
                    if (result.error == 401){
                        flipperLiveData.value = Pair(FLIPPER_ERROR, R.string.error401)
                    } else {
                        flipperLiveData.value = Pair(FLIPPER_ERROR, R.string.error400)
                    }
                }
                is BooksResult.ServerError -> {
                    flipperLiveData.value = Pair(FLIPPER_ERROR, R.string.error500)
                }
            }
        }
    }

    class ViewModelFactory(private val repository: BooksRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BookViewModel::class.java)){
                return BookViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknowm ViewModel Class")
        }

    }

    companion object{
        private const val FLIPPER_BOOKS = 1
        private const val FLIPPER_ERROR = 2
    }

}