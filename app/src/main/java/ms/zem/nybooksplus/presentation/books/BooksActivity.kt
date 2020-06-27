package ms.zem.nybooksplus.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_books.*
import ms.zem.nybooksplus.R

class BooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        toolbarMain.title = getString(R.string.books)
        setSupportActionBar(toolbarMain)

        val viewModel: BookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        viewModel.books.observe(this, Observer {
            it?.let {
                with(recyclerMain){
                    layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(it)
                    addItemDecoration(DividerItemDecoration(this@BooksActivity, DividerItemDecoration.VERTICAL))
                }
            }
        })
        viewModel.getBooks()
    }

}