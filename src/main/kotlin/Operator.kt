import io.reactivex.Observable

fun main() {
    filter()
}

fun filter()
{
    val observable = Observable.
        just("One", "Two", "Three", "Four", "Five")

    observable.filter { emtr -> emtr.length > 3 }
        .subscribe(::println )
}

fun onDispose() {

    val observable = Observable.just(1, 2, 3, 4, 5)

    observable.doOnSubscribe { println("subscribing") }
        .doOnDispose { println("Emission is disposed off") }
        .subscribe { integer -> println(integer) }

}