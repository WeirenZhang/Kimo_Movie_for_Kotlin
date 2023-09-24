package com.weiren.zhang.movie_kotlin.fragment.myfavourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.weiren.zhang.lib_common.base.fragment.BaseBindFragment
import com.weiren.zhang.lib_common.router.RouterPath
import com.weiren.zhang.movie_kotlin.adapter.myfavourite.MovieMyFavouriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.weiren.zhang.movie_kotlin.databinding.RecyclerviewBinding
import com.weiren.zhang.movie_kotlin.module.MovieManager

@AndroidEntryPoint
@Route(path = RouterPath.MyFavourite.PATH_MovieMyFavourite_HOME)
class MovieMyFavouriteFragment : BaseBindFragment<RecyclerviewBinding>(),
    MovieMyFavouriteAdapter.EventListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Bundle?) -> RecyclerviewBinding
        get() = { layoutInflater, viewGroup, _ ->
            RecyclerviewBinding.inflate(layoutInflater, viewGroup, false)
        }

    private val mAdapter: MovieMyFavouriteAdapter by lazy {
        MovieMyFavouriteAdapter(
            mActivity,
            this
        )
    }

    fun mEmptyLL_isVisible() {
        binding.mEmptyLL.isVisible = true
    }

    override fun initView() {
        binding.mRecyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
        binding.mSwipeRefreshLayout.isRefreshing = false;
        binding.mSwipeRefreshLayout.isEnabled = false;
        binding.mRecyclerView.adapter = mAdapter
    }

    override fun initData() {
        val newDataList = MovieManager.getMovieList()
        if (newDataList.size > 0) {
            mAdapter.setList(newDataList)
        } else {
            mEmptyLL_isVisible()
        }
    }

    override fun onEvent() {
        mEmptyLL_isVisible()
    }
}