object WallService {
    private var posts = mutableListOf<Post>() // Список для хранения постов
    private var nextId = 1 // Счетчик для генерации уникальных ID

    private var comments = emptyArray<Comment>()
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

    //Очищает список постов и сбрасывает счетчик ID.
    fun clear() {
        posts.clear()
        nextId = 1
        comments = emptyArray()
        nextCommentId = 1
    }

    //Возвращает список всех постов.
    fun getPosts(): List<Post> {
        return posts.toList()
    }
}