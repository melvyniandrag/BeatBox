package com.example.beatbox

import androidx.lifecycle.ViewModel
import android.content.res.AssetManager

/**
 * This is not an MVVM ViewModel.
 * This is a Jetpack view model for storing data across screen rotation.
 * It is used by the MainActivity to store the BeatBox
 */

private const val TAG = "BeatBoxJetPackViewModel"


class BeatBoxJetPackViewModel constructor(val assets: AssetManager): ViewModel() {


    public val beatBox = BeatBox(assets)

    override fun onCleared() {
        super.onCleared()
        beatBox.release()
    }

    public fun getSounds() : List<Sound>{
        return beatBox.sounds
    }

}