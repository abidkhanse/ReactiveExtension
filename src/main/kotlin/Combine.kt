package combine

import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit


/* * * * * * * * * * * * * * * *
    Transform observables
* * * * * * * * * * * * * * * */


/* * * * * * * * * * * * * * *
    Transform observable ends
* * * * * * * * * * * * * * * */


fun main()
{

}

fun Sleep(seconds: Long)
{
    runBlocking {
        delay(seconds * 1000)
    }

}


fun filter()
{
    val observable = Observable.
        just("One", "Two", "Three", "Four", "Five", "Six", "Seven")

    observable.filter { emtr -> emtr.length > 3 }
        .subscribe(::println )
}


fun onDispose() {

    val observable = Observable.just(1, 2, 3, 4, 5)

    observable.doOnSubscribe { println("subscribing") }
        .doOnDispose { println("Emission is disposed off") }
        .subscribe { integer -> println(integer) }

}