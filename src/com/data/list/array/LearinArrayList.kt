package com.data.list.array

import java.util.*
import kotlin.math.max

class LearinArrayList<E>() : MutableList<E> {
    override var size: Int = 0
    var contents: Array<Any?> = emptyArray()

    init {
        if (size > 0) {
            contents = arrayOfNulls<Any?>(size)
        }
    }

    override fun contains(element: E): Boolean {
        return indexOf(element) > -1
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        return elements.any { !contains(it) }
    }

    override fun get(index: Int): E {
        if (index < 0 || index >= size) {
            throw  IndexOutOfBoundsException()
        }
        return contents[index] as E
    }

    override fun indexOf(element: E): Int {
        for ((index, data) in contents.withIndex()) {
            if (element === data) {
                return index
            }
        }
        return -1
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    inner open class InnerIter : MutableIterator<E> {
        open var cursor = 0
        open var last = -1
        override fun hasNext(): Boolean {
            return cursor < size
        }

        override fun next(): E {
            last = cursor
            return get(cursor++)
        }

        override fun remove() {
            removeAt(cursor)
            cursor = last
        }
    }

    inner class ListItr(cursor: Int = 0) : InnerIter(), MutableListIterator<E> {
        init {
            this.cursor = cursor
        }

        override fun hasPrevious(): Boolean {
            return cursor != 0
        }

        override fun nextIndex(): Int {
            return cursor;
        }

        override fun previous(): E {
            val i = cursor - 1
            if (i < 0) throw NoSuchElementException()
            if (i >= contents.size) throw ConcurrentModificationException()
            cursor = i
            return contents[i.also { last = it }] as E
        }

        override fun previousIndex(): Int {
            return cursor - 1
        }

        override fun add(element: E) {
            TODO("Not yet implemented")
        }

        override fun set(element: E) {
            TODO("Not yet implemented")
        }

    }


    override fun iterator(): MutableIterator<E> {
        return InnerIter()
    }

    override fun lastIndexOf(element: E): Int {
        for (index in size downTo 0) {
            get(index) == element
            return index;
        }
        return -1

    }

    override fun add(element: E): Boolean {
        val targetSize = size + 1
        checkCapitalGrow(targetSize)
        contents[targetSize - 1] = element;
        size = targetSize;
        return true;
    }

    private fun checkCapitalGrow(targetSize: Int) {
        if (targetSize > contents.size) {
            var toSize = targetSize
            if (contents.isEmpty()) {
                toSize = max(toSize, 10)
            }
            grow(toSize)


        }

    }

    private val MAX_ARRAY_SIZE = Int.MAX_VALUE - 8

    private fun grow(toSize: Int) {
        var oldCapacity = contents.size
        //这里扩大了为原来的1.5 倍
        var newCapacity = oldCapacity + oldCapacity.shr(1)
        if (newCapacity < toSize) {
            newCapacity = toSize
        }
        if (newCapacity > MAX_ARRAY_SIZE) {
            if (newCapacity > Int.MAX_VALUE) {
                newCapacity = if (toSize > MAX_ARRAY_SIZE) toSize else MAX_ARRAY_SIZE
            }
        }
        contents = contents.copyOf(newCapacity)


    }

    override fun add(index: Int, element: E) {
        if (index > size || size < 0) throw  ArrayIndexOutOfBoundsException()
        val targetSize = size++;
        checkCapitalGrow(targetSize)
        contents.copyInto(contents, index + 1, index, size)
        contents[index] = element
        size++
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
//        val thisCollection = elements as java.util.Collection<E>
//        val a = thisCollection.toArray();
//        val numNew = a.size
//        checkCapitalGrow(size + numNew) // Increments modCount
//        System.arraycopy(a, , contents, size, numNew)
//        size += numNew
//        return numNew != 0
        return false;
    }

    override fun addAll(elements: Collection<E>): Boolean {
        val thisCollection = elements as java.util.Collection<E>
        val a = thisCollection.toArray();
        val numNew = a.size
        checkCapitalGrow(size + numNew) // Increments modCount
        System.arraycopy(a, 0, contents, size, numNew)
        size += numNew
        return numNew != 0
    }

    override fun clear() {
        for (index in contents.indices) {
            //主要是为了 gc，如果仅仅将 size 弄成0 的话 里面的引用还是在持有
            contents[index] = null
        }
        size = 0
    }

    override fun listIterator(): MutableListIterator<E> {
        return ListItr(0)
    }

    override fun listIterator(index: Int): MutableListIterator<E> {
        return ListItr(index)
    }

    override fun remove(element: E): Boolean {
        TODO("Not yet implemented")


    }

    override fun removeAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }


    override fun removeAt(index: Int): E {
        val result = elementAt(index)
        val numRemoved = size - index - 1
        if (numRemoved > 0) {
            contents = contents.copyInto(contents, index, index + 1, size)
        }
        contents[size - 1] = null
        size--
        return result
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun set(index: Int, element: E): E {
        val oldValue = get(index)
        if (index in 0 until size) {
            contents[index] = element
        } else {
            throw  IndexOutOfBoundsException()
        }
        return oldValue;
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
        TODO("Not yet implemented")
    }
}