package com.egorovsoft.vkconnector.ui.fragment.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.model.image.IImageLoader
import com.egorovsoft.vkconnector.mvp.view.fragment.friends.IRvFriendsPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.friends.IViewHolderFriends
import com.egorovsoft.vkconnector.ui.MainApp
import com.egorovsoft.vkconnector.ui.image.GlideImageLoader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rv_friends.*
import javax.inject.Inject

class FriendsRvAdapter(val presenter : IRvFriendsPresenter): RecyclerView.Adapter<FriendsRvAdapter.ViewHolder>() {
    val friendsSubcomponent = MainApp.instance.friendsComponent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_friends, parent, false)).apply {
            friendsSubcomponent.inject(this)
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
        LayoutContainer, IViewHolderFriends {
        override var pos = -1
        @Inject lateinit var imageLoader: IImageLoader<ImageView>

        override fun setText(txt: String) = with(containerView){
            tv_friends_username.text = txt
        }

        override fun setImage(path: String) = with(containerView){
            imageLoader.loadInto(path, iv_friends_photo)
        }
    }
}