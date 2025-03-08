import junit.framework.TestCase.*
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class WallServiceTest {

    // Очищаем WallService перед каждым тестом
    @Before
    fun clearService() {
        WallService.clear()
    }

    @Test
    fun addPost_IdShouldNotBeZero() {
        val service = WallService
        val post = Post(
            id = 0,
            ownerId = 123,
            fromId = 456,
            date = 1234567890,
            text = "Тестовый пост",
            replyOwnerId = null,
            replyPostId = null
        )

        val addedPost = service.add(post)
        assertNotNull(addedPost)
        assertNull(addedPost.replyOwnerId)
        assertNull(addedPost.replyPostId)
        assertNotEquals(0, addedPost.id, "ID поста должен быть отличным от 0 после добавления")
    }

    @Test
    fun updatePost_WithExistingId_ShouldReturnTrue() {
        val service = WallService
        val post = Post(
            id = 0,
            ownerId = 123,
            fromId = 456,
            date = 1234567890,
            text = "Тестовый пост"
        )
        val addedPost = service.add(post)

        val updatedPost = addedPost.copy(text = "Обновленный текст")
        val isUpdated = service.update(updatedPost)

        assertTrue(isUpdated, "Обновление поста с существующим ID должно вернуть true")
    }

    @Test
    fun updatePost_WithNonExistingId_ShouldReturnFalse() {
        val service = WallService

        val nonExistingPost = Post(
            id = 999,
            ownerId = 123,
            fromId = 456,
            date = 1234567890,
            text = "Несуществующий пост"
        )

        val isUpdated = service.update(nonExistingPost)

        assertFalse(isUpdated, "Обновление поста с несуществующим ID должно вернуть false")
    }

    @Test
    fun createComment_WithExistingPost_ShouldReturnComment() {
        val service = WallService

        // Создаем пост
        val post = Post(
            id = 1,
            ownerId = 123,
            fromId = 456,
            date = 1234567890,
            text = "Тестовый пост"
        )
        service.add(post)

        // Создаем комментарий
        val comment = Comment(
            id = 0, // ID будет присвоен автоматически
            fromId = 789,
            date = 1234567891,
            text = "Это комментарий",
            postId = 1 // Указываем ID поста, к которому относится комментарий
        )

        // Добавляем комментарий
        val addedComment = service.createComment(postId = 1, comment = comment)

        // Проверяем, что комментарий добавлен
        assertEquals(1, addedComment.id) // ID комментария должен быть равен 1
        assertEquals("Это комментарий", addedComment.text) // Текст комментария должен совпадать
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val service = WallService

        // Создаем комментарий для несуществующего поста
        val comment = Comment(
            id = 0,
            fromId = 789,
            date = 1234567891,
            text = "Несуществующий комментарий",
            postId = 999 // Несуществующий ID поста
        )

        // Пытаемся добавить комментарий (должно выкинуть исключение)
        service.createComment(postId = 999, comment = comment)
    }

    @Test
    fun reportComment_WithValidData_ShouldAddReport() {
        val service = WallService

        val post = Post(
            id = 1,
            ownerId = 123,
            fromId = 456,
            date = 1234567890,
            text = "Тестовый пост"
        )
        service.add(post)

        val comment = Comment(
            id = 0, // ID будет присвоен автоматически
            fromId = 789,
            date = 1234567891,
            text = "Это комментарий",
            postId = 1
        )

        service.createComment(postId = 1, comment = comment)
        // Добавляем репорт
        service.reportComment(commentId = 1, reason = "Спам")
        // Проверяем, что репорт добавлен
        assertEquals(1, service.getCommentReports().size)
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportComment_WithNonExistingComment_ShouldThrowException() {
        // Используем объект WallService напрямую
        val service = WallService

        // Пытаемся добавить репорт к несуществующему комментарию
        service.reportComment(commentId = 999, reason = "Спам")
    }

    @Test(expected = InvalidReasonException::class)
    fun reportComment_WithEmptyReason_ShouldThrowException() {
        // Используем объект WallService напрямую
        val service = WallService

        // Создаем пост и комментарий
        val post = Post(
            id = 1,
            ownerId = 123,
            fromId = 456,
            date = 1234567890,
            text = "Тестовый пост"
        )
        service.add(post)

        val comment = Comment(
            id = 0,
            fromId = 789,
            date = 1234567891,
            text = "Это комментарий",
            postId = 1
        )
        service.createComment(postId = 1, comment = comment)

        // Пытаемся добавить репорт с пустой причиной
        service.reportComment(commentId = 1, reason = "")
    }


}