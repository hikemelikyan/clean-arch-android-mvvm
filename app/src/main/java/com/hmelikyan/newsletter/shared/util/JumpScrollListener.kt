package com.hmelikyan.newsletter.shared.util

import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class JumpScrollListener(
    private val adapter: JumpAdapter
) : RecyclerView.OnScrollListener() {

    var totalScroll = 0f

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        when (newState) {
            RecyclerView.SCROLL_STATE_IDLE -> {
                adapter.invalidate(totalScroll)
                totalScroll = 0f
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        totalScroll += abs(dx)
        adapter.update(abs(totalScroll / recyclerView.computeHorizontalScrollOffset().toFloat()))
    }

    interface JumpAdapter{

        fun invalidate(lastValue:Float)

        fun update(value:Float)
    }

}