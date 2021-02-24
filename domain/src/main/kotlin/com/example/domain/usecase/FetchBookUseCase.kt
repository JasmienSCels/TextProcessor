package com.example.domain.usecase

import android.content.Context
import com.example.domain.common.extensions.isPrime
import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.FileRepository
import com.example.domain.repository.WordRepository
import com.example.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*
import javax.inject.Inject
/*
    This could be improved as a ObservableUseCase, where the DMs get emitted as they get saved in the db.
    This would allow for better UX.
 */
class FetchBookUseCase @Inject constructor(
    private val context: Context,
    private val fileRepository: FileRepository<Single<ResponseBody>>,
    private val wordRepository: WordRepository<WordFrequencyDM>,
    val scheduler: SchedulerProvider
) : SingleUseCase<Set<WordFrequencyDM>, String>(scheduler.io, scheduler.main) {

    override fun buildUseCaseSingle(title: String?): Single<Set<WordFrequencyDM>> {

        return fileRepository.getFile(title!!)
            .map { rb ->
                val file = toFile(title, rb)
                val text = file!!.readText().toLowerCase(Locale.ROOT)
                val r = Regex("""\p{javaLowerCase}+""")
                val matches = r.findAll(text!!)
                val wordGroups = matches.map { it.value }
                    .groupBy { it }
                    .map { Pair(it.key, it.value.size) }
                val wordList = mutableListOf<WordFrequencyDM>()
                for ((word, freq) in wordGroups) {
                    val wordFrequencyDM =
                        WordFrequencyDM(word = word, frequency = freq, isPrime = freq.isPrime())
                    wordRepository.saveWord(wordFrequencyDM)
                    wordList.add(wordFrequencyDM)
                }
                wordList.toSet()
            }
//            .onErrorReturn {
//             //   getErrorResult(it)
//            }
    }

    private fun toFile(title: String, body: ResponseBody?): File? {
        if (body == null) return null
        var byteStream: InputStream? = null;
        var buffer: FileOutputStream? = null;

        return try {
            byteStream = body.byteStream();
            buffer = FileOutputStream(context.filesDir.toString() + "/" + title);
            var size: Int
            while (byteStream.read().also { size = it } != -1) {
                buffer.write(size)
            }
            File(context.filesDir.toString() + "/" + title)
        } catch (e: IOException) {
            null;
        } finally {
            byteStream?.close()
            buffer?.close()
        }
    }

}