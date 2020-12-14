package com.example.mysound

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sp: SoundPool
    private var soundId: Int = 0
    private var spLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = SoundPool.Builder()
                //paramater 10 yang berada di metode setMaxStreams()
                // adalah untuk menentukan jumlah streams secara simultan yang dapat diputar secara bersamaan
                .setMaxStreams(10)
                .build()

        sp.setOnLoadCompleteListener { soundPool, sampleid, status ->
            if (status == 0) {
                spLoaded = true
            } else {
                Toast.makeText(this, "gagal load", Toast.LENGTH_SHORT).show()
            }
        }

        soundId = sp.load(this, R.raw.sub1, 1)

        btn_soundpool.setOnClickListener{
            if (spLoaded) {
                sp.play(soundId, 1f, 1f, 0,0, 1f)
            }
        }
    }

}