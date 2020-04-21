package com.egorovsoft.vkconnector.ui.fragment.wall

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.model.image.IImageLoader
import com.egorovsoft.vkconnector.mvp.view.fragment.wall.IRvWallPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.wall.IViewHolderWall
import com.egorovsoft.vkconnector.ui.MainApp
import com.egorovsoft.vkconnector.ui.image.GlideImageLoader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rv_wall.*
import kotlinx.android.synthetic.main.rv_wall.photo_wall
import kotlinx.android.synthetic.main.rv_wall.txt_link_wall
import kotlinx.android.synthetic.main.rv_wall.txt_wall
import kotlinx.android.synthetic.main.rv_wall.view.*
import javax.inject.Inject

class WallRvAdapter(val presenter : IRvWallPresenter): RecyclerView.Adapter<WallRvAdapter.ViewHolder>() {
    val wallSubcomponent = MainApp.instance.wallComponent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_wall, parent, false)).apply {
            wallSubcomponent.inject(this)
        }

    override fun getItemCount(): Int = presenter.getItemCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos  =   position;
        holder.containerView.setOnClickListener{
            presenter.onClickListener?.invoke(holder)
        }
        presenter.bindViewHolder(holder)
    }

    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
        LayoutContainer, IViewHolderWall {
        @Inject lateinit var imageLoader: IImageLoader<ImageView>
        override var pos = -1

        override fun setUserText(txt: String?) = with(containerView){
            txt?.let {
                txt_wall.visibility = View.VISIBLE
                txt_wall.text = it
            }?: let {
                txt_wall.visibility = View.GONE
            }
        }

        override fun setIcon(path: String?) = with(containerView){
            path?.let {
                img_wall.visibility = View.VISIBLE
                imageLoader.loadInto(it, img_wall)
            }?: let{
                img_wall.visibility = View.GONE
            }
        }

        override fun setTitle(txt: String?) = with(containerView){
            txt?.let {
                title_wall.text = it
            }?: let {
                title_wall.text = ""
            }
        }

        override fun setPhoto(path: String?) = with(containerView){
            path?.let {
                photo_wall.visibility = View.VISIBLE
                imageLoader.loadInto(it, photo_wall)
            }?: let{
                photo_wall.visibility = View.GONE
            }
        }

        override fun setText(txt: String?) = with(containerView){
            txt?.let {
                txt_link_wall.visibility = View.VISIBLE
                txt_link_wall.text = it
            }?: let {
                txt_link_wall.visibility = View.GONE
            }
        }
    }
}