package com.example.tugas_budian

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class PreviewActivity : AppCompatActivity() {
    private lateinit var tvHasil: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preview)

        tvHasil = findViewById(R.id.tvHasil)

        // Ambil data dari Intent
        val nama = intent.getStringExtra("nama")
        val alamat = intent.getStringExtra("alamat")
        val tglLahir = intent.getStringExtra("tglLahir")
        val gender = intent.getStringExtra("gender")
        val agama = intent.getStringExtra("agama")

        // Tampilkan hasil
        val hasil = """
            ===== CV Preview =====

            Nama Lengkap : $nama
            Alamat       : $alamat
            Tanggal Lahir: $tglLahir
            Jenis Kelamin: $gender
            Agama        : $agama
        """.trimIndent()

        tvHasil.text = hasil
    }
}

