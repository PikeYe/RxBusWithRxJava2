# RxBusWithRxJava2
Simple Rx Event Bus implementation using Kotlin for Android and RxKotlin (RxJava2)

```java
//subscribe to events
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
.registerInBus(this)//registers your subscription to unsubscribe it properly later

//send events
val data:HashMap<String,String> = HashMap()
data.put("testData","This is the testing data")
val event = Event(data)
Bus.send(event)

//unsubscribe from events
Bus.unregister(this)
```
