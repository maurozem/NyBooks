package ms.zem.nybooksplus.data.repository

import ms.zem.nybooksplus.data.BooksResult

interface BooksRepository {

    fun getBooks(bookResultCallBack: (result: BooksResult) -> Unit)

}