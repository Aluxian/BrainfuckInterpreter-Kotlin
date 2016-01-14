/**
 * Hello World example:
 * ++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.
 *
 * @docs https://docs.google.com/document/d/1M51AYmDR1Q9UBsoTrGysvuzar2_Hx69Hz14tsQXWV6M/edit?usp=sharing
 */
object Brainfuck {
    val mem = ByteArray(65536)
    var pointer = 0

    fun interpret(program: String) {
        var i = 0
        var l = 0

        while (i <= program.lastIndex) {
            when (program.charAt(i)) {
                '>' -> if (pointer == mem.lastIndex) pointer = 0 else pointer++
                '<' -> if (pointer == 0) pointer = mem.lastIndex else pointer--
                '+' -> mem[pointer]++
                '-' -> mem[pointer]--
                '.' -> print(mem[pointer].toChar())
                ',' -> mem[pointer] = readLine()?.first()?.toByte() ?: 0
                '[' -> {
                    if (mem[pointer].toInt() == 0) {
                        i++

                        while (l > 0 || program.charAt(i) != ']') {
                            if (program.charAt(i) == '[') l++
                            if (program.charAt(i) == ']') l--
                            i++
                        }
                    }
                }
                ']' -> {
                    if (mem[pointer].toInt() != 0) {
                        i--

                        while (l > 0 || program.charAt(i) != '[') {
                            if (program.charAt(i) == ']') l++
                            if (program.charAt(i) == '[') l--
                            i--
                        }

                        i--
                    }
                }
            }

            i++
        }
    }
}

fun main(args: Array<String>) {
    Brainfuck.interpret(readLine().orEmpty())
}
