package Buffer

import create.Sleep
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


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

    // source.map { it * 10 }
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

    val boundry = Observable.interval(1, TimeUnit.SECONDS)
    val source = Observable.interval(200, TimeUnit.MILLISECONDS)
    source.buffer(boundry).subscribe{ println(it)}

    Sleep(5)
}


fun main() {
    boundaryBasedBuffer()
}