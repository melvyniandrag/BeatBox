package com.example.beatbox

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatbox.databinding.ActivityMainBinding
import com.example.beatbox.databinding.ListItemSoundBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  beatBoxViewModel : BeatBoxJetPackViewModel

    private val drawableIDs : List<Int> = listOf(
        R.drawable.beluga,
        R.drawable.hecker,
        R.drawable.belumom,
        R.drawable.lester,
        R.drawable.mark,
        R.drawable.pablo,
        R.drawable.skittlechan,
        R.drawable.belugajr,
        R.drawable.bigpapa,
        R.drawable.elon,
        R.drawable.eugene,
        R.drawable.skittle,
        R.drawable.snowball,
        R.drawable.vladimir,
        R.drawable.walt
    )


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factoryModel =  BeatBoxFactoryModel(assets)
        beatBoxViewModel = ViewModelProvider(this, factoryModel).get(BeatBoxJetPackViewModel::class.java)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.apply{
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBoxViewModel.getSounds(), drawableIDs)
        }
    }

    override fun onDestroy(){
        super.onDestroy()
        //beatBox.release()
    }

    private inner class SoundHolder(private val binding: ListItemSoundBinding): RecyclerView.ViewHolder(binding.root){
        init{
            binding.viewModel = SoundViewModel(beatBoxViewModel.beatBox)
        }

        fun bind(sound: Sound,imgSrc: Drawable){
            binding.apply{
                viewModel?.sound = sound
                viewModel?.imgSrc = imgSrc
                executePendingBindings()
            }
        }
    }

    private inner class SoundAdapter(private val sounds: List<Sound>, private val drawableIDs: List<Int>) : RecyclerView.Adapter<SoundHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            )
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder:SoundHolder, position: Int){
            val sound = sounds[position]
            val resources : Resources = applicationContext.resources
            val imgSrc : Drawable = resources.getDrawable(drawableIDs[position % 15])
            holder.bind(sound, imgSrc)
        }

        override fun getItemCount() = sounds.size
    }

}