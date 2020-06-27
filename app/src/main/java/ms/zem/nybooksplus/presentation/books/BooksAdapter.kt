package ms.zem.nybooksplus.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_book.view.*
import ms.zem.nybooksplus.R
import ms.zem.nybooksplus.data.model.Book

class BooksAdapter(
        private val books: List<Book>
): RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(view);
    }

    override fun getItemCount() = books.count()

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    class BooksViewHolder(view: View): RecyclerView.ViewHolder(view){

        val titulo = view.textViewTitulo
        val autor = view.textViewAutor

        fun bindView(book: Book){
            titulo.text = book.title
            autor.text = book.author
        }
    }
}