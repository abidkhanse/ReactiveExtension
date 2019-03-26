
/* * * * * * * * * * * * * * * *
    Higher order function
* * * * * * * * * * * * * * * */


fun highOrdExample() {
    val range = 1..5
    range.forEach { println(it) }
}


fun myPrint(i: Int){
    print(i)
}

fun hrFunc(fn : () -> Unit) {
    fn()
}

fun func(){
    println("Hello")
}

/*
fun main()
{
    hrFunc { func() }

}
*/

fun func01(i: Int, j : Int): Int {
    return i + j
}

fun hrFunc01 ( i:Int, j:Int, fn : (Int,Int) -> Int) {

    println(fn(i,j))

}

/*
fun main()
{
    hrFunc01(2,3, ::func01)
}
*/




val lambda : (Int)-> Unit = {i : Int -> println(i)}

fun lxFunc(i : Int,  fn : (Int) -> Unit) {
    fn(i)
}

fun main() {
    lxFunc (6,lambda)
}

val calc1 : (Int, Int) -> Int = { x, y -> x + y }
val calc2 = { x : Int, y : Int -> x + y }


fun lxFunc01 (i: Int, j:Int, fn : (Int,Int) -> Int) {
    println(fn(i,j))
}

/*
fun main() {
    lxFunc01 (6,3,calc1)
    lxFunc01 (6,3,calc2)
}
*/

