package com.example.life_is_well

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.life_is_well.databinding.ActivityOpenHeartMeditationPageBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class OpenHeartMeditationPage : AppCompatActivity() {
    private lateinit var binding: ActivityOpenHeartMeditationPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenHeartMeditationPageBinding.inflate(layoutInflater)

        // set screen orientation to landscape
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(binding.root)
        actionBar?.hide() // hide action bar

        // YouTube Player View - initiate View by Id
        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.openHeartView)
        lifecycle.addObserver(youTubePlayerView) // getting observer for youtube player view.

        // setting full screen for YoutubePlayer view
        //  youTubePlayerView.enterFullScreen()
        youTubePlayerView.toggleFullScreen()


        // adding listener for YoutubePlayer view.
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // selected video
                val videoId = "hn6biky7D1g"

                // load video into the YouTube Player
                youTubePlayer.loadVideo(videoId, 0f)
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                // this method is called if video has ended,
                super.onStateChange(youTubePlayer, state)
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