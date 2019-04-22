package ErrorHandlingRetry

import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun retry() {

    val source = Observable.interval(1, TimeUnit.SECONDS)
    source.map { 100 / (it - 2) }
        .doOnError{ println("Error:")}
        .retry()
        .subscribe {println(it)}
}

fun retryFixedNumberOfTIme() {

    val source = Observable.interval(1, TimeUnit.SECONDS)
    source.map { 100 / (it - 2) }
        .doOnError{ println("Error:")}
        .retry(2)
        .subscribe {println(it)}

    Sleep(10)
}

fun retryUntil() {

    var count = 0
    val source = Observable.interval(1, TimeUnit.SECONDS)
    source.map { 100 / (it - 2) }
        .retryUntil  { ++count > 2   }
        .subscribe ( {println(it)}, { println("ERROR $it") } )

    Sleep(10)
}


fun retryWhen() {

    val source = Observable.interval(1, TimeUnit.SECONDS)
    source.map { 100 / (it - 2) }
        .retryWhen  { it.delay(3, TimeUnit.SECONDS).take(4) }
        .subscribe {println(it)}

    Sleep(15)
}


fun main() {

    retryWhen()
}

fun Sleep(seconds: Long) {
    runBlocking {
        delay(seconds * 1000)
    }
}