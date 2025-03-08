import junit.framework.TestCase.*
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
}