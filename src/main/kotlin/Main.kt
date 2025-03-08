fun main() {

    val photo = Photo(1, 123, "https://vk.com/photo130", "https://vk.com/photo604")
    val video = Video(1, 123, "Funny Cat Video", 30)
    val audio = Audio(1, 123, "Artist", "Song Title")
    val doc = Doc(1, 123, "Document.pdf", 1024)
    val link = Link("https://example.com", "Example", "This is an example link")

    // Создаем новый пост
    val post = Post(
        id = 0,
        ownerId = 123,
        fromId = 456,
        date = 1234567890,
        text = "Пост с вложениями",
        attachments = arrayOf(
            PhotoAttachment(photo),
            VideoAttachment(video),
            AudioAttachment(audio),
            DocAttachment(doc),
            LinkAttachment(link)
        )
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