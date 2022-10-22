package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.home.YoutubeSelector
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
        videoAdapter.onItemClick = {
            val intent = Intent(this, YoutubeSelector::class.java)
            intent.putExtra("video", it)

            startActivity(intent)
        }
    }
}