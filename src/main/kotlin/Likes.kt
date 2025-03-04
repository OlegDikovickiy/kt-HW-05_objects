data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false, // Поставил ли текущий пользователь лайк
    val canLike: Boolean = true, // Может ли текущий пользователь поставить лайк
    val canPublish: Boolean = true // Может ли текущий пользователь сделать репост записи
)
