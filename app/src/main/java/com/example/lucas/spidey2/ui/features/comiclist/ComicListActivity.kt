package com.example.lucas.spidey2.ui.features.comiclist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import com.example.lucas.spidey2.R
import com.example.lucas.spidey2.internal.di.Injector
import com.example.lucas.spidey2.ui.features.comicdetail.ComicDetailActivity
import com.example.lucas.spidey2.ui.features.comiclist.adapter.ComicListAdapter
import com.example.lucas.spidey2.ui.features.comiclist.adapter.EndlessRecyclerViewScrollListener
import com.example.lucas.spidey2.ui.features.comiclist.adapter.items.ComicListItem
import com.example.lucas.spidey2.ui.features.comiclist.di.ComicListModule
import kotlinx.android.synthetic.main.activity_comic_list.*
import javax.inject.Inject

class ComicListActivity : AppCompatActivity(), ComicListView {
    @Inject lateinit var presenter: ComicListPresenter

    lateinit var comicListAdapter: ComicListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_list)

        Injector.getAppComponent()
                .getComicListComponent(ComicListModule(this))
                .inject(this)

        setupComicList()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadComics()
    }

    private fun setupComicList() {
        comicListAdapter = ComicListAdapter(presenter::comicClicked, presenter::loadComics)

        comic_list_grid.setHasFixedSize(true)
        comic_list_grid.adapter = comicListAdapter
        (comic_list_grid.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
        comic_list_grid.addOnScrollListener(object : EndlessRecyclerViewScrollListener(comic_list_grid.manager!!) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                presenter.loadComics()
            }
        })
    }

    override fun showComics(items: List<ComicListItem>) {
        comicListAdapter.update(items)
    }

    override fun launchDetailActivity(comicId: Int, comicTitle: String) {
        val intent = Intent(applicationContext, ComicDetailActivity::class.java)
        intent.putExtra(ComicDetailActivity.COMIC_ID_EXTRA, comicId)
        intent.putExtra(ComicDetailActivity.COMIC_TITLE_EXTRA, comicTitle)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }
}
