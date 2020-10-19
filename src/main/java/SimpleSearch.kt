import testsource.TestSourceRepository
import java.util.*

var texts = TestSourceRepository.texts
val documentStore = DocumentStore()
val invertedIndex = InvertedIndex()
val scanner = Scanner(System.`in`)

fun main() {


    texts.forEach {


        val document = Document(title = it.first , body = it.second.toLowerCase())

        documentStore.add(document)
        invertedIndex.add(document)
    }

    while (true)
    {
        println("Enter your query:")
        val line = scanner.nextLine().toLowerCase()

        if (line == "quit")
            return

        invertedIndex.get(line)?.let {
            it.getDocIds()?.let {
                it.forEach {
                    println(documentStore.get(it)?.title)
                }
            }
        }

    }

}