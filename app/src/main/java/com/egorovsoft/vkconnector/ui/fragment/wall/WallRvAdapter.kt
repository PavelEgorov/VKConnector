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
import com.egorovsoft.vkconnector.mvp.view.fragment.wall.IRvWallPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.wall.IViewHolderWall
import com.egorovsoft.vkconnector.ui.image.GlideImageLoader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rv_wall.*

class WallRvAdapter(val presenter : IRvWallPresenter): RecyclerView.Adapter<WallRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_wall, parent, false))

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
        private val imageLoader = GlideImageLoader()

        override var pos = -1

        override fun setText(txt: String) = with(containerView){
            txt_wall.visibility = View.VISIBLE
            txt_wall.text = txt
        }

        override fun setIcon(path: String) {
            imageLoader.loadInto(path, img_wall)
        }

        override fun setTitle(txt: String) {
            title_wall.text = txt
        }

        override fun addImage(path: String) = with(containerView){
            photo_wall.visibility = View.VISIBLE
            imageLoader.loadInto(path, photo_wall)
        }

        override fun addText(txt: String) = with(containerView){
            txt_link_wall.visibility = View.VISIBLE
            txt_link_wall.text = txt
        }
    }
}