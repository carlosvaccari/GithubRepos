package com.cvaccari.customviews.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cvaccari.customviews.R

class CustomRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var isLoading = false

    private var loadMoreListener: LoadMoreListener? = null

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        setDecorator()
    }

    private fun setDecorator() {
        this.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
    }

    fun startAnim() {
        val controller =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_wave)
        layoutAnimation = controller
        scheduleLayoutAnimation()
    }

    override fun onScrolled(dx: Int, dy: Int) {
        val layoutManager = layoutManager as LinearLayoutManager?
        val visibleItemCount = layoutManager!!.childCount
        val totalItemCount = layoutManager.itemCount
        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
        if (!isLoading) {
            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                loadMoreListener?.loadMore()
            }
        }
    }

    fun setOnLoadMoreListener(listener: LoadMoreListener) {
        loadMoreListener = listener
    }

    interface LoadMoreListener {
        fun loadMore()
    }
}