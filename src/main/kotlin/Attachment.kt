sealed class Attachment(val type: String)

data class Photo(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String
)

class PhotoAttachment(
    val photo: Photo
) : Attachment("photo") // Указываем тип вложения

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int
)

class VideoAttachment(
    val video: Video
) : Attachment("video")

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String
)

class AudioAttachment(
    val audio: Audio
) : Attachment("audio")

data class Doc(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int
)

class DocAttachment(
    val doc: Doc
) : Attachment("doc")

data class Link(
    val url: String,
    val title: String,
    val description: String
)

class LinkAttachment(
    val link: Link
) : Attachment("link")