package com.egorovsoft.vkconnector.mvp.model.wall

import com.egorovsoft.vkconnector.mvp.model.IVKApi
import com.egorovsoft.vkconnector.mvp.model.room.RoomWall
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomAudio
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomLink
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomVideo
import com.egorovsoft.vkconnector.mvp.model.room.cache.basic.*
import com.egorovsoft.vkconnector.mvp.model.room.cache.wall.IWallCache
import io.reactivex.rxjava3.schedulers.Schedulers

class WallModel(
    val api: IVKApi,
    val roomWall: IWallCache,
    val roomPhoto: IPhotoCache,
    val roomSize: ISizesCache,
    val roomAudio: IAudioCache,
    val roomDoc: IDocCache,
    val roomLink: ILinkCache,
    val roomNote: INoteCache,
    val roomVideo: IVideoCache
) {
    fun getWall(token: String, userId: Int) =
        api.wallGet(token, userId)
            .map {
                it.response?.let {
                    it.items?.let {listWallItem->
                        roomWall.putUserWall(listWallItem).toSingleDefault(listWallItem)

                        val listWallItem = listWallItem.map {wallItem ->
                            var imageUrl: String? = null
                            var text: String? = null

                            wallItem.attachments?.let {
                                it.map {
                                    when (it.type) {
                                        "photo" -> {
                                            text = it.photo.text

                                            roomPhoto.putPhoto(it.photo, wallItem.id)
                                            it.photo.sizes?.let {sizes ->
                                                imageUrl = sizes.get(1).url
                                                roomSize.putSizes(sizes, it.photo.id)
                                            }
                                        }
                                        "video" -> {
                                            text = "${it.video.title} \n ${it.video.description}"
                                            roomVideo.putVideo(it.video, wallItem.id)
                                        }
                                        "audio" -> {
                                            text = "${it.audio.artist} \n ${it.audio.title}"
                                            roomAudio.putAudio(it.audio, wallItem.id)
                                        }
                                        "doc" -> {
                                            text = "${it.doc.title}"
                                            roomDoc.putDoc(it.doc, wallItem.id)
                                        }
                                        "link" -> {
                                            text = "${it.link.title} \n ${it.link.description}"
                                            roomLink.putLink(it.link, wallItem.id)
                                        }
                                        "note" -> {
                                            text = "${it.note.title} \n ${it.note.text}"
                                            roomNote.putNote(it.note, wallItem.id)
                                        }
                                    }

                                    it
                                }

                            }?: wallItem.copyHistory?.let {
                                if(it.size > 0){
                                    text = it[0].text
                                    ///TODO: копирование нужо вынести в отдельную функцию, либо переработать код
                                    it[0].attachments?.let {
                                        it.map {
                                            when (it.type) {
                                                "photo" -> {
                                                    roomPhoto.putPhoto(it.photo, wallItem.id)
                                                    it.photo.sizes?.let {sizes ->
                                                        imageUrl = sizes.get(1).url
                                                        roomSize.putSizes(sizes, it.photo.id)
                                                    }
                                                }
                                                "video" -> {
                                                    text = "${it.video.title} \n ${it.video.description}"
                                                    roomVideo.putVideo(it.video, wallItem.id)
                                                }
                                                "audio" -> {
                                                    text = "${it.audio.artist} \n ${it.audio.title}"
                                                    roomAudio.putAudio(it.audio, wallItem.id)
                                                }
                                                "doc" -> {
                                                    text = "${it.doc.title}"
                                                    roomDoc.putDoc(it.doc, wallItem.id)
                                                }
                                                "link" -> {
                                                    text = "${it.link.title} \n ${it.link.description}"
                                                    roomLink.putLink(it.link, wallItem.id)
                                                }
                                                "note" -> {
                                                    text = "${it.note.title} \n ${it.note.text}"
                                                    roomNote.putNote(it.note, wallItem.id)
                                                }
                                            }

                                            it
                                        }
                                    }
                                }
                            }

                            WallFirstItem(wallItem.id, wallItem.fromId, wallItem.text, imageUrl, text)
                        }

                        listWallItem
                    }
                }
            }.subscribeOn(Schedulers.io())
}