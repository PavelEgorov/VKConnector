package com.egorovsoft.vkconnector.mvp.presenter.fragment

import com.egorovsoft.vkconnector.mvp.model.Const
import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachmentsWall
import com.egorovsoft.vkconnector.mvp.model.user.UserModel
import com.egorovsoft.vkconnector.mvp.model.wall.Wall
import com.egorovsoft.vkconnector.mvp.model.wall.WallItem
import com.egorovsoft.vkconnector.mvp.model.wall.WallModel
import com.egorovsoft.vkconnector.mvp.view.fragment.wall.IRvWallPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.wall.IViewHolderWall
import com.egorovsoft.vkconnector.mvp.view.fragment.wall.WallView
import com.egorovsoft.vkconnector.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber

@InjectViewState
class WallPresenter(
    val router: Router,
    val mainThread: Scheduler,
    val wallModel: WallModel,
    val userModel: UserModel
) : MvpPresenter<WallView>() {

    private var token: String? = null
    private var userId: Int? = null
    var wall: Wall? = null

    class RvPresenter(val mainThread: Scheduler) : IRvWallPresenter {
        var wallModel: WallModel? = null
        var userModel: UserModel? = null
        var token: String? = null

        val wall = mutableListOf<WallItem>()

        override var onClickListener: ((IViewHolderWall) -> Unit)? = null

        override fun bindViewHolder(holder: IViewHolderWall) {
            val item = wall[holder.pos]

            //TODO: Вопрос: пока отрабатывает фон холдер уже меняется, нужно загрузить в нужную позицию
            showWallItem(holder, item)

            token?.let {
                userModel?.let {usr ->
                    usr.getUser(it, item.fromId)
                        .observeOn(mainThread)
                        .subscribe(
                            {
                                if (it.response.size > 0) {
                                    holder.setIcon(it.response[0].photo_50)
                                    holder.setTitle("${it.response[0].firstName} ${it.response[0].lastName}")
                                }
                            },
                            {Timber.e(it)}
                        )
                }
            }
        }

        override fun getItemCount(): Int = wall.size

        fun showWallItem(h: IViewHolderWall, item: WallItem){
            if (!item.text.equals("")) h.setText(item.text)

            val attachments = item.attachments
            attachments?.let {
                showFirstAttachment(it, h)
            } ?: let{
                item.copyHistory?.let {history ->
                    if (history.size > 0) {
                        if (!history[0].text.equals("")) h.addText(history[0].text)
                        history[0].attachments?.let {
                            showFirstAttachment(it, h)
                        }
                    }
                }
            }
        }

        fun showFirstAttachment(it: MutableList<ItemAttachmentsWall>, h: IViewHolderWall) {
            if (it.size > 0) {
                val firstItem = it[0]
                when (firstItem.type) {
                    in Const.notSupportedList -> h.setText("Not supported")
                    "photo" -> {
                        if (!firstItem.photo.text.equals("")) h.addText(firstItem.photo.text)

                        val images = firstItem.photo.sizes
                        if (images.size > 0) {
                            h.addImage(images[3].url)
                        }
                    }
                    "postedPhoto" -> h.addImage(firstItem.postedPhoto.photo_130)
                    "video" -> h.addText("${firstItem.video.description}  ${firstItem.video.player}")
                    "audio" -> h.addText("${firstItem.audio.artist} \n ${firstItem.audio.title}")
                    "Doc" -> h.addText("${firstItem.doc.title}")
                    "graffiti" -> h.addImage(firstItem.graffiti.photo_130)
                    "link" -> h.addText("${firstItem.link.title} \n ${firstItem.link.description} \n ${firstItem.link.url}")
                    "note" -> h.addText("${firstItem.note.title} \n ${firstItem.note.text}")
                    "app" -> h.addText(firstItem.app.photo_130)
                    "poll" -> h.addText(firstItem.poll.question)
                }
            }
        }
    }

    val rvPresenter = RvPresenter(mainThread)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        updateNews()

        ///TODO: Обработку клика допишу позже
        rvPresenter.onClickListener = { itemView ->
//            router.navigateTo(Screens.WallItemScreen(rvPresenter.wall[itemView.pos].items))
            Timber.d("Item Click ${itemView.pos}")
        }
    }

    fun updateNews() {
        token?.let { t ->
            userId?.let { u ->
                wallModel.getWall(t, u)
                    .observeOn(mainThread)
                    .subscribe(
                        {
                            ///TODO: Нужна проверка на пустой массив и на результат ошибки
                            wall = it.response

                            wall?.let {
                                rvPresenter.wall.clear()
                                rvPresenter.wall.addAll(it.items)

                                viewState.updateList()
                            }
                        },
                        {
                            Timber.e(it)
                        }
                    )
            }
        }
    }

    fun initData(t: String, u: Int){
        token = t
        userId = u

        rvPresenter.token = token
        rvPresenter.userModel = userModel
        rvPresenter.wallModel = wallModel
    }
}