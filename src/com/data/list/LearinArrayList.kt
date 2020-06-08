package com.data.list

class LearinArrayList<E>() : MutableList<E> {
    override val size: Int = 0
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
        if (contents.size < index || index >= size) {
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

    inner class InnderIter : MutableIterator<E> {
        var currentIndex = 0;
        override fun hasNext(): Boolean {
            return currentIndex < size
        }

        override fun next(): E {
            return get(++currentIndex)
        }

        override fun remove() {
            TODO("Not yet implemented")
        }

    }


    override fun iterator(): MutableIterator<E> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: E): Int {
        TODO("Not yet implemented")
    }

    override fun add(element: E): Boolean {
        TODO("Not yet implemented")
    }

    override fun add(index: Int, element: E) {
        TODO("Not yet implemented")
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun addAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun listIterator(): MutableListIterator<E> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): MutableListIterator<E> {
        TODO("Not yet implemented")
    }

    override fun remove(element: E): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAt(index: Int): E {
        TODO("Not yet implemented")
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun set(index: Int, element: E): E {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
        TODO("Not yet implemented")
    }
}