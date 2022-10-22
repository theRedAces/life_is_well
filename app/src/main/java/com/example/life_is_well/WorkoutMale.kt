package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import youtube.YouTubeVideoAdapter
import youtube.YouTubeVideos
import com.example.life_is_well.home.YoutubeSelector

class WorkoutMale : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoList: ArrayList<YouTubeVideos>
    private lateinit var videoAdapter: YouTubeVideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_male)

        recyclerView = findViewById(R.id.youtubeRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        videoList = ArrayList()

        videoList.add(YouTubeVideos(R.drawable.workout1, "Workout #1"))
        videoList.add(YouTubeVideos(R.drawable.workout2, "Workout #2"))
        videoList.add(YouTubeVideos(R.drawable.workout3, "Workout #3"))
        videoList.add(YouTubeVideos(R.drawable.workout4, "Workout #4"))
        videoList.add(YouTubeVideos(R.drawable.workout5, "Workout #5"))

        videoAdapter = YouTubeVideoAdapter(videoList)
        recyclerView.adapter = videoAdapter

        videoAdapter.onItemClick = {
            val intent = Intent(this, YoutubeSelector::class.java)
            intent.putExtra("video" , it)

            startActivity(intent)
        }
    }
}