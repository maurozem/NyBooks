package ms.zem.nybooksplus.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import ms.zem.nybooksplus.R
import ms.zem.nybooksplus.data.BooksResult
import ms.zem.nybooksplus.data.model.Book
import ms.zem.nybooksplus.data.repository.BooksRepository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private lateinit var viewModel: BookViewModel

//    Outra forma de inicializar o mockito
//
//    @Before
//    fun setup(){
//        MockitoAnnotations.initMocks(this)
//    }

    @Test
    fun `when viewmodel getBooks get success then set books list`() {
        // Arrange
        val books = listOf(
            Book("titulo 01", "autor01", "escricao01")
        )
        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel = BookViewModel(resultSuccess)
        viewModel.books.observeForever(booksLiveDataObserver)
        viewModel.flipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when viewmodel getBooks get server error then set flipperLiveData`() {
        // Arrange
        val resultServerError = MockRepository(BooksResult.ServerError)
        viewModel = BookViewModel(resultServerError)
        viewModel.flipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error500))
    }

    @Test
    fun `when viewmodel getBooks get server error then set flipper error 401`() {
        // Arrange
        val resultApiError = MockRepository(BooksResult.ApiError(401))
        viewModel = BookViewModel(resultApiError)
        viewModel.flipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error401))
    }
}

private class MockRepository(private val result: BooksResult) : BooksRepository {
    override fun getBooks(bookResultCallBack: (result: BooksResult) -> Unit) {
        bookResultCallBack(result)
    }
}