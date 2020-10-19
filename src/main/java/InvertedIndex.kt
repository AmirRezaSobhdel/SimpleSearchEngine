import utils.TokenAndPostingListTableStorageHandler
import java.util.HashSet

class InvertedIndex {


    private val table = HashMap<String , PostingList>()

    fun add(document: Document)
    {
        val tokens = document.body.split(" ")
        val distinctTokens = HashSet<String>()

        tokens.forEach {
            distinctTokens.add(it)
        }

        distinctTokens.forEach {
            table[it]?.let { postingList ->
                postingList.add(document.docId)
                TokenAndPostingListTableStorageHandler.addDocIdToPostingListOfToken(it,document.docId)
            }?: kotlin.run {
                table[it] = PostingList()
                table[it]!!.add(document.docId)
                TokenAndPostingListTableStorageHandler.addDocIdToPostingListOfToken(it,document.docId)
            }
        }

    }

    fun get(token: String): PostingList? = TokenAndPostingListTableStorageHandler.getPostingListOfToken(token)

}