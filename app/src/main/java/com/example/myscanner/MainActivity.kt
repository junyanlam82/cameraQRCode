package com.example.myscanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val btnScan: Button = findViewById(R.id.btnScan)

        btnScan.setOnClickListener {
            val qrCode = IntentIntegrator(this)
            qrCode.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            qrCode.setBeepEnabled(false)
            qrCode.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)

        val tvResult:TextView = findViewById(R.id.tvResult)
        if(result!=null){
            if(result.contents == null){
                tvResult.setText("Empty contents.")
            }else{
                tvResult.setText(result.contents)
            }
        }else{
            Toast.makeText(this,"Empty object.",Toast.LENGTH_LONG).show()
        }
        super.onActivityResult(requestCode, resultCode, data)


    }
}