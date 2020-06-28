package ms.zem.nybooksplus.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_book_details.*
import ms.zem.nybooksplus.R
import ms.zem.nybooksplus.data.model.Book

class BookDetailsActivity : AppCompatActivity() {

    lateinit var book: Book
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

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