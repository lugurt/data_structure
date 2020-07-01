package com.data.list.link

import java.awt.HeadlessException

/**
 * 这里没有处理，add的 node 有对象的问题
 * 暂时不支持 添加列表的元素
 */
class SimpleLink<E : Comparable<E>> : Iterable<E> {
    val headNode: LinkNode = LinkNode(null, null)
    var lastNode: LinkNode = headNode

    inner class LinkNode(var next: LinkNode?, val element: E?)

    fun add(e: E) {
//        if (e == 100) {
//            val insertNode = LinkNode(indexValue(70 as E).second, e);
//            add(insertNode)
//
//        } else {
        val insertNode = LinkNode(null, e);
        add(insertNode)
//        }
    }

    /**
     * 默认认为 一个e 只对应1个node
     */
    fun remove(e: E): Boolean {
        val result = indexValue(e)
        var pre = result.first
        var indexNode = result.second

        indexNode?.let {
            remove(pre, it)
            return true
        }
        return false
    }

    fun indexValue(e: E): Pair<LinkNode, LinkNode?> {
        var preNode = headNode
        var indexNode = headNode.next
        while (indexNode != null) {
            if (indexNode.element == e) {
                break
            }
            preNode = indexNode
            indexNode = indexNode.next
        }
        return Pair(preNode, indexNode)
    }

    fun indexNode(node: LinkNode): Pair<LinkNode, LinkNode?> {
        var preNode = headNode
        var indexNode = headNode.next
        while (indexNode != null) {
            if (indexNode === node) {
                break
            }
            preNode = indexNode
            indexNode = indexNode.next
        }
        return Pair(preNode, indexNode)
    }


    fun add(node: LinkNode) {
        lastNode.next = node
        lastNode = node
        //node.next = null
    }

    fun remove(preNode: LinkNode, removeNode: LinkNode) {
        preNode.next = removeNode.next
    }

    fun remove(node: LinkNode) {
        indexNode(node).let { pair ->
            {
                pair.second?.let {
                    remove(pair.first, it)
                }
            }
        }
    }

    fun isCircle(): Int {
        var count = 0
        var slowNode: LinkNode? = headNode
        var fastNode: LinkNode? = headNode
        var slowIndex = 0
        var fastIndex = 0
        var firstCount = 0
        var xiangzhuang = 0

        while (slowNode != null && fastNode != null) {
            count += 1
            if (count % 2 == 0) {
                slowNode = slowNode!!.next
                slowIndex += 1
            }
            fastNode = fastNode!!.next
            fastIndex += 1
            if (slowNode == fastNode) {
                println("count$count")
                println("fastIndex$fastIndex")
                xiangzhuang += 1
                if (xiangzhuang == 1) {
                    firstCount = count
                    count = 0
                } else if (xiangzhuang == 2) {
                    println("count$count")
                    println("firstCount$firstCount")
                    return count
                }
            }
        }
        return -1
    }

    fun reverse() {
        var operationNode = headNode.next
        var preNode: SimpleLink<E>.LinkNode = headNode
        while (operationNode != null) {
            val nextNode = operationNode.next
            operationNode.next = preNode
            if (operationNode.next == headNode) {
                operationNode.next = null
            }
            preNode = operationNode
            operationNode = nextNode
        }
        headNode.next = preNode
    }


    override fun iterator(): Iterator<E> {
        return SLItr()
    }

    inner class SLItr : Iterator<E> {
        var curr: LinkNode = headNode
        override fun hasNext() = curr.next != null

        override fun next(): E {
            val nextNode = curr.next;
            curr = nextNode!!
            return nextNode.element!!
        }

    }

    fun merge(link: SimpleLink<E>): SimpleLink<E> {


        var leftCurNode: LinkNode? = headNode.next
        var rightCurNode: LinkNode? = link.headNode.next
        val resultLink = SimpleLink<E>()
        while (true) {
            if (leftCurNode == null && rightCurNode == null) {
                break
            } else if (leftCurNode == null) {
                resultLink.add(rightCurNode!!)
                break
            } else if (rightCurNode == null) {
                resultLink.add(leftCurNode)
                break
            } else {
                val compare = leftCurNode.element?.compareTo(rightCurNode.element!!)
                compare?.let {
                    if (it >= 0) {
                        resultLink.add(rightCurNode!!)
                        rightCurNode = rightCurNode?.next
                    } else {
                        resultLink.add(leftCurNode!!)
                        leftCurNode = leftCurNode?.next
                    }
                }
            }
        }
        return resultLink

    }

    fun getLast(lastIndex: Long): E? {
        var currentIndex: Long = -1
        var currentNode: LinkNode? = headNode
        var isCount = false
        var targetNode: LinkNode? = headNode
        while (currentNode != null) {
            currentNode = currentNode.next
            if (!isCount) {
                currentIndex += 1
                if (currentIndex >= lastIndex) {
                    isCount = true
                }
            } else {
                targetNode = targetNode?.next
            }
        }
        return targetNode?.element

    }

    fun isEmpty() = headNode.next == null


}