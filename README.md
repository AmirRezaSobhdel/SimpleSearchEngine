# SimpleSearchEngine

a simple search engine based on inverted index and posting lists , written in Kotlin

**note**: this project is for my Information Retrieval course in the university and is not written for industrial purposes. 



## A General Look At Classes



### Document.kt

document class is a data class containing title , body and docId of each documents .

### DocumentStore.kt

document store class contains a **TreeMap** with **docId as key** and **Document as value**.

**Why a TreeMap?** it has time complexity of **O(nlogn)** in inserting and deleting items but the keys inserted into it will be in ascending order and this will contribute to the access time related operations which I will discuss.

### PostingList.kt

posting list class contains an **ArrayList of docId's ** that represents documents that a token exists in .

this class also contains some key functions for searching :

**and** : returns a posting list object containing docId's that two tokens exist in at the same time.

**or** : returns a posting list object containing docId's that one of or both tokens exist in at the same time.

**not** : returns a posting list object containing all docId's that this token doesn't exist in.

### InvertedIndex.kt

inverted index class contains a **HashMap** with **token as key** and **PostingList as value**.

the **add** function in this class is responsible for **processing and tokenization of the body of the added document** and then adding docId to PostingList of each token in the hashMap.



### utils/BinarySearchUtils.kt

for optimization purposes , docId lists need to be sorted and so to keep the docId lists sorted I've used binary search and insertion.

### utils/TokenAndPostingListTableStorageHandler.kt

this class is responsible for all storage logic and operations (posting lists for all tokens are stored on hard disk) .



## Storage Of Tokens And Their PostingLists on HardDisk

 in this project my main focus was on optimization of access time and hence for preprocessing and storage time optimization there are still lots of works to do.

### Logic

tokens and their postingLists are stored **Distributedly** in hashmaps and these hashmaps are stored in files. I store all tokens in a single hashmap and store it in a single file but to reduce the **IO operation time** I distributed the tokens in hashmaps that each own a file.

**for example** : 

token **"hello"** will be stored in a hashmap and this hashmap will be written to file **h.bin**

token **moon"** will be stored in a hashmap and this hashmap will be written to file **m.bin**



**so when adding a token** : 

* a file named as the first letter of the token is created
* if the file was created before then its just retrieved, not recreated
* this file will contain or is containing a hashmap with with **token as key** and **PostingList as value**
* all the tokens in this hashmap are started with the same character
* the token and its posting list will be added to this hashmap
* hashmap will be written to the file

### Implementation

to write the hashmaps to the files I had to use serialization and I knew that Java's built in serializer was slow

so I decided to use [Kryo](https://github.com/EsotericSoftware/kryo) serialization library.

