package combine

import io.reactivex.Observable
import io.reactivex.observables.GroupedObservable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.withLatestFrom
import io.reactivex.rxkotlin.zipWith
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit


/* * * * * * * * * * * * * * * *
    Combine observables
* * * * * * * * * * * * * * * */


fun merging() {

    val source1 = Observable.just(1,2,3)
    val source2 = Observable.just(4,5,6)

    val combine = Observable.merge(source1,source2)

    println("-- Merge --")
    combine.subscribe{ println( it )}

    println("-- Merge with --")
    source1.mergeWith(source2).subscribe{ println( it ) }

}


fun mergeArray () {

    val source1 = Observable.just(1,2,3)
    val source2 = Observable.just(4,5,6)
    val source3 = Observable.just(7,8,9)

    val combine = Observable.mergeArray(source1,source2,source3)

    println("-- Merge Array --")
    combine.subscribe{ println( it ) }

}


fun mergeInfiniteSources() {


    val source1 = Observable.interval(1, TimeUnit.SECONDS).map { " Source 1 : $it" }

    val source2 = Observable.interval(500, TimeUnit.MILLISECONDS).map { " Source 2 : $it" }

    val combine = source1.mergeWith(source2)

    println("-- Merge infinite source --")

    combine.subscribe{ println( it ) }

    Sleep(5)

}


fun concat() {

    val source1 = Observable.just(1,2,3)
    val source2 = Observable.just(4,5,6)

    val combine = Observable.concat(source1,source2)

    println("-- Concat --")
    combine.subscribe{ println( it )}

    println("-- Concat with --")
    source1.concatWith(source2).subscribe{ println( it ) }
}



fun concatInfiniteSources() {

    val source1 = Observable.interval(1, TimeUnit.SECONDS).take(3).map { " Source 1 : $it" }

    val source2 = Observable.interval(500, TimeUnit.MILLISECONDS).map { " Source 2 : $it" }

    val combine = source1.concatWith(source2)

    println("-- Concat infinite source --")

    combine.subscribe{ println( it ) }

    Sleep(5)
}



fun ambiguous(){

    val source1 = Observable.interval(1, TimeUnit.SECONDS)
        .map { " Source 1 : $it" }

    val source2 = Observable.interval(500, TimeUnit.MILLISECONDS)
        .map { " Source 2 : $it" }

    val result = source1.ambWith(source2)

    println("-- Ambiguous infinite source --")

    result.subscribe{ println( it ) }

    Sleep(5)
}



fun zip()
{

    val source1 = Observable.just("one","two","three","four","five")
    val source2 = Observable.range(1,6)

     Observable.zip (source1, source2, io.reactivex.functions.BiFunction
        <String, Int, String> { s1, s2 -> "$s1 $s2" })
        .subscribe { println ( " $it " ) }

}


fun zipWith()
{
    val source1 = Observable.interval(1, TimeUnit.SECONDS).take(3).map { " Source 1 : $it" }

    val source2 = Observable.interval(500, TimeUnit.MILLISECONDS).map { " Source 2 : "+ it * 500  }

    source1.zipWith(source2) { s1, s2  -> "$s1 , $s2"}
        .subscribe { println(" $it ")}

    Sleep(5)

}


fun combineLatest()
{
    println( "combineLatest " )
    val source1 = Observable
        .interval(1, TimeUnit.SECONDS)
        .take(3).map { " Source 1 : $it" }

    val source2 = Observable
        .interval(500, TimeUnit.MILLISECONDS)
        .map { " Source 2 : "+ it * 500  }

    Observables
        .combineLatest( source1, source2 ) { s1, s2 -> "$s1 - $s2" }
        .subscribe { println(" $it ") }

    Sleep(5)
}


fun combineWithLatestFrom(){

    println( "combineWithLatestFrom " )

    val source1 = Observable
        .interval(1, TimeUnit.SECONDS)
        .take(3).map { " Source 1 : $it" }

    val source2 = Observable
        .interval(500, TimeUnit.MILLISECONDS)
        .map { " Source 2 : "+ it * 500  }

    source1.withLatestFrom(source2) { s1, s2 -> "$s1 - $s2" }
        .subscribe { println(" $it ") }

    Sleep(5)

}

fun grouping() {

    val source = Observable.just("one","two","three","four","five")

    val keyvalues : Observable < GroupedObservable<Int, String> > ? = source.groupBy { s -> s.length }

    keyvalues?.flatMapSingle {
            grp -> grp.reduce ("") { x, y ->  "$x , $y" }
            .map { s -> "${grp.key}  : " + s } } ?.subscribe { println(it) }

    //key_values?.flatMapSingle { group -> group.toList() } ?.subscribe { println( it ) }

}

fun main() {

    grouping()

}


fun Sleep(seconds: Long)
{
    runBlocking {
        delay(seconds * 1000)
    }

}



fun onDispose() {

    val observable = Observable.just(1, 2, 3, 4, 5)

    observable.doOnSubscribe { println("subscribing") }
        .doOnDispose { println("Emission is disposed off") }
        .subscribe { integer -> println(integer) }

}


/* * * * * * * * * * * * * * *
    Combine observable ends
* * * * * * * * * * * * * * * */
