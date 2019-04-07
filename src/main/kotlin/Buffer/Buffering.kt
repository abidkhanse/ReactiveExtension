package Buffer

import create.Sleep
import io.reactivex.Observable
import java.util.HashSet
import java.util.concurrent.TimeUnit


fun bufferWithInterface(){

    val source = Observable.range(1,23)

    source.buffer(4).subscribe(
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
}

fun buffer(){

    val source = Observable.range(1,23)
    source.buffer(4).subscribe{ println(it)}
}

fun bufferAndSkip(){

    val source = Observable.range(1,23)

    source.buffer(4,2).subscribe{ println(it)} // Add difference in next emission

    source.buffer(2,4).subscribe{ println(it)} // Skip difference in next emission
}


fun timeBasedBuffer(){

    val source = Observable.interval(200, TimeUnit.MILLISECONDS)
    source
        .buffer(1, TimeUnit.SECONDS).
        subscribe{ println(it)}
    Sleep(5)
}



fun timeBasedMaxBuffer(){

    val source = Observable.interval(200, TimeUnit.MILLISECONDS)
    source.buffer(1, TimeUnit.SECONDS, 3).
            subscribe{ println(it)}

    Sleep(5)
}



fun boundaryBasedBuffer(){

    val boundary = Observable.interval(1, TimeUnit.SECONDS)
    val source = Observable.interval(200, TimeUnit.MILLISECONDS)
    source.buffer(boundary).subscribe{ println(it)}

    Sleep(5)
}



fun display(list : List<Int>){
    println("Display buffer as a list")
    list.forEach { print ("$it ")  }
    println("")
}

fun bufferingList01() {

    val source = Observable.range(1,19)
    source.buffer(5).subscribe{emt -> display(emt) }
}



// Hash set as a result
fun display(hashset : HashSet<Int>){
    println("Display buffer as a Hash set")
    hashset.forEach { print (it)  }
    println("")
}

fun bufferingHashSet01() {

    Observable.range(1, 24)
        .buffer<HashSet<Int>>(5) { HashSet() }
        .subscribe { println(it) }
}

fun bufferingHashSet02() {

    Observable.range(1, 24)
        .buffer<HashSet<Int>>(5) { HashSet() }
        .subscribe { display(it) }
}


fun main() {
    val boundary = Observable.interval(1, TimeUnit.SECONDS)
    val source = Observable.interval(200, TimeUnit.MILLISECONDS)
    source.buffer(boundary).subscribe{ println(it)}

    Sleep(5)
}