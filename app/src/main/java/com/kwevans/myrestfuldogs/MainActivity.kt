package com.kwevans.myrestfuldogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var retService: DogsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retService = RetrofitInstance
                .getRetrofitInstance()
                .create(DogsService::class.java)
        getSomeDogs()
//        uploadDog()
    }

    private fun getSomeDogs(){
        val responseLiveData: LiveData<Response<Dogs>> = liveData {
            val response = retService.getDogs()
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val dogsList = it.body()?.listIterator()
            if (dogsList != null) {
                while (dogsList.hasNext()) {
                    val dog = dogsList.next()
                    val result = "\n ${dog.name} is a ${dog.breed}" + "\n"
                    text_view.append(result)
                }
            }
        })
    }

    private fun uploadDog(){
        val dog = Dog(0,"Dotty", "Spotty Dog")
        val postResponse: LiveData<Response<Dog>> = liveData {
            val response = retService.uploadDog(dog)
            emit(response)
        }

        postResponse.observe(this, Observer
        {
            val receivedDog = it.body()
            val result = " " + "Dog added: ${receivedDog?.name}" + "\n"

            text_view.text = result
        })
    }
}