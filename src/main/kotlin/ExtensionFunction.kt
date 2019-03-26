
/* * * * * * * * * * * * * * * *
    Extension Function
* * * * * * * * * * * * * * * */





// Normal function
fun printReverse(text : String)
{
    for (i in text.length downTo 1) {
        print(text[i-1])
    }
}

// Extension function
fun String.printReverseEx()
{
    for (i in this.length downTo 1)
    {
        print(this[i-1])
    }
}


// Normal function
fun doubleTheValue(value : Int) : Int {
    return value * 2
}

// Extension function
fun Int.doubleTheValueEx() : Int {
    return this * 2
}


fun BuiltInExtensionFunctionWithList(){

    val range = 1..10
    range.filter { it % 2 != 0 }.map { println("$it  Odd") }
}


infix fun String.isEqual(message : String) :Boolean {
    return this == message
}

fun main (){
    val kotlin = "Kotlin"
    val java = "Java"

    println(kotlin.isEqual(java))
    println(kotlin isEqual java)
}