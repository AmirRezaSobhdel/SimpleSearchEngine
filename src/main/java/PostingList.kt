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

    fun and(others: MutableList<PostingList>): PostingList
    {
        val result = PostingList()

        /** this hashmap represents a table of docIds and how many Posting Lists they appear in  **/
        val postingListsDocIdAppearanceTable = hashMapOf<Int,Int>()

        others.add(this)

        others.forEach { postingList ->
            postingList.docIds.forEach { docId ->
                postingListsDocIdAppearanceTable[docId]?.let { count ->
                    postingListsDocIdAppearanceTable[docId] = count + 1
                }?: kotlin.run {
                    postingListsDocIdAppearanceTable[docId] = 1
                }
            }
        }

        postingListsDocIdAppearanceTable.keys.forEach { docId ->
            if (postingListsDocIdAppearanceTable[docId] == others.size)
                result.add(docId)
        }


        /** below code is for ANDing two posting lists **/
//        var i = 0
//        var j = 0
//
//        while (i < size() && j < other.size())
//        {
//            var a = docIds[i]
//            var b = other.docIds[j]
//            if (a == b)
//            {
//                result.add(a)
//                i++
//                j++
//            }
//            else if (a < b)
//                i++
//            else j++
//        }
        return result
    }

    fun or(others: List<PostingList>): PostingList
    {
        val result = PostingList()
        val orSet = mutableSetOf<Int>()

        orSet.addAll(docIds)
        others.forEach {
            orSet.addAll(it.docIds)
        }

        orSet.forEach {
            result.add(it)
        }

        return result
    }

    fun not(allDocIds: ArrayList<Int>): PostingList
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

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {


            val allDocIds = PostingList()
            allDocIds.add(1)
            allDocIds.add(2)
            allDocIds.add(3)
            allDocIds.add(5)
            allDocIds.add(6)
            allDocIds.add(8)
            allDocIds.add(12)
            allDocIds.add(16)
            allDocIds.add(20)

            val postingList1 = PostingList()
            postingList1.add(1)
            postingList1.add(3)
            postingList1.add(8)
            postingList1.add(5)
            postingList1.add(12)

            val postingList2 = PostingList()
            postingList2.add(1)
            postingList2.add(2)
            postingList2.add(5)
            postingList2.add(12)
            postingList2.add(6)

            val postingList3 = PostingList()
            postingList2.add(1)
            postingList2.add(2)
            postingList2.add(5)
            postingList2.add(8)
            postingList2.add(20)
            postingList2.add(16)

            println(postingList1.and(mutableListOf(postingList2)).not(allDocIds.docIds).docIds)
            postingList1.and(mutableListOf(postingList2)).docIds
        }
    }

}