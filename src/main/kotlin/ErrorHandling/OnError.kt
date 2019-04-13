package ErrorHandling

import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun produceAnErrorWithObserverInterface(){

    val source = Observable.interval(1, TimeUnit.SECONDS)
    source.subscribe(
        {
            println( "On Next ${100 / (it-2)}" )
        },
        {
            println(it)
        },
        {
            println("On Complete")
        }
    )
    Sleep(5)
}

fun onErrorReturn(){

    val source = Observable.interval(1, TimeUnit.SECONDS)
    source
        .map { 100 / (it - 2) }
        .onErrorReturn {
            if(it is ArithmeticException)
                -10101
            else
                -11111
        }
        .subscribe{println(it)}

    Sleep(10)
}

fun onErrorReturnItem(){

    val source = Observable.interval(1, TimeUnit.SECONDS)
    source
        .map { 100 / (it - 2) }
        .onErrorReturnItem(-10101)
        .subscribe{println(it)}

    Sleep(10)
}


fun onErrorResumeNext(){
    val source = Observable.interval(1, TimeUnit.SECONDS)
    source
        .map { 100 / (it - 2) }
        .onErrorResumeNext(Observable.interval(1, TimeUnit.SECONDS))
        .subscribe{println(it)}

    Sleep(5)
}

fun onErrorReturn_Error(){
    val source = Observable.interval(1, TimeUnit.SECONDS)
    source
        .map {
            if ((it > 2) and (it % 2 == 0L)) {
                throw error("Throw Error")
            }
            else{it * 100}
        }
        .onErrorReturnItem(-10101)
        .subscribe{println(it)}

    Sleep(5)
}

fun onErrorReturn_Exception(){
    val source = Observable.interval(1, TimeUnit.SECONDS)
    source
        .map {
            if ((it > 2) and (it % 2 == 0L)) {
                throw Exception("Throw Exception")
            }
            else{it * 100}
        }
        .onErrorReturnItem(-10101)
        .subscribe{println(it)}

    Sleep(5)
}

fun onExceptionResumeNext(){

    val source = Observable.interval(1, TimeUnit.SECONDS)
    source
        .map { 100 / (it - 2) }
        .onExceptionResumeNext(Observable.interval(1, TimeUnit.SECONDS))
        .subscribe{println(it)}

    Sleep(5)
}

fun onExceptionResumeNext_Exception(){
    val source = Observable.interval(1, TimeUnit.SECONDS)
    source
        .map {
            if ((it > 2) and (it % 2 == 0L)) {
                throw Exception("Throw Exception")
            }
            else{it * 100}
        }
        .onExceptionResumeNext(Observable.interval(1, TimeUnit.SECONDS))
        .subscribe{println(it)}

    Sleep(10)
}

fun onExceptionResumeNext_Error(){
    val source = Observable.interval(1, TimeUnit.SECONDS)
    source
        .map {
            if ((it > 2) and (it % 2 == 0L)) {
                throw Error("Throw Error")
            }
            else{it * 100}
        }
        .onExceptionResumeNext(Observable.interval(1, TimeUnit.SECONDS))
        .subscribe{println(it)}

    Sleep(10)
}


fun main() {
    onExceptionResumeNext_Exception()
}

fun Sleep(seconds: Long) {
    runBlocking {
        delay(seconds * 1000)
    }
}