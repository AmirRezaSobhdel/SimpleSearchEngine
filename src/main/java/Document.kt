import java.util.concurrent.atomic.AtomicInteger

data class Document(
        var docId: Int = atomicInt.incrementAndGet() ,
        var title: String ,
        var body: String

) {
    companion object {
        val atomicInt = AtomicInteger()
    }
}