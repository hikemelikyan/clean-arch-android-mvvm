package com.hmelikyan.newsletter.shared.util

import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class JumpScrollListener(
    private val adapter : JumpAdapter
) : RecyclerView.OnScrollListener() {

	var scrollStart = 0f

	override fun onScrollStateChanged(recyclerView : RecyclerView, newState : Int) {
		super.onScrollStateChanged(recyclerView, newState)
		when (newState) {
            RecyclerView.SCROLL_STATE_IDLE -> {
                adapter.invalidate()
            }
            RecyclerView.SCROLL_STATE_DRAGGING -> {
                scrollStart = recyclerView.computeHorizontalScrollOffset().toFloat()
            }
		}
	}

	override fun onScrolled(recyclerView : RecyclerView, dx : Int, dy : Int) {
		super.onScrolled(recyclerView, dx, dy)
		adapter.update(abs(recyclerView.computeHorizontalScrollOffset() - scrollStart) / (recyclerView.computeHorizontalScrollRange() / 2).toFloat())
	}

	interface JumpAdapter {

		fun invalidate()

		fun update(value : Float)
	}

}