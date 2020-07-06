package ms.zem.nybooksplus.data

import ms.zem.nybooksplus.data.model.Book

sealed class BooksResult {

    class Success(val books: List<Book>): BooksResult()
    class ApiError(val error: Int): BooksResult()
    object ServerError : BooksResult()

}