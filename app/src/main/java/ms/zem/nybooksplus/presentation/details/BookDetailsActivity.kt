package ms.zem.nybooksplus.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_book_details.tvDescricao
import kotlinx.android.synthetic.main.activity_book_details.tvTitulo
import kotlinx.android.synthetic.main.activity_book_details.tvAutor
import kotlinx.android.synthetic.main.toolbar.toolbar
import ms.zem.nybooksplus.R
import ms.zem.nybooksplus.data.model.Book
import ms.zem.nybooksplus.presentation.base.BaseActivity

class BookDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        toolbar?.let {
            setupToolbar(it, R.string.book_detail)
        } ?: setupToolbar(findViewById(R.id.toolbar), R.string.book_detail)


        val book = intent.getSerializableExtra(BOOK) as Book
        tvTitulo.text = book.title
        tvAutor.text = book.author
        tvDescricao.text = book.description
    }

    companion object{
        const val BOOK = "book"
        fun getStartIntent(context: Context, book: Book): Intent{
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra(BOOK, book)
            }

        }
    }
}