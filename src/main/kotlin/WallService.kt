object WallService {
    private var posts = mutableListOf<Post>() // Список для хранения постов
    private var nextId = 1 // Счетчик для генерации уникальных ID

    private var comments = emptyArray<Comment>()
    private var commentReports = emptyArray<CommentReport>() // Массив для хранения репортов
    private var nextCommentId = 1 // Счетчик для генерации уникальных ID комментариев


    //Добавляет новый пост в список.
    fun add(post: Post): Post {
        val newPost = post.copy(id = nextId++) // Присваиваем уникальный ID
        posts.add(newPost)
        return newPost
    }

    //Обновляет существующий пост в списке.
    fun update(post: Post): Boolean {
        for ((i, existingPost) in posts.withIndex()) {
            if (existingPost.id == post.id) {
                posts[i] = post
                return true
            }
        }
        return false
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        var postFound = false
        for (post in posts) {
            if (post.id == postId) {
                postFound = true
                break
            }
        }
        if (!postFound) {
            throw PostNotFoundException("Пост с ID $postId не найден")
        }
        val newComment = comment.copy(id = nextCommentId++)
        comments += newComment
        return newComment
    }

    fun reportComment(commentId: Int, reason: String) {
        // Проверяем, существует ли комментарий
        var commentFound = false
        for (comment in comments) {
            if (comment.id == commentId) {
                commentFound = true
                break
            }
        }
        // Если комментарий не найден, выбрасываем исключение
        if (!commentFound) {
            throw CommentNotFoundException("Комментарий с ID $commentId не найден")
        }
        // Проверяем, что причина не пустая
        if (reason.isBlank()) {
            throw InvalidReasonException("Некорректная причина: $reason")
        }

        // Добавляем репорт в массив
        commentReports += CommentReport(commentId, reason)
    }

    //Очищает список постов и сбрасывает счетчик ID.
    fun clear() {
        posts.clear()
        nextId = 1
        comments = emptyArray()
        commentReports = emptyArray()
        nextCommentId = 1

    }

    fun getCommentReports(): Array<CommentReport> {
        return commentReports
    }

    //Возвращает список всех постов.
    fun getPosts(): List<Post> {
        return posts.toList()
    }

}