package com.example.domain.usecase

import android.content.Context
import android.util.Log
import com.example.domain.common.extensions.isPrime
import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.FileRepository
import com.example.domain.repository.WordRepository
import com.example.domain.usecase.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.internal.operators.observable.ObservableSwitchIfEmpty
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class LoadBookUseCase @Inject constructor(
    private val context: Context,
    private val fileRepository: FileRepository<Single<ResponseBody>>,
    private val wordRepository: WordRepository<WordFrequencyDM>,
    scheduler: SchedulerProvider
) : ObservableUseCase<WordFrequencyDM?, String>(scheduler.io, scheduler.main) {


    override fun buildUseCaseObservable(title: String?): Observable<WordFrequencyDM?> {
        return ObservableSwitchIfEmpty(wordRepository.getWords(), wordRepository.getWords())
    }

    /*

     */
    private fun getRemote(title: String?): Observable<WordFrequencyDM> {
        Log.d(TAG, "getRemote")
        return Observable.create { emitter ->
            try {
                fileRepository.getFile(title!!)
                    .map pairs@{ rb ->
                        val file = toFile(title, rb)
                        val text = file!!.readText().toLowerCase(Locale.ROOT)
                        val r = Regex("""\p{javaLowerCase}+""")
                        val matches = r.findAll(text)
                        return@pairs matches.map {
                            matches.map { it.value }
                                .groupBy { it }
                                .map { it.key to it.value.size }
                                .map { (title, freq) ->
                                    val word =
                                        WordFrequencyDM(
                                            word = title,
                                            frequency = freq,
                                            isPrime = freq.isPrime()
                                        )
                                    wordRepository.saveWord(word)
                                    emitter.onNext(word)
                                }
                        }
                    }
            } catch (e: Exception) {
                Log.d(TAG, "Error: " + e.localizedMessage)
                emitter.onError(e)
            }
            emitter.onComplete()
        }

    }

    private fun toFile(title: String, body: ResponseBody?): File? {
        if (body == null) return null
        var byteStream: InputStream? = null;
        var buffer: FileOutputStream? = null;

        try {
            byteStream = body.byteStream();
            buffer = FileOutputStream(context.filesDir.toString() + "/" + title);
            var size: Int
            while (byteStream.read().also { size = it } != -1) {
                buffer.write(size)
            }
            return File(context.filesDir.toString() + "/" + title)
        } catch (e: IOException) {
            return null;
        } finally {
            if (byteStream != null) {
                byteStream.close();
            }
            if (buffer != null) {
                buffer.close();
            }
        }
    }

    private companion object {
        val TAG = LoadBookUseCase::class.java.simpleName
    }

}