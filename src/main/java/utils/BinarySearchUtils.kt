package utils

object BinarySearchUtils {
    fun insertIntToList(list: ArrayList<Int>, input: Int)
    {
        list.add(binarySearchInsertionIndex(list,input),input)
    }

    fun binarySearchExists(list: ArrayList<Int>, input: Int): Boolean
    {
        var low = 0
        var high = list.size - 1
        while (low <= high) {
            val mid = (low + high) / 2
            val midVal = list[mid]
            val cmp = midVal.compareTo(input)
            if (cmp < 0) low = mid + 1 else if (cmp > 0) high = mid - 1 else return (list[mid] == input)
        }
        return (list[high] == input || list[low] == input)
    }

    private fun binarySearchInsertionIndex(list: ArrayList<Int>, input: Int): Int {
        var low = 0
        var high = list.size - 1
        while (low <= high) {
            val mid = (low + high) / 2
            val midVal = list[mid]
            val cmp = midVal.compareTo(input)
            if (cmp < 0) low = mid + 1 else if (cmp > 0) high = mid - 1 else return mid
        }
        return low
    }


    @JvmStatic
    fun main(args: Array<String>) {
        val list = arrayListOf<Int>()
        insertIntToList(list,2)
        insertIntToList(list,1)
        insertIntToList(list,-10)
        insertIntToList(list,8)
        insertIntToList(list,3)
        insertIntToList(list,12)
        insertIntToList(list,0)
        print(binarySearchExists(list,5))
    }

}