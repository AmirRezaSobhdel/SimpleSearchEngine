package utils

import PostingList
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


object TokenAndPostingListTableStorageHandler {

    private val kryo = Kryo()
    private val mapFilesDirectory = File("C:/Users/amire/Desktop/map_files/")
    init {
        kryo.register(HashMap::class.java)
        kryo.register(ArrayList::class.java)
        if (!mapFilesDirectory.exists())
            mapFilesDirectory.mkdir()
    }



    fun createPostingListForToken(token: String , postingList: PostingList)
    {
        getMapFile(token[0])?.let {
            it[token] = postingList.getDocIds()
            createOrUpdateMapFile(token[0],it)
        }?: kotlin.run {
            val newMap = HashMap<String,ArrayList<Int>>()
            newMap[token] = postingList.getDocIds()
            createOrUpdateMapFile(token[0],newMap)
        }

    }

    fun addDocIdToPostingListOfToken(token: String , docId: Int)
    {
        getMapFile(token[0])?.let {

            it[token]?.let { al ->
                al.add(docId)
            }?: kotlin.run {
                it[token] = arrayListOf(docId)
            }

            createOrUpdateMapFile(token[0],it)

        }?: kotlin.run {

            val newMap = HashMap<String,ArrayList<Int>>()
            newMap[token] = arrayListOf(docId)
            createOrUpdateMapFile(token[0],newMap)

        }

    }

    fun getPostingListOfToken(token: String): PostingList?
    {
        getMapFile(token[0])?.let {
            return PostingList(it[token])
        }?: kotlin.run {
            return null
        }
    }


    /** getMapFile returns the hashmap which is retrieved from its holder file , if there is no
     * such file , then it will return null**/
    fun getMapFile(firstLetterOfToken: Char): HashMap<String,ArrayList<Int>>?
    {
        val x = firstLetterOfToken.toString()
        mapFilesDirectory.listFiles()?.forEach {
            if (it.name == "$firstLetterOfToken.bin") {

                val file = if (firstLetterOfToken.isLetterOrDigit()) File(mapFilesDirectory,"$firstLetterOfToken.bin")
                else File(mapFilesDirectory,"signs.bin")

                val input = Input(FileInputStream(file))
                val mapInFile: HashMap<String, ArrayList<Int>> = kryo.readObject(input, HashMap::class.java) as HashMap<String, ArrayList<Int>>
                input.close()

                return mapInFile
            }
        }
        return null
    }


    fun createOrUpdateMapFile(firstLetterOfToken: Char , map: HashMap<String,ArrayList<Int>>)
    {

        val file = if (firstLetterOfToken.isLetterOrDigit()) File(mapFilesDirectory,"$firstLetterOfToken.bin")
        else File(mapFilesDirectory,"signs.bin")


        val output = Output(FileOutputStream(file))
        kryo.writeObject(output, map)
        output.close()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val newMap = HashMap<String,String>()
        newMap["hello"] = "what did you say ?!"

        val file = File(mapFilesDirectory , "a.bin")

        val output = Output(FileOutputStream(file))

        kryo.writeObject(output, newMap)
        output.close()
    }

}