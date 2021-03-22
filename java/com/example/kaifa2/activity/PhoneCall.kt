package com.example.kaifa2.activity

import com.example.kaifa2.R
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_phone_call.*

class PhoneCall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_call)
        makeCall.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            }
            else{
                call()
            }

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    call()
                else{
                    Toast.makeText(this,"你没授权",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun call() {
        val number="tel:"+input.text.toString()
        try {
            val intent=Intent(Intent.ACTION_CALL)
            intent.data= Uri.parse(number)
            startActivity(intent)
        }catch (e:SecurityException){
            e.printStackTrace()
        }
    }
}