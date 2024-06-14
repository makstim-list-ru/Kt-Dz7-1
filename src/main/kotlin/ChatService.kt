package ru.netology

object ChatService {
    var chatList: MutableList<Chat> = mutableListOf<Chat>()

    fun getUnreadChatsCount() = chatList.filter(fun(chat: Chat) = !chat.readStatus).size
    //Видеть, сколько чатов не прочитано (например, service.getUnreadChatsCount). В каждом из таких чатов есть хотя бы одно непрочитанное сообщение.

    fun getChats() = chatList
//        Получить список чатов (например, service.getChats).

    fun getLastMessagesFromChats(): MutableList<String> {
        //Получить список последних сообщений из чатов (можно в виде списка строк). Если сообщений в чате нет (все были удалены), то пишется «нет сообщений».
        var strTmpList = mutableListOf<String>()

        chatList.forEach {
            strTmpList.add(it.messages.last())
        }
        if (strTmpList.size == 0) return mutableListOf<String>("no messages")

        return strTmpList
    }

    fun getMessagesFromChat(idOwner: Int, numbersOfMessages: Int) : MutableList<String> {
        //Получить список сообщений из чата, указав: //ID собеседника; количество сообщений.
        // После того как вызвана эта функция, все отданные сообщения автоматически считаются прочитанными.
        var strTmpList = mutableListOf<String>()

        val chatListTmp = chatList.filter { chat: Chat -> chat.idOwner == idOwner }

        if (chatListTmp.size == 1) {
            return (chatListTmp[0].messages.filterIndexed { index, s ->  (chatListTmp[0].messages.size - numbersOfMessages - 1) < index}).toMutableList()
        } else {
            throw RuntimeException("<< fun getMessagesFromChat >> ERROR = no message to get")
        }

    }

    fun createMessage(idOwner: Int, message: String) {
        //Создать новое сообщение.

        val chatListTmp = chatList.filter { chat: Chat -> chat.idOwner == idOwner }

        if (chatListTmp.size == 1) {
            chatListTmp[0].messages.add(message)
        } else {
            createChat(idOwner, message)
        }
    }

    fun deleteMessage(idOwner: Int, message: String) {
        //Удалить сообщение.
        val chatListTmp = chatList.filter { chat: Chat -> chat.idOwner == idOwner }

        if (chatListTmp.size == 1) {
            chatListTmp[0].messages.remove(message)
        } else {
            throw RuntimeException("<< fun deleteMessage >> ERROR = no message to delete")
        }

    }

    fun createChat(idOwner: Int, message: String) {
        //Создать чат. Чат создаётся, когда пользователю отправляется первое сообщение.
        val mess = mutableListOf<String>()
        mess.add(message)

        chatList.add(Chat(idOwner = idOwner, messages = mess))
    }

    fun deleteChat(idOwner: Int) {
        //Удалить чат, т. е. целиком удалить всю переписку.
        chatList.removeIf(fun(chat: Chat) = (chat.idOwner == idOwner))
    }


}