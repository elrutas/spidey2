package com.example.lucas.spidey2.ui.features.comiclist.adapter.items

open class ComicListItem(val type: Type) {

    enum class Type(val value: Int) {
        COMIC(0),
        LOADING_ITEM(1)
    }
}