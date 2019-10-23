package com.example.rxbuswithrxjava2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerEventObersers()
        initClickAction()
    }

    private fun initClickAction() {
        button.setOnClickListener {
            val data:HashMap<String,String> = HashMap()
            data.put("testData","This is the testing data")
            val event = Event(data)
            Bus.send(event)
        }
    }

    private fun registerEventObersers() {
        Bus.observe<Event>()
            .subscribe {
                runOnUiThread {
                    var testData = it.data.get("testData")
                    Toast.makeText(
                        this,
                        "Receive event:$testData",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            .registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
