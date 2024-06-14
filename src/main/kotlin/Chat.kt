package ru.netology

data class Chat(
//    Чат — это общение с одним человеком, так называемые direct messages.
    val idOwner: Int = 0,
    var messages: MutableList<String> = mutableListOf<String>(),
    var readStatus: Boolean = false,
) {

    override fun toString(): String {

        var tmpStr: String = ""

        this.messages.forEach {
            tmpStr += "\nChat(idOwner=$idOwner, messages=$it, readStatus=$readStatus)"
        }
        return tmpStr+"\n"
    }
}


