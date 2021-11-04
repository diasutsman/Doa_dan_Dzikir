package com.example.doadandzikir

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {


    private val listArtikel: ArrayList<Artikel> = arrayListOf()

    //  cara buat slider
    private lateinit var dotSlider: Array<ImageView?>
    private val slidingCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
//          will change selected dot with R.drawable.dot_active
            for (i in 0 until listArtikel.size) {
                dotSlider[i]?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.dot_inactive)
                )
            }
//          will change selected dot with R.drawable.dot_active
            dotSlider[position]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext, R.drawable.dot_active)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        initData()
        setupViewPager()
    }

    private fun setupViewPager() {
        val llSliderDot: LinearLayout = findViewById(R.id.ll_slider_dot)

        dotSlider = arrayOfNulls(listArtikel.size)

        for (i in 0 until listArtikel.size) {
            dotSlider[i] = ImageView(this)
            dotSlider[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext, R.drawable.dot_inactive
                )
            )
            // menentukan lebar dan tinggi indikator
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            // mengatur jarak antar indikator
            params.setMargins(9, 0, 8, 0)
            // mengatur posisi indikator
            params.gravity = Gravity.CENTER_VERTICAL
            // menerapkan params sebagai aturan bagaimana indikator ditampilkan
            llSliderDot.addView(dotSlider[i], params)

        }

        dotSlider[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext, R.drawable.dot_active
            )
        )

    }

    private fun initData() {
        val titleArtikel = resources.getStringArray(R.array.arr_title_artikel)
        val descArtikel = resources.getStringArray(R.array.arr_desc_artikel)
        val imageArtikel = resources.obtainTypedArray(R.array.arr_img_artikel)

        for (i in titleArtikel.indices) {
            listArtikel.add(
                Artikel(
                    titleArtikel[i],
                    descArtikel[i],
                    imageArtikel.getResourceId(i, 0)
                )
            )
        }
        imageArtikel.recycle()
    }

    private fun initView() {
        val vpArtikel: ViewPager2 = findViewById(R.id.vp_artikel)
        vpArtikel.adapter = ArtikelAdapter(listArtikel)

        // registerOnPageChangeCallback untuk menampilkan indikator pada view pager
        vpArtikel.registerOnPageChangeCallback(slidingCallback)

        //        set on click listener on llDzikirDoaSholat view and move to the QauliyahShalatActivity
        val llDzikirDoaSholat: LinearLayout = findViewById(R.id.ll_dzikir_doa_sholat)
        llDzikirDoaSholat.setOnClickListener {
            startActivity(Intent(this, QauliyahShalatActivity::class.java))
        }

//        set on click listener on llDzikirSetiapSaat view and move to the SetiapSaatDzikirActivity
        val llDzikirSetiapSaat: LinearLayout = findViewById(R.id.ll_dzikir_setiap_saat)
        llDzikirSetiapSaat.setOnClickListener {
            startActivity(Intent(this, SetiapSaatDzikirActivity::class.java))
        }

//        set on click listener on llDzikirDoaHarian view and move to the HarianDzikirDoaActivity
        val llDzikirDoaHarian: LinearLayout = findViewById(R.id.ll_dzikir_doa_harian)
        llDzikirDoaHarian.setOnClickListener {
            startActivity(Intent(this, HarianDzikirDoaActivity::class.java))
        }

//        set on click listener on llDzikirPagiPetang view and move to the PagiPetangDzikirActivity
        val llDzikirPagiPetang: LinearLayout = findViewById(R.id.ll_dzikir_pagi_petang)
        llDzikirPagiPetang.setOnClickListener {
            startActivity(Intent(this, PagiPetangDzikirActivity::class.java))
        }
    }
}