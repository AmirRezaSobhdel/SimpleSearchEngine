import normalizer.Normalizer
import testsource.ReadXmlFile
import testsource.TestSourceRepository
import java.util.*

val documentStore = DocumentStore()
val invertedIndex = InvertedIndex()
val scanner = Scanner(System.`in`)

fun main() {


    var start = System.currentTimeMillis()

    ReadXmlFile().readXml("C:\\Users\\amire\\Desktop\\information retrieval\\simple.xml", object : ReadXmlFile.XmlReadCompletionListener {
        override fun OnXmlReadCompleted(pairs: ArrayList<Pair<String, String>>?) {


            pairs?.forEach {


                val document = Document(title = it.first, body = Normalizer.normalize(it.second))

                documentStore.add(document)
                invertedIndex.add(document)
            }


            var finish = System.currentTimeMillis()

            println("total time is ${finish - start}")

            println("total number of tokens is ${invertedIndex.tokensCount()}")

            while (true) {
                println("Enter your query:")
                val line = Normalizer.normalize(scanner.nextLine())

                if (line == "quit")
                    return

                invertedIndex.get(line)?.let {
                    it.getDocIds().let {
                        it.forEach {
                            println(documentStore.get(it)?.title)
                        }

                        println("total number of docs found is ${it.size}")
                    }
                }

            }
        }

    })

}