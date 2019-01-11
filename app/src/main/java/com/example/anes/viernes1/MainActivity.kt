package com.example.anes.viernes1

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    companion object {
        private val GrabarCode=123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Foto.setOnClickListener{

        }

        Llamar.setOnClickListener{
            llamar()

        }

        Imagen.setOnClickListener{

        }

        Audio.setOnClickListener{
            grabarAudio()

        }

    }

    fun tomarFoto(){

    }

    fun llamar(){
        val telefono = "5540519495"
        val intent = Intent()
        intent.action = Intent.ACTION_CALL
        intent.data = Uri.parse("tel:" + telefono)
        val permiso = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)
        if(permiso != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),225)
        }else{
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent)
            }
        }


    }

    fun imagen(){

    }

    fun grabarAudio(){
        val intent = Intent()
        intent.action = RecognizerIntent.ACTION_RECOGNIZE_SPEECH
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"es-MX")
        try{
            startActivityForResult(intent, GrabarCode)
        }catch (anfe: ActivityNotFoundException){
            Toast.makeText(this,"Tu telefono no lo puede",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(Activity.RESULT_OK == resultCode){
            if(GrabarCode == requestCode){
                val speech = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                txtV.text=speech?.get(0)
            }

        }
    }
}
