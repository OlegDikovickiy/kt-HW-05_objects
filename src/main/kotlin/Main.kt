import kotlin.coroutines.coroutineContext

fun main() {


    // Создаем новый пост
    val post = Post(
        id = 1,
        ownerId = 123,
        fromId = 456,
        date = 1234567890,
        text = "Пост с вложениями",
    )

    // Добавляем пост в WallService
    val addedPost = WallService.add(post)
    println("Добавлен пост: $addedPost")

    // Создаем комментарий
    val comment = Comment(
        id = 0,
        fromId = 789,
        date = 1234567891,
        text = "Это комментарий",
        postId = 1 // Указываем ID поста, к которому относится комментарий
    )

    // Добавляем комментарий
    val addedComment = WallService.createComment(1, comment)
    val reportComment = WallService.reportComment(1,"Спам")

    try {
        WallService.reportComment(commentId = 999, reason = "Спам")
    } catch (e: CommentNotFoundException) {
        println(e.message) // Комментарий с ID 999 не найден
    }

    // Пытаемся добавить репорт с пустой причиной
    try {
        WallService.reportComment(commentId = 1, reason = "")
    } catch (e: InvalidReasonException) {
        println(e.message) // Некорректная причина:
    }

}


fun processAttachment(attachment: Attachment) {
    when (attachment) {
        is PhotoAttachment -> println("Это фото: ${attachment.photo.photo130}")
        is VideoAttachment -> println("Это видео: ${attachment.video.title}")
        is AudioAttachment -> println("Это аудио: ${attachment.audio.title}")
        is DocAttachment -> println("Это документ: ${attachment.doc.title}")
        is LinkAttachment -> println("Это ссылка: ${attachment.link.url}")
    }
}