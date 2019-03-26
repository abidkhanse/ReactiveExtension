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
    Create observables
* * * * * * * * * * * * * * * */



fun range ()
{
    val source = Observable.range(10,5)
    source.subscribe{ emtr -> println("Range $emtr") }

    val sourceLong = Observable.rangeLong(10,5)
    sourceLong.subscribe{ emtr -> println("Range Long $emtr") }
}

fun empty(){

    val source = Observable.empty<String>()

    println("*** Empty ***")
    println("****************")

    source.subscribe( { println( "On Next $it" ) }, { it.printStackTrace() } , { println("Done") } )
}


fun never(){

    val source = Observable.never<String>()

    println("*** Never ***")
    println("****************")

    source.subscribe( { println( "On Next $it" ) }, { it.printStackTrace() } , { println("Done") } )

    Sleep(5)
}


fun single() {

    println("*** Single ***")
    val source = Single.just("Only one")

    source.subscribe( { println( "On Next $it" ) }, { it.printStackTrace() } )
}

fun maybe() {

    println("\n*** May be ***")
    val source1 = Maybe.just("One")
    source1.subscribe( { println( "On Next $it" ) }, { it.printStackTrace() } , { println("Done") } )

    println("\n*** May be empty ***")
    val source2 = Maybe.empty<String>()
    source2.subscribe( { println( "On Next $it" ) }, { it.printStackTrace() } , { println("Done") } )
}



fun observerInterface()
{

    val observer: Observer<String> = object : Observer<String> {

        override fun onComplete() {
            println("**** Completed Successfully ****")
        }

        override fun onNext(it: String) {
            println("on Next $it")
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

fun observerInterfaceLambdaExpression(){

    val source = Observable.just("One","Two","Three","Four","Five")

    println("*** On next, on error and on complete ***")
    println("*****************************************")

    source.subscribe(
        {
            println( "On Next $it" )
        },
        {
            it.printStackTrace()
        },
        {
            println("On Complete")
        }
    )

    println("\n*** On next and on error ***")
    println("*****************************************")
    source.subscribe( { println( "On Next $it" ) }  ,  { it.printStackTrace() } )

    println("\n*** On next ***")
    println("*****************************************")
    source.subscribe { println( "On Next $it" ) }

}

fun create()
{
    val source = Observable.create <String>
    { item ->
        item.onNext("One")
        item.onNext("Two")
        item.onNext("Three")
        item.onNext("Four")
        item.onNext("Five")
        item.onComplete()
    }

    source.subscribe {emission -> println("Value from source : $emission") }
}

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



fun interval ()
{
    val delay : Long = 1

    println("Print after every $delay seconds")
    val source = Observable.interval(delay, TimeUnit.SECONDS)
    source.subscribe{emtr -> println(emtr)}

    Sleep(5)
}

fun Sleep(seconds: Long)
{
    runBlocking {
        delay(seconds * 1000)
    }
}



fun timer ()
{
    val delay : Long = 1

    println("Print after every $delay seconds")

    val source = Observable.timer(delay, TimeUnit.SECONDS)

    source.subscribe{emtr -> println(emtr)}

    Sleep(10)
}


fun main()
{
    single()
    maybe()
}

