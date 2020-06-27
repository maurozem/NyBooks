package ms.zem.nybooksplus.presentation.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ms.zem.nybooksplus.data.model.Book

class BookViewModel : ViewModel() {

    private val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val books: LiveData<List<Book>> = booksLiveData

    fun getBooks(){
        booksLiveData.value = getBooksMock()
    }

    private fun getBooksMock() = listOf<Book>(
            Book("Titulo 01", "Autor 01"),
            Book("Titulo 02", "Autor 02"),
            Book("Titulo 03", "Autor 03")
    )
}