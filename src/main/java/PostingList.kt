import utils.BinarySearchUtils
import java.util.ArrayList

class PostingList {

    constructor()
    {

    }

    constructor(docIds: ArrayList<Int>?)
    {
        docIds?.let {
            this.docIds = it
            return
        }

        this.docIds = arrayListOf()

    }

    private var docIds = arrayListOf<Int>()

    fun size(): Int = docIds.size

    fun add(docId: Int)
    {
        // i use binary insertion to keep the docIds list sorted as i keep adding elements to it
        BinarySearchUtils.insertIntToList(docIds,docId)
    }

    fun getDocIds() = docIds

    fun and(other: PostingList): PostingList
    {
        val result = PostingList()
        var i = 0
        var j = 0

        while (i < size() && j < other.size())
        {
            var a = docIds[i]
            var b = other.docIds[j]
            if (a == b)
            {
                result.add(a)
                i++
                j++
            }
            else if (a < b)
                i++
            else j++
        }
        return result
    }

    fun or(other: PostingList): PostingList
    {
        val result = PostingList()
        val orSet = mutableSetOf<Int>()

        orSet.addAll(docIds)
        orSet.addAll(other.docIds)

        orSet.forEach {
            result.add(it)
        }

        return result
    }

    fun not(allDocIds: List<Int>): PostingList
    {

        val result = PostingList()

        // allDocIds list is sorted
        // docIds is sorted as well

        allDocIds.forEach {
            if (it < docIds.first())
                result.add(it)
            else if (it > docIds.last())
                result.add(it)
            else {
                if (!BinarySearchUtils.binarySearchExists(docIds,it))
                    result.add(it)
            }
        }
        return result
    }

}