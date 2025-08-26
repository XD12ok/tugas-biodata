package com.example.tugas_budian

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {
    private lateinit var etNama: EditText
    private lateinit var etAlamat: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var rgGender: RadioGroup
    private lateinit var spAgama: Spinner
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inisialisasi view
        etNama = findViewById(R.id.txtNama)
        etAlamat = findViewById(R.id.txtAlamat)
        datePicker = findViewById(R.id.txtDate)
        rgGender = findViewById(R.id.rgGender)
        spAgama = findViewById(R.id.spnAgama)
        btnSubmit = findViewById(R.id.btnSubmit)

        // Isi data agama ke spinner
        val agamaList = arrayOf("Islam", "Kristen", "Katolik", "Hindu", "Buddha", "Kong Hu Cu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, agamaList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spAgama.adapter = adapter

        // Aksi tombol submit
        btnSubmit.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val alamat = etAlamat.text.toString().trim()

            // 🔎 Cek validasi kosong
            if (nama.isEmpty() || alamat.isEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("Data tidak lengkap")
                    .setMessage("Nama dan Alamat wajib diisi!")
                    .setPositiveButton("OK", null)
                    .show()
                return@setOnClickListener
            }

            val day = datePicker.dayOfMonth
            val month = datePicker.month + 1 // bulan mulai dari 0
            val year = datePicker.year
            val tanggalLahir = "$day/$month/$year"

            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                val rb = findViewById<RadioButton>(selectedGenderId)
                rb.text.toString()
            } else {
                "LGBTQ, iyuh huekk"
            }

            val agama = spAgama.selectedItem.toString()

            // Kirim ke PreviewActivity
            val intent = Intent(this, PreviewActivity::class.java)
            intent.putExtra("nama", nama)
            intent.putExtra("alamat", alamat)
            intent.putExtra("tglLahir", tanggalLahir)
            intent.putExtra("gender", gender)
            intent.putExtra("agama", agama)
            startActivity(intent)
        }
    }
}
