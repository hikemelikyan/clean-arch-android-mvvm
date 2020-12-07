package com.hmelikyan.newsletter.ui.screens.testActivity.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.app.FrameMetricsAggregator
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.hmelikyan.newsletter.BR
import com.hmelikyan.newsletter.databinding.AdapterCategoryItemHorizontalBinding
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import com.hmelikyan.newsletter.mvvm.ui.BaseListAdapter
import com.hmelikyan.newsletter.mvvm.ui.BaseViewHolder
import com.hmelikyan.newsletter.root.ext.dpToPx
import com.hmelikyan.newsletter.shared.util.JumpScrollListener

class CategoriesAdapterHorizontal : BaseListAdapter<NotificationDomain, AdapterCategoryItemHorizontalBinding>(NotificationDomain.Companion), JumpScrollListener.JumpAdapter {

	private val translateProvider : TranslateProvider by lazy { TranslateProvider() }

	private val listOfImages = listOf(
		"https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
		"https://static.toiimg.com/photo/72975551.cms",
		"https://www.w3schools.com/w3css/img_lights.jpg",
		"https://eskipaper.com/images/images-2.jpg",
		"https://latestauto20.com/wp-content/uploads/2020/07/photo-1494548162494-384bba4ab999.jpg"
	)

	inner class TranslateProvider : BaseObservable() {

		var translationY = 0f
			@Bindable get
			set(value) {
				field = value
				notifyPropertyChanged(BR.translationY)
			}
	}

	override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterCategoryItemHorizontalBinding {
		return AdapterCategoryItemHorizontalBinding.inflate(inflater, parent, attachToParent)
	}

	override fun BaseViewHolder<AdapterCategoryItemHorizontalBinding, NotificationDomain>.bindActionTo(data : NotificationDomain) {
		binding.apply {

			item = translateProvider
			tvCategory.text = data.description
			tvTime.text = data.id.toString()
			Glide.with(holderContext)
				.load(listOfImages[absoluteAdapterPosition % listOfImages.size])
				.addListener(object : RequestListener<Drawable> {
					override fun onLoadFailed(e : GlideException?, model : Any?, target : Target<Drawable>?, isFirstResource : Boolean) : Boolean {
						return false
					}

					override fun onResourceReady(resource : Drawable?, model : Any?, target : Target<Drawable>?, dataSource : DataSource?, isFirstResource : Boolean) : Boolean {

						resource?.toBitmap()?.let {
							val palette = Palette.from(it)
							palette.addTarget(androidx.palette.graphics.Target.DARK_VIBRANT)
							palette.generate {
								it?.vibrantSwatch?.rgb?.let {
									image.setShadowColor(it)
									image.setShadowRadius(10)
								}
							}
						}
						return false
					}

				})
				.into(image)
		}
	}

	override fun down() {
		ValueAnimator.ofFloat(15.dpToPx().toFloat(), 0f)
			.apply {
				interpolator = BounceInterpolator()
				addUpdateListener {
					translateProvider.translationY = it.animatedValue as Float
				}
				duration = 400L
			}.start()
	}

	override fun up() {
		ValueAnimator.ofFloat(0f, 15.dpToPx().toFloat())
			.apply {
				interpolator = LinearInterpolator()
				addUpdateListener {
					translateProvider.translationY = it.animatedValue as Float
				}
				duration = FrameMetricsAggregator.ANIMATION_DURATION.toLong()
			}.start()
	}
}