package create

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit



/* * * * * * * * * * * * * * * *
    Creating observables start
* * * * * * * * * * * * * * * */

fun just ()
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


fun range ()
{
    val source = Observable.range(1,5)
    source.subscribe{ emtr -> println(emtr) }
}

fun interval ()
{
    val delay : Long = 2

    println("Display emission after every $delay seconds")

    val source = Observable.interval(delay, TimeUnit.SECONDS)

    source.subscribe{emtr -> println(emtr)}

    Sleep(10)
}


fun timer ()
{
    val delay : Long = 2

    println("Display emission after every $delay seconds")

    val source = Observable.timer(delay, TimeUnit.SECONDS)

    source.subscribe{emtr -> println(emtr)}

    Sleep(10)
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
        emtr.onError(Throwable("Error"))
    }

    source.subscribe {emtr -> println("Value from source : $emtr") }
}


fun createWithInterface() {

    val observer: Observer<String> = object : Observer<String> {

        override fun onComplete() {
            println("**** Completed Successfully ****")
        }

        override fun onNext(it: String) {
            println("onNext $it")
        }

        override fun onError(error: Throwable) {
            println("Error : ${error.message}")
        }

        override fun onSubscribe(disposable: Disposable) {
            println("**** New Subscriber ****")
        }
    }

    val source = Observable.just("One","Two","Three","Four","Five")

    source.subscribe(observer)
}



fun createWithLambdaExpression(){

    val source = Observable.just("One","Two","Three","Four","Five")

    println("*** On next, on error and on complete ***")
    println("*****************************************")

    source.subscribe( { println( "On Next $it" ) }, { it.printStackTrace() } , { println("Done") } )

    println("\n*** On next and On complete ***")
    println("*****************************************")
    source.subscribe( { println( "On Next $it" ) }, { println("Done") } )

    println("\n*** On next ***")
    println("*****************************************")
    source.subscribe( { println( "On Next $it" ) } )

}

fun empty(){

    val source = Observable.empty<String>()

    println("*** Empty ***")
    println("****************")

    source.subscribe( { println( "On Next $it" ) }, { it.printStackTrace() } , { println("Done") } )
}


fun never(){

    val source = Observable.never<String>()

    println("*** Empty ***")
    println("****************")

    source.subscribe( { println( "On Next $it" ) }, { it.printStackTrace() } , { println("Done") } )

    Sleep(5)
}


fun single() {

    val source = Single.just("One")

    println("*** On next, on error ***")
    println("*****************************************")

    source.subscribe( { println( "On Next $it" ) }, { it.printStackTrace() } )
}

fun maybe() {

    val source1 = Maybe.just("One")

    println("*** May be 1 ***")
    println("****************")

    source1.subscribe( { println( "On Next $it" ) }, { it.printStackTrace() } , { println("Done") } )

    val source2 = Maybe.empty<String>()

    println("\n*** May be 2 ***")
    println("****************")

    source2.subscribe( { println( "On Next $it" ) }, { it.printStackTrace() } , { println("Done") } )
}





/* * * * * * * * * * * * * * *
    Creating observable ends
* * * * * * * * * * * * * * * */


fun main()
{
    maybe()
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