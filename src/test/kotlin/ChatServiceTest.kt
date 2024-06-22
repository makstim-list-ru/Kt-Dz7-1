package ru.netology

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.netology.ChatService.chatList

class ChatServiceTest {

    @Before
    fun createMessage() {
        ChatService.clear()
        ChatService.createMessage(11, "this is message from owner #11 number 1")
        ChatService.createMessage(22, "this is message from owner #22 number 1")
        ChatService.createMessage(22, "this is message from owner #22 number 2")
        ChatService.createMessage(22, "this is message from owner #22 number 3")
        ChatService.createMessage(11, "this is message from owner #11 number 2")
    }

    @Test
    fun getUnreadChatsCount() {
//        println("<<getUnreadChatsCount()>>\n"+ChatService.getUnreadChatsCount())
//        println()
        assertEquals(2, ChatService.getUnreadChatsCount())
    }

    @Test
    fun getChats() {
        assertEquals(2, ChatService.getChats().size)
//        println("<<getChats()>>\n" + ChatService.getChats())
//        println()
    }

    @Test
    fun getLastMessagesFromChats() {
        assertEquals(2, ChatService.getLastMessagesFromChats().size)
//        println("<<getLastMessagesFromChats()>>\n" + ChatService.getLastMessagesFromChats())
//        println()
    }

    @Test
    fun getMessagesFromChat() {

        val tmpStringList = ChatService.getMessagesFromChat(22, 2)

        assertEquals("this is message from owner #22 number 2", tmpStringList[0])
        assertEquals("this is message from owner #22 number 3", tmpStringList[1])

//        println("<<getMessagesFromChat(22,2)>>\n" + ChatService.getMessagesFromChat(22, 2))
//        println()
    }

    @Test(expected = RuntimeException::class)
    fun getMessagesFromChatRtE() {
        println("<<getMessagesFromChat(33,2)>>\n" + ChatService.getMessagesFromChat(33, 2))
        println()
    }

    @Test
    fun deleteMessage() {

        val chatListBefore =
            chatList.filter { chat: Chat -> chat.messages.contains("this is message from owner #11 number 2") }
//        println("<<deleteMessage(11, this is message from owner #11 number 2) - BEFORE>>\n"+ChatService.getChats())
        ChatService.deleteMessage(11, "this is message from owner #11 number 2")
//        println("<<deleteMessage(11, this is message from owner #11 number 2) - AFTER>>\n"+ChatService.getChats())
//        println()
        val chatListAfter =
            chatList.filter { chat: Chat -> chat.messages.contains("this is message from owner #11 number 2") }

        assertEquals(1, chatListBefore.size)
        assertEquals(0, chatListAfter.size)
    }

    @Test(expected = RuntimeException::class)
    fun deleteMessageRtE() {
        ChatService.deleteMessage(11, "this is message from owner #11 number 2xxx")
    }


    @Test
    fun deleteChat() {
        val chatListBefore =
            chatList.filter { chat: Chat -> chat.idOwner == 22 }
        ChatService.deleteChat(22)
        val chatListAfter =
            chatList.filter { chat: Chat -> chat.idOwner == 22 }
        assertEquals(1, chatListBefore.size)
        assertEquals(0, chatListAfter.size)
//        println("<<deleteChat(22)>>\n" + ChatService.getChats())
//        println()
    }

    @Test(expected = RuntimeException::class)
    fun deleteChatRtE() {
        ChatService.deleteChat(33)
    }
}