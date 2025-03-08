data class Post(
    val id: Int,
    val ownerId: Int, //id владельца стены
    val fromId: Int, //id автора записи
    val date: Int, //Время публикации записи в формате unixtime
    val text: String, //Текст записи
    val friendsOnly: Boolean = false, //Запись доступна всем
    val commentsPost: CommentsPost = CommentsPost(), //Комментарии
    val likes: Likes = Likes(), //Лайки
    val views: Views = Views(), //Просмотры
    val postType: String = "post", //Тип записи (post, copy, reply, postpone, suggest)
    val canPin: Boolean = true, //Разрешено закрепить
    val replyOwnerId: Int? = null, // id владельца записи, в ответ на которую оставлена текущая (если есть)
    val replyPostId: Int? = null, // id записи, в ответ на которую оставлена текущая (если есть)
    val attachments: Array<Attachment> = emptyArray() // Массив вложений
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false, // Поставил ли текущий пользователь лайк
    val canLike: Boolean = true, // Может ли текущий пользователь поставить лайк
    val canPublish: Boolean = true // Может ли текущий пользователь сделать репост записи
)

data class Comment(
    val id: Int,
    val fromId: Int, // Автор комментария
    val date: Int,
    val text: String,
    val postId: Int // id поста, к которому относится коммент
)

data class CommentsPost(
    val count: Int = 0, // Количество комментариев
    val canPost: Boolean = true, // Может ли текущий пользователь оставить комментарий
    val groupsCanPost: Boolean = false, // Могут ли сообщества оставлять комментарии
    val canClose: Boolean = false, // Может ли текущий пользователь закрыть комментарии
    val canOpen: Boolean = false // Может ли текущий пользователь открыть комментарии
)

data class Views(
    val count: Int = 0
)

data class CommentReport(
    val commentId: Int,
    val reason: String
)