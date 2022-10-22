package com.example.life_is_well

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import youtube.YouTubeVideoAdapter
import youtube.YouTubeVideos

class WorkoutNonBinary : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoList: ArrayList<YouTubeVideos>
    private lateinit var videoAdapter: YouTubeVideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_out_non_binary)

        recyclerView = findViewById(R.id.youtubeRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        videoList = ArrayList()

        videoList.add(YouTubeVideos(R.drawable.workout11, "Workout #1"))
        videoList.add(YouTubeVideos(R.drawable.workout12, "Workout #2"))
        videoList.add(YouTubeVideos(R.drawable.workout13, "Workout #3"))
        videoList.add(YouTubeVideos(R.drawable.workout14, "Workout #4"))
        videoList.add(YouTubeVideos(R.drawable.workout15, "Workout #5"))

        videoAdapter = YouTubeVideoAdapter(videoList)
        recyclerView.adapter = videoAdapter
    }
}