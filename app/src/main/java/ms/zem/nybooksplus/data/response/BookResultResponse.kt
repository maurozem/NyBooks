package ms.zem.nybooksplus.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ms.zem.nybooksplus.data.model.Book

@JsonClass(generateAdapter = true)
data class BookResultResponse (
    @Json(name = "book_details")
    val bookDetais: List<BookDetaisResponse>
)

fun BookResultResponse.toBook(): Book {
    return Book(
        bookDetais[0].title,
        bookDetais[0].author,
        bookDetais[0].description
    )
}