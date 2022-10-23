package com.example.life_is_well.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.life_is_well.R
import youtube.YouTubeVideos

class YoutubeSelector : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_selector)

        val video = intent.getParcelableExtra<YouTubeVideos>("video")
        if(video != null){
            val textView : TextView = findViewById(R.id.tvYoutubeSelector)
            val imageView : ImageView = findViewById(R.id.ivYoutubeSelector)

            textView.text = video.name
            imageView.setImageResource(video.image)
        }
    }
}