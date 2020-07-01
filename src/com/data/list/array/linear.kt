package com.data.list.array

/**
 * 数组 是一种线性表数据结构，他用一组连续的内存控件，来存储一组具有相同类型的数据
 *  特性：因为内容连续性，带来了可以随机访问，带来的弱势：插入和删除比较低效，因为你要维护连续性，
 */
fun main(args: Array<String>?) {
    println("开始数组内容")

    testArrayList()
}

fun testArrayList() {
    val learningList: LearinArrayList<Int> = LearinArrayList()
    learningList.add(0)
    learningList.add(1)
    learningList.add(2)
    learningList.add(3)
    learningList.add(4)
    learningList.add(5)
    learningList.add(6)
    learningList.add(7)
    learningList.add(8)
    learningList.add(9)
    learningList.add(10)
    learningList.add(11)
    learningList.add(12)
    learningList.add(13)
    learningList.add(14)
    learningList.add(15)
    learningList.removeAt(4)
    for (num in learningList) {
        println(num)
    }


}