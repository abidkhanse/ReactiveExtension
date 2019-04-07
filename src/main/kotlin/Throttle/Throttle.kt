package Throttle

import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun throttleWithTimeoutEmission() {

    val source = Observable.interval(1500, TimeUnit.MILLISECONDS)
        .map { "$it emission" }

    source.throttleWithTimeout(1000,TimeUnit.MILLISECONDS)
        .subscribe{ println(it)}

    Sleep(10)
}

fun throttleLastEmission() {
    val source = Observable
        .interval(200, TimeUnit.MILLISECONDS)
        .map { "$it" }

    source.throttleLast(1, TimeUnit.SECONDS)
        .subscribe{ println(it)}

    Sleep(10)
}

fun throttleFirstEmission() {

    val source = Observable
        .interval(200, TimeUnit.MILLISECONDS)
        .map { "$it" }

    source.throttleFirst(1, TimeUnit.SECONDS)
        .subscribe{ println(it)}

    Sleep(10)
}

fun main() {
    throttleFirstEmission()
}

fun Sleep(seconds: Long)
{
    runBlocking {
        delay(seconds * 1000)
    }
}


