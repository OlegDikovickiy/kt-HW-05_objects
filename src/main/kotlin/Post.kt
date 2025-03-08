data class Post(
    val id: Int,
    val ownerId: Int, //id владельца стены
    val fromId: Int, //id автора записи
    val date: Int, //Время публикации записи в формате unixtime
    val text: String, //Текст записи
    val friendsOnly: Boolean = false, //Запись доступна всем
    val comments: Comments = Comments(), //Комментарии
    val likes: Likes = Likes(), //Лайки
    val views: Views = Views(), //Просмотры
    val postType: String = "post", //Тип записи (post, copy, reply, postpone, suggest)
    val canPin: Boolean = true, //Разрешено закрепить
    val replyOwnerId: Int? = null, // id владельца записи, в ответ на которую оставлена текущая (если есть)
    val replyPostId: Int? = null, // id записи, в ответ на которую оставлена текущая (если есть)
    val attachments: Array<Attachment> = emptyArray() // Массив вложений
)
