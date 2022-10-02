package com.example.life_is_well

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.life_is_well.databinding.ActivityProfilePageMainBinding

class ProfilePageMain : AppCompatActivity() {
    private lateinit var binding: ActivityProfilePageMainBinding
    private lateinit var profilePhoto: ImageView
    private var pickedProfilePhoto: Uri? = null
    private var pickedProfileBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilePageMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profilePhoto = findViewById(R.id.profile_photo)
        val pickProfilePhoto = View.OnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            } else {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, 2)
            }
        }
        profilePhoto.setOnClickListener(pickProfilePhoto)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 2)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        profilePhoto = findViewById(R.id.profile_photo)
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            pickedProfilePhoto = data.data!!
            if (Build.VERSION.SDK_INT >= 28) {
                val source = ImageDecoder.createSource(this.contentResolver, pickedProfilePhoto!!)
                pickedProfileBitmap = ImageDecoder.decodeBitmap(source)
                profilePhoto.setImageBitmap(pickedProfileBitmap)
            } else {
                pickedProfileBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, pickedProfilePhoto)
                profilePhoto.setImageBitmap(pickedProfileBitmap)
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}