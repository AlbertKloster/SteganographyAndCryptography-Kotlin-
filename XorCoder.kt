package cryptography

class XorCoder {
    fun getXorString(inputString: String, password: String): String {
        val xorString = inputString.encodeToByteArray()
        val passwordByteArray = password.encodeToByteArray()
        val xorStringByteArray = emptyArray<Byte>().toMutableList()
        for (i in xorString.indices) {
            xorStringByteArray.add((xorString[i].toInt() xor
                    passwordByteArray[i % passwordByteArray.size].toInt()).toByte())
        }
        return xorStringByteArray.toByteArray().toString(Charsets.UTF_8)
    }
}