package combine

import io.reactivex.Observable
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


fun mergeInfiniteSources() {


    val source1 = Observable.interval(1, TimeUnit.SECONDS).map { " Source 1 : $it" }

    val source2 = Observable.interval(500, TimeUnit.MILLISECONDS).map { " Source 2 : $it" }

    // val combine = Observable.merge(source1,source2)

    val combine = source1.mergeWith(source2)

    println("-- Merge infinite source --")

    combine.subscribe{ println( it ) }

    Sleep(5)

}


fun mergeArray () {

    val source1 = Observable.just(1,2,3)
    val source2 = Observable.just(4,5,6)
    val source3 = Observable.just(7,8,9)

    val combine = Observable.mergeArray(source1,source2,source3)

    println("-- Merge Array --")
    combine.subscribe{ println( it ) }

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



fun concatArray () {

    val source1 = Observable.just(1,2,3)
    val source2 = Observable.just(4,5,6)
    val source3 = Observable.just(7,8,9)

    val combine = Observable.concatArray(source1,source2,source3)

    println("-- Merge Array --")
    combine.subscribe{ println( it ) }

}




fun Sleep(seconds: Long)
{
    runBlocking {
        delay(seconds * 1000)
    }
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
    val source1 = Observable.just("A","B","C","D","E")
    val source2 = Observable.range(1,7)

    Observables.zip (source1, source2) { s1, s2 -> "$s1 - $s2" } . subscribe { println ( it ) }
}


fun zipWith()
{
    val source1 = Observable.interval(1, TimeUnit.SECONDS).take(3).map { " Source 1 : $it" }

    val source2 = Observable.interval(500, TimeUnit.MILLISECONDS).map { " Source 2 : "+ it * 500  }

    source1.zipWith(source2) { s1, s2  -> "$s1 - $s2"}
        .subscribe { println(" $it ")}

    Sleep(5)

}



fun combineLatest()
{
    println( "Combine Latest " )

    val source1 = Observable.interval(1, TimeUnit.SECONDS).take(3).map { " Source 1 : $it" }

    val source2 = Observable.interval(500, TimeUnit.MILLISECONDS).map { " Source 2 : "+ it * 500  }

    Observables.combineLatest( source1, source2 ) { s1, s2 -> "$s1 - $s2" }.subscribe { println(" $it ") }

    Sleep(5)
}


fun combineWithLatestFrom(){

    println( "Combine With Latest From " )

    val source1 = Observable.interval(1, TimeUnit.SECONDS).map { " Source 1 : $it" }

    val source2 = Observable.interval(500, TimeUnit.MILLISECONDS).map { " Source 2 : "+ it * 500  }

    source1.withLatestFrom(source2) { s1, s2 -> "$s1 - $s2" }.subscribe { println(" $it ") }
    //source2.withLatestFrom(source1) { s1, s2 -> "$s1 - $s2" }.subscribe { println(" $it ") }

    Sleep(5)

}


fun groupWithString() {

    val source = Observable.just("one","two","three","four","five")
    val lengths = source.groupBy { s -> s.length }
    lengths.flatMapSingle { myGroup -> myGroup.toList() } .subscribe { println(it) }
}


fun groupWithInt() {

    val source = Observable.just(1,2,5,3,5,2,1)
    val groupedObservable = source.groupBy { it }
    groupedObservable.flatMapSingle { eachGroup -> eachGroup.toList() } .subscribe { println(it) }
}

fun grouping() {

    val source = Observable.just("one","two","three","four","five", "Six")
    val lengths = source.groupBy { s -> s.length }

    lengths.flatMapSingle { group -> group.reduce ("") { x, y ->  "$x  $y" }
           .map { "${group.key}  : " + it } }
           .subscribe { println(it) }

}


fun groupByInt() {

    val source = Observable.just(1,2,5,3,5,2,1)

    val types = source.groupBy { it }

    types.flatMapSingle { myGroup -> myGroup.toList() } .subscribe { println(it) }
}


fun throttle() {

    val source1 = Observable.interval(500, TimeUnit.MILLISECONDS).map { " Source 1 : $it" }.throttleFirst(2, TimeUnit.SECONDS)

    source1.subscribe{ println( it ) }

    Sleep(10)
}


fun onDispose() {

    val observable = Observable.just(1, 2, 3, 4, 5)

    observable.doOnSubscribe { println("subscribing") }
        .doOnDispose { println("Emission is disposed off") }
        .subscribe { integer -> println(integer) }

}


fun main() {
    grouping()
}