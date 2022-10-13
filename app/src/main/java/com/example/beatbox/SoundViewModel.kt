package com.example.beatbox

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel(private val beatBox: BeatBox) : BaseObservable(){
    fun onButtonClicked(){
        sound?.let{
            beatBox.play(it)
        }
    }

    var sound: Sound? = null

    set(sound){
        field = sound
        notifyChange()
    }

    var imgSrc: Drawable? = null
    set(imgSrc){
        field = imgSrc
        notifyChange()
    }


    @get: Bindable
    val btnImg: Drawable?
        get() {
            return imgSrc
        }

}

