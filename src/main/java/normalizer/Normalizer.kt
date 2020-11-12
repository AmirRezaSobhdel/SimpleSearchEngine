package normalizer

 object Normalizer {

    /** todo: equivalence class normalizing **/

    @JvmStatic
    fun normalize(input: String): String
    {
        val charList = input.toMutableList()

        for (it in charList.indices) {
            when(input[it])
            {

                /** character level normalizing **/

                // arabic letters
                'ي' -> charList[it] = 'ی'
                'ئ' -> charList[it] = 'ی'

                'ك' -> charList[it] = 'ک'

                'ؤ' -> charList[it] = 'و'

                'ة' -> charList[it] = 'ه'

                'أ' -> charList[it] = 'ا'
                'إ' -> charList[it] = 'ا'
                'ٱ' -> charList[it] = 'ا'
                'آ' -> charList[it] = 'ا'
                'ء' -> charList[it] = ' '

                // digits
                '0' -> charList[it] = '۰'
                '1' -> charList[it] = '۱'
                '2' -> charList[it] = '۲'
                '3' -> charList[it] = '۳'
                '4' -> charList[it] = '۴'
                '5' -> charList[it] = '۵'
                '6' -> charList[it] = '۶'
                '7' -> charList[it] = '۷'
                '8' -> charList[it] = '۸'
                '9' -> charList[it] = '۹'



                /** punctuation normalizing **/
                '.' -> charList[it] = ' '
                ';' -> charList[it] = ' '
                ',' -> charList[it] = ' '
                ':' -> charList[it] = ' '
                '؛' -> charList[it] = ' '
                '؟' -> charList[it] = ' '
                '?' -> charList[it] = ' '
                '!' -> charList[it] = ' '
                '،' -> charList[it] = ' '

                // hyphen
                '-' -> charList[it] = ' '
                // Em dash
                '—' -> charList[it] = ' '
                // En dash
                '–' -> charList[it] = ' '
                // Brackets
                '[' -> charList[it] = ' '
                ']' -> charList[it] = ' '
                // Braces
                '{' -> charList[it] = ' '
                '}' -> charList[it] = ' '
                // Parentheses
                '(' -> charList[it] = ' '
                ')' -> charList[it] = ' '
                // apostrophe
                '\'' -> charList[it] = ' '
                //Quotations mark
                '"' -> charList[it] = ' '

//                '<' -> charList[it] = ' '
//                '>' -> charList[it] = ' '
//                '=' -> charList[it] = ' '
//                '+' -> charList[it] = ' '
//                '%' -> charList[it] = ' '
//                '#' -> charList[it] = ' '
//                '*' -> charList[it] = ' '
//                '|' -> charList[it] = ' '
//                '^' -> charList[it] = ' '
//                '&' -> charList[it] = ' '
//                '_' -> charList[it] = ' '
//                '$' -> charList[it] = ' '
//                '~' -> charList[it] = ' '


            }
        }
//        println(charList.toCharArray().concatToString().toLowerCase())
        /** to lower case normalizing **/
        return charList.toCharArray().concatToString().toLowerCase()
    }

     @JvmStatic
     fun main(args: Array<String>) {
         var x = "d      s h".split("\\s+".toRegex())
         println(x)
     }

}