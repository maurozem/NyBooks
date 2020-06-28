package ms.zem.nybooksplus.data.model

import java.io.Serializable

data class Book (
        val title: String,
        val author: String,
        val description: String
) : Serializable