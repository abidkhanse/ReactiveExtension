package ErrorHandlingdoOn

import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random


fun doOnNextFunction01(){

    var count = 0

    val source = Observable.interval(Random.nextLong(4000), TimeUnit.MILLISECONDS)
    source.doOnNext { print("Total items emitted : ${++count}") }
        .map { (it + 1) * 3 }
        .subscribe{ println(" Result : $it")}

    Sleep(10)

    println("Total count is = $count")
}

fun doOnNextFunction02(){

    val source = Observable.interval(1, TimeUnit.SECONDS)

    source
        .doOnNext { println("\nMap : multiply $it with 3") }
        .map { it * 3 }
        .doOnNext { println("Filter out if odd : $it") }
        .filter{it % 2 == 0L}
        .doOnNext { println("Successfully subscribing : $it") }
        .subscribe{ println("Final Result : $it")}

    Sleep(10)
}

fun doOnErrorFunction() {

    val source = Observable.interval(1, TimeUnit.SECONDS)

    source.map { 100 / (it - 2) }
        .doOnError { println ("Division is failed") }
        .onErrorReturnItem(-10101)
        .subscribe ( {println(it)}, { println(it) } )

    Sleep(5)

}

fun doOnCompleteFunction(){

    val source = Observable.just(1,2,3,4,5)
    source
        .doOnComplete { println("Successfully completed") }
        .subscribe{ println(it) }
}

fun doOnTerminateFunction01(){

    val source = Observable.just(1,2,3,4,5)
    source
        .doOnComplete { println("Successfully completed") }
        .doOnTerminate { println("Terminated") }
        .subscribe ( {println(it)}, { println(it) } )
}


fun doOnTerminateFunction02(){

    val source = Observable.just(1,2,3,4,5)
    source
        .map { it / (it - 3) }
        .doOnComplete { println("Successfully completed") }
        .doOnError{ println("Division is failed")}
        .doOnTerminate { println("Terminated") }
        .subscribe ( {println(it)}, { println(it) } )
}

fun doOnSubscribeAndDoFinally() {

    var startTime = 0L
    var finishTime = 0L

    val source = Observable.interval(Random.nextLong(4000), TimeUnit.MILLISECONDS).take(4)

    source
        .doOnSubscribe {
            println("Subscribing .... ")
            startTime = Calendar.getInstance().timeInMillis
        }
        .doFinally {
            println("Finishing ....")
            finishTime = Calendar.getInstance().timeInMillis
        }
        .subscribe {
            println(" Result : $it")
        }

    Sleep(15)

    val timeConsumed = finishTime - startTime

    val timeInSeconds = TimeUnit.MILLISECONDS.toSeconds(timeConsumed)

    println("Time consumed $timeInSeconds Seconds")

}


fun main() {
    var startTime = 0L
    var finishTime = 0L
    val source = Observable.interval(Random.nextLong(4000), TimeUnit.MILLISECONDS).take(4)

    source
        .doOnSubscribe {
            println("Subscribing .... ")
            startTime = Calendar.getInstance().timeInMillis
        }
        .doFinally {
            println("Finishing ....")
            finishTime = Calendar.getInstance().timeInMillis
        }
        .subscribe {
            println(" Result : $it")
        }

    Sleep(15)

    val timeConsumed = finishTime - startTime
    val timeInSeconds = TimeUnit.MILLISECONDS.toSeconds(timeConsumed)
    println("Time consumed $timeInSeconds Seconds")

}

fun Sleep(seconds: Long) {
    runBlocking {
        delay(seconds * 1000)
    }
}