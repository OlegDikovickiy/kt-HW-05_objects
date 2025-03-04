data class Comments(
    val count: Int = 0, // Количество комментариев
    val canPost: Boolean = true, // Может ли текущий пользователь оставить комментарий
    val groupsCanPost: Boolean = false, // Могут ли сообщества оставлять комментарии
    val canClose: Boolean = false, // Может ли текущий пользователь закрыть комментарии
    val canOpen: Boolean = false // Может ли текущий пользователь открыть комментарии
)
