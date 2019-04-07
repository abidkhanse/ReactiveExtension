package Throttle

import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {
    throttleWithTimeoutEmission()
}

fun throttleWithTimeoutEmission() {

    val obs1Sec = Observable
        .interval(2, TimeUnit.SECONDS)
        .map { "$it 2 Sec Observable" }
        .take(3)

    val obs2MSec = Observable
        .interval(200, TimeUnit.MILLISECONDS)
        .map { "$it 200 M Sec Observable" }
        .take(5)

    val obs5MSec = Observable
        .interval(500, TimeUnit.MILLISECONDS)
        .map { "$it 500 M Sec Observable" }
        .take(3)

    Observable.concat(obs1Sec,obs2MSec,obs5MSec)
        .throttleWithTimeout(1, TimeUnit.SECONDS)
        .subscribe{ println(it)}

    Sleep(10)
}

fun throttleLastEmission() {

    val obs1Sec = Observable
        .interval(1, TimeUnit.SECONDS)
        .map { "$it 1 Sec Observable" }
        .take(5)

    val obs2MSec = Observable
        .interval(200, TimeUnit.MILLISECONDS)
        .map { "$it 200 M Sec Observable" }
        .take(5)

    val obs5MSec = Observable
        .interval(500, TimeUnit.MILLISECONDS)
        .map { "$it 500 M Sec Observable" }
        .take(5)

    Observable.concat(obs1Sec,obs2MSec,obs5MSec)
        .throttleLast(1, TimeUnit.SECONDS)
        .subscribe{ println(it)}

    Sleep(10)
}


fun throttleFirstEmission() {

    val obs1Sec = Observable
        .interval(1, TimeUnit.SECONDS)
        .map { "$it 1 Sec Observable" }
        .take(5)

    val obs2MSec = Observable
        .interval(200, TimeUnit.MILLISECONDS)
        .map { "$it 200 M Sec Observable" }
        .take(5)

    val obs5MSec = Observable
        .interval(500, TimeUnit.MILLISECONDS)
        .map { "$it 500 M Sec Observable" }
        .take(5)

    Observable.concat(obs1Sec,obs2MSec,obs5MSec)
        .throttleFirst(1, TimeUnit.SECONDS)
        .subscribe{ println(it)}

    Sleep(10)

}

fun Sleep(seconds: Long)
{
    runBlocking {
        delay(seconds * 1000)
    }
}


