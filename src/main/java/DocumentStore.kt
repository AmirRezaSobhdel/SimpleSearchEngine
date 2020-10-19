import java.util.*

class DocumentStore {

    private val docsHashMap = TreeMap<Int,Document>()

    fun add(document: Document)
    {
        docsHashMap[document.docId] = document
    }
    fun get(docId: Int): Document? = docsHashMap[docId]

    fun getAll() = docsHashMap.values
    fun getALlDocIds() = docsHashMap.keys.toList()

}