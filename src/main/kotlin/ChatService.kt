package ru.netology

object ChatService {
    var chatList: MutableList<Chat> = mutableListOf<Chat>()

    //fun getUnreadChatsCount() = chatList.filter(fun(chat: Chat) = !chat.readStatus).size
    fun getUnreadChatsCount() = chatList.count { chat: Chat -> !chat.readStatus }
    //Видеть, сколько чатов не прочитано (например, service.getUnreadChatsCount). В каждом из таких чатов есть хотя бы одно непрочитанное сообщение.

    fun getChats() = chatList
//        Получить список чатов (например, service.getChats).

//    fun getLastMessagesFromChats(): MutableList<String> {
//        //Получить список последних сообщений из чатов (можно в виде списка строк). Если сообщений в чате нет (все были удалены), то пишется «нет сообщений».
//        val strTmpList = mutableListOf<String>()
//
//        chatList.forEach {
//            strTmpList.add(it.messages.last())
//        }
//        if (strTmpList.size == 0) return mutableListOf<String>("no messages")
//
//        return strTmpList
//    }

    fun getLastMessagesFromChats(): MutableList<String> =
        chatList.map { chat: Chat -> chat.messages.lastOrNull() ?: "NO MESSAGES for ID:${chat.idOwner}" }
            .toMutableList()

//    fun getMessagesFromChat(idOwner: Int, numbersOfMessages: Int) : MutableList<String> {
//        //Получить список сообщений из чата, указав: //ID собеседника; количество сообщений.
//        // После того как вызвана эта функция, все отданные сообщения автоматически считаются прочитанными.
//        val chatListTmp = chatList.filter { chat: Chat -> chat.idOwner == idOwner }
//
//        if (chatListTmp.size == 1) {
//            chatListTmp[0].readStatus = true
//            return (chatListTmp[0].messages.filterIndexed { index, s ->  (chatListTmp[0].messages.size - numbersOfMessages - 1) < index}).toMutableList()
//        } else {
//            throw RuntimeException("<< fun getMessagesFromChat >> ERROR = no message to get")
//        }
//    }

    fun getMessagesFromChat(idOwner: Int, numbersOfMessages: Int): MutableList<String> =
        chatList.asSequence().filter() { chat: Chat -> chat.idOwner == idOwner }
            .ifEmpty { throw RuntimeException("<< fun getMessagesFromChat >> ERROR = no message to get") }
            .onEach { it.readStatus = true }
            .last().messages.takeLast(numbersOfMessages).toMutableList()


//    fun createMessage(idOwner: Int, message: String) {
//        //Создать новое сообщение.
//
//        val chatListTmp = chatList.filter { chat: Chat -> chat.idOwner == idOwner }
//
//        if (chatListTmp.size == 1) {
//            chatListTmp[0].messages.add(message)
//        } else {
//            createChat(idOwner, message)
//        }
//    }

    fun createMessage(idOwner: Int, message: String) {
        chatList.filter { chat: Chat -> chat.idOwner == idOwner }
            .ifEmpty { createChat(idOwner, message); return }
            .last().messages.add(message)
    }
    //Создать новое сообщение.


//    fun deleteMessage(idOwner: Int, message: String) {
//        //Удалить сообщение.
//        val chatListTmp = chatList.filter { chat: Chat -> chat.idOwner == idOwner }
//
//        if (chatListTmp.size == 1) {
//            if (!chatListTmp[0].messages.remove(message)) throw RuntimeException("<< fun deleteMessage >> ERROR = no message to delete")
//        } else {
//            throw RuntimeException("<< fun deleteMessage >> ERROR = no message to delete")
//        }
//
//    }

    fun deleteMessage(idOwner: Int, message: String) {
        //Удалить сообщение.
        chatList.asSequence().filter { chat: Chat -> chat.idOwner == idOwner }
            .ifEmpty { throw RuntimeException("<< fun deleteMessage >> ERROR = no message to delete") }
            .last().messages.remove(message)
            .let { if (!it) throw RuntimeException("<< fun deleteMessage >> ERROR = no message to delete") }
    }

//    private fun createChat(idOwner: Int, message: String) {
//        //Создать чат. Чат создаётся, когда пользователю отправляется первое сообщение.
//        val mess = mutableListOf<String>()
//        mess.add(message)
//
//        chatList.add(Chat(idOwner = idOwner, messages = mess))
//    }

    private fun createChat(idOwner: Int, message: String) {
        //Создать чат. Чат создаётся, когда пользователю отправляется первое сообщение.
        chatList.add(Chat(idOwner = idOwner, messages = mutableListOf<String>(message)))
    }

    //fun deleteChat(idOwner: Int) {
//        //Удалить чат, т. е. целиком удалить всю переписку.
//        if (!chatList.removeIf(fun(chat: Chat) = (chat.idOwner == idOwner))) throw RuntimeException("<< fun deleteChat(idOwner: Int) >> ERROR = nothing to delete")
//    }
    fun deleteChat(idOwner: Int) {
        //Удалить чат, т. е. целиком удалить всю переписку.
        chatList.removeIf(fun(chat: Chat) = (chat.idOwner == idOwner))
            .let { if (!it) throw RuntimeException("<< fun deleteChat(idOwner: Int) >> ERROR = nothing to delete") }
    }

    fun clear() {
        chatList = mutableListOf<Chat>()
    }


}