import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun withoutSwitchMap() {

    val source1 = Observable.interval(1, TimeUnit.SECONDS)
    val source2 = Observable.interval(300, TimeUnit.MILLISECONDS).map { "$it observable" }

    source1.subscribe {
        source2.subscribe(::println)
    }
    Sleep(10)
}

fun switchMap01() {

    val source1 = Observable.interval(2, TimeUnit.SECONDS)
    val source2 = Observable.interval(300, TimeUnit.MILLISECONDS).map { "$it observable" }

    source1.switchMap {
        source2.doOnDispose { println("Disposed") }
    } .subscribe {
        println(it)
    }

    Sleep(10)
}

fun switchMap02() {

    val source = Observable.just("one","two","three","four","five","six","seven")
    val delayedSource = source.concatMap { Observable.just(it).delay (Random.nextLong(2000), TimeUnit.MILLISECONDS) }

    Observable.interval(3, TimeUnit.SECONDS)
        .switchMap {delayedSource.doOnDispose {println("Disposed")}}
        .subscribe{println(it)}

    Sleep(10)

}

fun main() {

    switchMap02()
}

fun Sleep(seconds: Long) {
    runBlocking {
        delay(seconds * 1000)
    }
}