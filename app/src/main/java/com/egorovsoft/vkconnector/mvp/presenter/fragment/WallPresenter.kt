package com.egorovsoft.vkconnector.mvp.presenter.fragment

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
    val wallModel: WallModel
) : MvpPresenter<WallView>() {

    var token: String? = null
    var userId: Int? = null
    var wall: Wall? = null

    class RvPresenter() : IRvWallPresenter {
        val wall = mutableListOf<WallItem>()

        override var onClickListener: ((IViewHolderWall) -> Unit)? = null

        override fun bindViewHolder(holder: IViewHolderWall) {
            holder.setText("id: ${wall[holder.pos].id} from id: ${wall[holder.pos].fromId} text: ${wall[holder.pos].text} ");
        }

        override fun getItemCount(): Int = wall.size
    }

    val rvPresenter = RvPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        updateNews()

        /// Обработку клика допишу позже
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
                            /// Нужна проверка на пустой массив и на результат ошибки
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
}