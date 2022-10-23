package youtube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.example.life_is_well.R.layout.each_item
import youtube.YouTubeVideos

class YouTubeVideoAdapter(private val videoList:ArrayList<YouTubeVideos>)
    : RecyclerView.Adapter<YouTubeVideoAdapter.VideoViewHolder>(){

    var onItemClick : ((YouTubeVideos) -> Unit)? = null

    class VideoViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.Thumbnail)
        val textView : TextView = itemView.findViewById(R.id.workoutTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(each_item, parent, false)
        return VideoViewHolder(view)

    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videoList[position]
        holder.imageView.setImageResource(video.image)
        holder.textView.text = video.name

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(video)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}