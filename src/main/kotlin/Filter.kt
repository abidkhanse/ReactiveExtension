package filter

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit


/* * * * * * * * * * * * * * * *
    Filter observables
* * * * * * * * * * * * * * * */


//**** Filter ****//

fun filter()
{
    val observable = Observable.
        just("One", "Two", "Three", "Four", "Five", "Six", "Seven")

    observable.filter { emson -> emson.length > 3 }
        .subscribe(::println )
}

//**** Distinct ****//

fun distinct(){
    val source = Observable.just(2, 1, 2, 2, 2, 3, 4, 4, 5, 4)

    source.distinct ().subscribe { println(it) }
}

fun distinctUntilChanged(){

    val source = Observable.just(2, 1, 2, 2, 2, 3, 4, 4, 5, 4)

    source.distinctUntilChanged ().subscribe { println(it) }
}



// **** Skip **** //

fun skip()
{
    var number : Long = 3
    val source = Observable.just("One", "Two", "Three", "Four", "Five")
    source.skip(number)
        .subscribe { emt -> print("$emt ") }

    number = 15
    val range = Observable.range(1, 20)
    range.skip(number)
        .subscribe { emt -> print("$emt ") }


    // Skip emission of first 5 seconds

    number = 5

    val sourcetime = Observable.interval(1, TimeUnit.SECONDS)
    sourcetime.skip(number, TimeUnit.SECONDS)
        .subscribe{ println(it)}

    Sleep(10)

}

fun skipWhile()
{
    val range = Observable.range(1,10)

    range.skipWhile{ it < 5}
        .subscribe{ println(it)}
}


fun skipUntil()
{
    val source = Observable.interval(200, TimeUnit.MILLISECONDS)

    val target = Observable.interval(1000, TimeUnit.MILLISECONDS)

    source.skipUntil(target).subscribe { println(it) }

    Sleep(5)
}


fun skipLast()
{
    var number = 3

    val source = Observable.just("One", "Two", "Three", "Four", "Five")

    source.skipLast(number)
        .subscribe { emt -> println("$emt ") }

    // Skip first 5 seconds start emission

    val sourcetime = Observable.interval(1, TimeUnit.SECONDS)
    sourcetime.skipLast(5, TimeUnit.SECONDS)
        .subscribe{ println(it)}

    Sleep(20)

}



// **** Element **** //

fun element()
{

    var index : Long = 5

    val source = Observable.range(1,10)
    source.elementAt(index).subscribe{ println(it) }

    index = 20
    source.elementAt(index,100).subscribe( Consumer { print(it) })

}

fun elementOnError()
{
    val source = Observable.range(1,10)

    val observer : SingleObserver<Any> = object : SingleObserver<Any> {

        override fun onError(e: Throwable) {
            println("Error ${e.message}")
        }

        override fun onSubscribe(d: Disposable) {
            println("New Subscription ")
        }

        override fun onSuccess(t: Any) {
            println("Success $t")
        }
    }

    source.elementAtOrError(20).subscribe(observer)

    source.elementAtOrError(5).subscribe(observer)

}




fun main()
{
    elementOnError()
}


fun onDispose() {

    val observable = Observable.just(1, 2, 3, 4, 5)

    observable.doOnSubscribe { println("subscribing") }
        .doOnDispose { println("Emission is disposed off") }
        .subscribe { integer -> println(integer) }

}



fun Sleep(seconds: Long)
{
    runBlocking {
        delay(seconds * 1000)
    }
}
