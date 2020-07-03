package ms.zem.nybooksplus.presentation.books

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.toolbar.toolbar
import ms.zem.nybooksplus.R
import ms.zem.nybooksplus.presentation.base.BaseActivity
import ms.zem.nybooksplus.presentation.details.BookDetailsActivity
import kotlin.time.seconds

class BooksActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(toolbar, R.string.books)

        val viewModel: BookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)


        viewModel.flipperLiveData.observe(this, Observer {
            it?.let { pair ->
                flipper.displayedChild = pair.first
                pair.second?.let { idTexto ->
                    textError.text = getString(idTexto)
                }
            }
        })
        viewModel.books.observe(this, Observer {
            it?.let {
                val dividerItemDetailsActivity = DividerItemDecoration(this@BooksActivity, DividerItemDecoration.VERTICAL)
                baseContext.getDrawable(R.drawable.divider)?.let {
                    dividerItemDetailsActivity.setDrawable(it)
                }
                with(recyclerMain){
                    layoutManager = LinearLayoutManager(
                        this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(it){book ->
                        startActivity(BookDetailsActivity.getStartIntent(
                            this@BooksActivity, book))
                    }
                    addItemDecoration(dividerItemDetailsActivity)
                }
            }
        })
        viewModel.getBooks()
    }




}