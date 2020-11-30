package com.hmelikyan.newsletter.ui.screens.testActivity.adapters

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.core.app.FrameMetricsAggregator
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.bumptech.glide.Glide
import com.hmelikyan.newsletter.BR
import com.hmelikyan.newsletter.databinding.AdapterCategoryItemHorizontalBinding
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import com.hmelikyan.newsletter.mvvm.ui.BaseListAdapter
import com.hmelikyan.newsletter.mvvm.ui.BaseViewHolder
import com.hmelikyan.newsletter.root.shared.ext.dpToPx
import com.hmelikyan.newsletter.shared.util.JumpScrollListener

class CategoriesAdapterHorizontal : BaseListAdapter<NotificationDomain, AdapterCategoryItemHorizontalBinding>(NotificationDomain.Companion) , JumpScrollListener.JumpAdapter {

    private val translateProvider: TranslateProvider by lazy { TranslateProvider() }

    inner class TranslateProvider : BaseObservable() {

        var translationY = 0f
            @Bindable get
            set(value) {
                field = value
                notifyPropertyChanged(BR.translationY)
            }
    }

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): AdapterCategoryItemHorizontalBinding {
        return AdapterCategoryItemHorizontalBinding.inflate(inflater, parent, attachToParent)
    }

    override fun BaseViewHolder<AdapterCategoryItemHorizontalBinding, NotificationDomain>.bindActionTo(data: NotificationDomain) {
        binding.apply {
            item = translateProvider
            tvCategory.text = data.description
            tvTime.text = data.id.toString()
            Glide.with(holderContext)
                .load("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg")
                .into(image)
        }
    }

    override fun invalidate() {
        ValueAnimator.ofFloat(translateProvider.translationY, 0f)
            .apply {
                interpolator = DecelerateInterpolator()
                addUpdateListener {
                    translateProvider.translationY = it.animatedValue as Float
                }
                duration = FrameMetricsAggregator.ANIMATION_DURATION.toLong()
            }.start()
    }

    override fun update(value: Float) {
        translateProvider.translationY = value * 15.dpToPx()
    }
}