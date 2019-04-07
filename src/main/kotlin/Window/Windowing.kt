package Window
import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.HashSet
import java.util.concurrent.TimeUnit

fun main() {
    windowWithDisplay()
}

fun boundaryBasedWindow() {

    val boundary = Observable.interval(1, TimeUnit.SECONDS)
    val source = Observable.interval(500, TimeUnit.MILLISECONDS)

    source.window(boundary)
        .flatMapSingle { obs -> obs.reduce(""){ x, y -> "$x $y" } }
        .subscribe{ println(it)}

    Sleep(5)
}


fun timeBasedWindow() {

    val source = Observable.interval(500, TimeUnit.MILLISECONDS)
    val obsSource = source.window(1, TimeUnit.SECONDS)
    obsSource
        .flatMapSingle { obs -> obs.reduce(""){x,y -> "$x $y"} }
        .subscribe{println(it)}

    Sleep(5)
}


fun windowWithSkip() {

    val source = Observable.range(1, 25)
    val obsSource = source.window(2, 4)

    obsSource
        .flatMapSingle { obs -> obs.reduce("") { x, y -> "$x $y" } }
        .subscribe { println(it) }

}

fun windowWithDisplay() {

    val source = Observable.interval(1, TimeUnit.SECONDS)
    val obsSource = source.window(4)
    obsSource.subscribe{ display(it)}
    Sleep(20)
}

fun display(source : Observable<Long>){
    println("\nDisplay observable")
   source.subscribe { print(it) }
}



fun window() {

    val source = Observable.range(1, 25)
    val obsSource : Observable<Observable<Int>> = source.window(4)

    obsSource.
        flatMapSingle{obs -> obs.reduce ("") { x, y ->  "$x  $y" } }.
        subscribe{ println(it)}
}

fun Sleep(seconds: Long)
{
    runBlocking {
        delay(seconds * 1000)
    }
}
