package com.vmodev.c18_implicit_intent

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_phone.setOnClickListener {
            if (tiet_number.text.toString().equals(""))
            {
                Toast.makeText(this, "Insert Number", Toast.LENGTH_SHORT).show()
            }
            else
            {
                makePhoneCall(tiet_number.text.toString())
            }
        }
        btn_maps.setOnClickListener {
            if (tiet_location.text.toString().equals(""))
            {
                Toast.makeText(this, "Insert Location", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var location = tiet_location.text.toString()
                location = location.replace(" ".toRegex(),"+")
                openLocation(location)
            }
        }
        btn_website.setOnClickListener {
            if (tiet_website.text.toString().equals(""))
            {
                Toast.makeText(this, "Insert URL", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var url = tiet_website.text.toString()
                openWebsite(url)
            }
        }
    }

    private fun makePhoneCall(phone:String){

        val callIntent : Intent = Uri.parse("tel:0$phone").let { number ->
            Intent(Intent.ACTION_DIAL, number)
        }
        startActivity(callIntent)
    }

    private fun openLocation(location:String){
        val mapIntent: Intent = Uri.parse(
            "geo:0,0?q=$location"
        ).let { location ->
            Intent(Intent.ACTION_VIEW, location)
        }
        startActivity(mapIntent)
    }
    private fun openWebsite(url:String){
        val webIntent: Intent = Uri.parse("https://www.$url").let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        try {
            startActivity(webIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No app founded", Toast.LENGTH_SHORT).show()
        }
    }
}