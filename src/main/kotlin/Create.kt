package create

import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit


/* * * * * * * * * * * * * * * *
    Creating observables start
* * * * * * * * * * * * * * * */

fun just()
{
    println("Create integer type Observable with Just")
    println("========================================")
    val sourceInt = Observable.just(1,2,3,4,5)
    sourceInt.subscribe { emtr -> println( emtr ) }

    println("Create string type Observable with Just")
    println("========================================")
    val sourceText = Observable.just("One","Two","Three","Four","Five")
    sourceText.subscribe { emtr -> println(emtr) }

    println("Create Mix Observable with Just")
    println("========================================")
    val sourceMix = Observable.just(1,"Two", 3, "Four", 5)
    sourceMix.subscribe { emtr -> println(emtr) }
}



fun create()
{
    val source = Observable.create <String> { emtr ->
        emtr.onNext("one")
        emtr.onNext("two")
        emtr.onNext("three")
        emtr.onNext("four")
        emtr.onNext("five")
        emtr.onComplete()
    }

    source.subscribe {emtr -> println("Value from source : $emtr") }
}



fun range ()
{
    val source = Observable.range(1,5)
    source.subscribe{ emtr -> println(emtr) }
}



fun interval()
{
    val delay : Long = 2

    println("Display emission after every $delay seconds")

    val source = Observable.interval(delay, TimeUnit.SECONDS)

    source.subscribe{emtr -> println(emtr)}

    Sleep(10)
}


fun timer()
{
    val source = Observable.timer(1, TimeUnit.SECONDS)

    source.subscribe{emtr -> println(emtr)}

    Sleep(10)
}

/* * * * * * * * * * * * * * *
    Creating observable ends
* * * * * * * * * * * * * * * */


fun main()
{
    create()
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