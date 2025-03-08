data class Comment(
    val id: Int,
    val fromId: Int, // Автор комментария
    val date: Int,
    val text: String,
    val postId: Int // id поста, к которому относится коммент
)
