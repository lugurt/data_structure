package com.data.list.link

fun main() {

    val leftLink = SimpleLink<Int>()
    for (a in 1..100 step 2) {
//        print(a)
        leftLink.add(a)
    }
    val rightLink = SimpleLink<Int>()

    for (a in 0..100 step 2) {
//        print(a)
        rightLink.add(a)
    }
    println()
    val result = leftLink.merge(rightLink)
    for (item in result) {
        println(item)
    }


}