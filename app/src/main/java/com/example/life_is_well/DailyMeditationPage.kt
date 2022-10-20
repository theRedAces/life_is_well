package com.example.life_is_well

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.life_is_well.databinding.ActivityDailyMeditationPageBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_meditation_page.*


class DailyMeditationPage:AppCompatActivity(){
    private lateinit var binding :ActivityDailyMeditationPageBinding

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityDailyMeditationPageBinding.inflate(layoutInflater)

        // set screen orientation to landscape
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(binding.root)
        actionBar?.hide() // hide action bar

        // YouTube Player View - initiate View by Id
        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.dailyMedView)
        lifecycle.addObserver(youTubePlayerView) // getting observer for youtube player view.

        //setting fullscreen for YoutubePlayer view
        //youTubePlayerView.enterFullScreen()
        youTubePlayerView.toggleFullScreen()


    //adding listener for YoutubePlayer view.
        youTubePlayerView.addYouTubePlayerListener(object:AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer:YouTubePlayer){
    //selected video
                val videoId="h7bJKdjZcOs"

    //load video into the YouTubePlayer
                youTubePlayer.loadVideo(videoId,0f)
            }
        })
    }

    fun backBtn(view: View) {
        val intent = Intent(this, MeditationPage::class.java)
        startActivity(intent)
    }

    fun homeBtn(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}