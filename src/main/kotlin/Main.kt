fun main() {
    // Создаем новый пост
    val post = Post(
        id = 0, // ID будет автоматически присвоен WallService
        ownerId = 123,
        fromId = 456,
        date = System.currentTimeMillis().toInt(),
        text = "Это мой первый пост!"
    )

    // Добавляем пост в WallService
    val addedPost = WallService.add(post)
    println("Добавлен пост: $addedPost")

    // Обновляем пост
    val updatedPost = addedPost.copy(text = "Обновленный текст поста")
    val isUpdated = WallService.update(updatedPost)
    println("Пост обновлен: $isUpdated")

    // Получаем все посты
    val allPosts = WallService.getPosts()
    println("Все посты: $allPosts")

    // Очищаем WallService
    WallService.clear()
    println("Все посты после очистки: ${WallService.getPosts()}")
}