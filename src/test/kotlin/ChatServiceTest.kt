package ru.netology

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

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
        assertEquals(2,ChatService.getUnreadChatsCount())
    }

    @Test
    fun getChats() {
        println("<<getChats()>>\n"+ChatService.getChats())
        println()
    }

    @Test
    fun getLastMessagesFromChats() {
        println("<<getLastMessagesFromChats()>>\n"+ChatService.getLastMessagesFromChats())
        println()
    }

    @Test
    fun getMessagesFromChat() {
        println("<<getMessagesFromChat(22,2)>>\n"+ChatService.getMessagesFromChat(22,2))
        println()
    }

    @Test(expected = RuntimeException::class)
    fun getMessagesFromChatRtE() {
        println("<<getMessagesFromChat(33,2)>>\n"+ChatService.getMessagesFromChat(33,2))
        println()
    }

    @Test
    fun deleteMessage() {
        println("<<deleteMessage(11, this is message from owner #11 number 2) - BEFORE>>\n"+ChatService.getChats())
        ChatService.deleteMessage(11, "this is message from owner #11 number 2")
        println("<<deleteMessage(11, this is message from owner #11 number 2) - AFTER>>\n"+ChatService.getChats())
        println()
    }

    @Test(expected = RuntimeException::class)
    fun deleteMessageRtE() {
        ChatService.deleteMessage(11, "this is message from owner #11 number 2xxx")
    }


    @Test
    fun deleteChat() {
        ChatService.deleteChat(22)
        println("<<deleteChat(22)>>\n"+ChatService.getChats())
        println()
    }

    @Test(expected = RuntimeException::class)
    fun deleteChatRtE() {
        ChatService.deleteChat(33)
    }
}