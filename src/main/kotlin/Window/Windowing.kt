package Window
import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {
    boundaryBasedWindow()
}

fun boundaryBasedWindow() {

    val boundary = Observable.interval(1, TimeUnit.SECONDS)
    val source = Observable.interval(500, TimeUnit.MILLISECONDS)

    source.window(boundary)
        .flatMapSingle { obs -> obs.reduce(""){ x, y -> "$x $y" } }
        .subscribe{ println(it)}

    Sleep(10)
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
