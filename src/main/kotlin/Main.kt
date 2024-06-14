package ru.netology


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    ChatService.createMessage(11, "this is message from owner #11 number 1")
    ChatService.createMessage(22, "this is message from owner #22 number 1")
    ChatService.createMessage(22, "this is message from owner #22 number 2")
    ChatService.createMessage(22, "this is message from owner #22 number 3")
    ChatService.createMessage(11, "this is message from owner #11 number 2")

    println(ChatService.getMessagesFromChat(22,2))

    println(ChatService.getLastMessagesFromChats())

    println(ChatService.getChats())

    println(ChatService.getUnreadChatsCount())

    ChatService.deleteChat(22)
    println(ChatService.getChats())

    ChatService.deleteMessage(11, "this is message from owner #11 number 2")
    println(ChatService.getChats())
}