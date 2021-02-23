package com.example.domain.usecase

import android.content.Context
import android.util.Log
import com.example.domain.common.errorHandling.isConnectionError
import com.example.domain.common.extensions.isPrime
import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.FileRepository
import com.example.domain.repository.WordRepository
import com.example.domain.usecase.base.ObservableUseCase
import com.example.domain.usecase.base.SingleUseCase
import io.reactivex.Flowable.just
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
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
) : ObservableUseCase<LoadBookUseCase.Result, String>(scheduler.io, scheduler.main) {


    override fun buildUseCaseObservable(title: String?): Observable<Result> {

        var list = mutableListOf<WordFrequencyDM>()
        return wordRepository.getWords()
            .doOnNext { list.add(it) }
            .map<Result> {
                Result.Success(list.toSet())
            }
            .onErrorReturn {
                Log.d("AHHHHH", it.message)
                getErrorResult(it)
            }
    }


//        return Observable.create { emitter ->
//            try {
//                fileRepository.getFile(title!!)
//                    .map { rb ->
//                        val file = toFile(title, rb)
//                        val text = file!!.readText().toLowerCase(Locale.ROOT)
//                        val r = Regex("""\p{javaLowerCase}+""")
//                        val matches = r.findAll(text!!)
//                        val wordGroups = matches.map { it.value }
//                            .groupBy { it }
//                            .map { Pair(it.key, it.value.size) }
//                        for ((word, freq) in wordGroups) {
//                            val wordFrequencyDM =
//                                WordFrequencyDM(
//                                    word = word,
//                                    frequency = freq,
//                                    isPrime = freq.isPrime()
//                                )
//                            emitter.onNext(Result.Success(wordFrequencyDM))
//
//                            wordRepository.saveWord(wordFrequencyDM)
//                        }
//                        emitter.onComplete()
//                    }
//                  //  .onErrorReturn(::getErrorResult)
//            } catch (e: Exception) {
//                Log.d(TAG, e.localizedMessage)
//                emitter.onError(e)
//            }
//        }
//    }
//
//    private fun toFile(title: String, body: ResponseBody?): File? {
//        if (body == null) return null
//        var byteStream: InputStream? = null;
//        var buffer: FileOutputStream? = null;
//
//        try {
//            byteStream = body.byteStream();
//            buffer = FileOutputStream(context.filesDir.toString() + "/" + title);
//            var size: Int
//            while (byteStream.read().also { size = it } != -1) {
//                buffer.write(size)
//            }
//            return File(context.filesDir.toString() + "/" + title)
//        } catch (e: IOException) {
//            return null;
//        } finally {
//            if (byteStream != null) {
//                byteStream.close();
//            }
//            if (buffer != null) {
//                buffer.close();
//            }
//        }
 //           }

 //   }


    private fun getErrorResult(throwable: Throwable): Result = when {
        throwable.isConnectionError() -> Result.ErrorConnection
        else -> Result.ErrorUnknown(throwable)
    }

    sealed class Result {
        data class Success(val wordFrequencyDM: Set<WordFrequencyDM>) : Result()
        object ErrorConnection : Result()
        data class ErrorUnknown(val throwable: Throwable) : Result()
    }

    private companion object {
        val TAG = LoadBookUseCase::class.java.simpleName
    }

}