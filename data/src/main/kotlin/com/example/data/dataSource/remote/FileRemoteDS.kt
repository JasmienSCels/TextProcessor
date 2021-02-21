package com.example.data.dataSource.remote

import android.util.Log
import com.example.data.dataSource.remote.apiService.BookApiService
import com.example.data.repository.FileRepositoryImpl
import io.reactivex.Single
import java.io.*
import java.net.URI
import java.net.URL
import javax.inject.Inject

class FileRemoteDS @Inject constructor(private val service: BookApiService) :
    FileRepositoryImpl.RemoteDS<File> {

    override fun fetch(url: URL): Single<File> {
        Log.d("AHHH", "Fetch")
        val result = service.loadBook()
            .map {
                try {
                    val imageFile = File("Railway-Children-by-E-Nesbit.txt")
                    var inputStream: InputStream? = null
                    var outputStream: OutputStream? = null
                    try {
                        val response = it.byteStream()


                        val fileReader = ByteArray(4096)
                        val fileSize: Long = it.contentLength()
                        var fileSizeDownloaded: Long = 0
                        inputStream = it.byteStream()
                        outputStream = FileOutputStream(File(, "tours"));

                        while (true) {
                            val read = inputStream.read(fileReader)
                            if (read == -1) {
                                break
                            }
                            outputStream.write(fileReader, 0, read)
                            fileSizeDownloaded += read.toLong()
                            Log.d(
                                "AHHHH",
                                "writeResponseBodyToDisk: file download: $fileSizeDownloaded of $fileSize"
                            )
                        }
                        outputStream.flush()
                    } catch (e: IOException) {
                        Log.d(
                            "AHHHHERRR",
                            e.localizedMessage
                        )
                        Unit
                    } finally {
                        inputStream?.close()
                        outputStream?.close()
                    }
                } catch (e: IOException) {
                    Log.d(
                        "AHHHHERRR",
                        e.localizedMessage
                    )
                    Unit
                }
            }
        Log.d("AHHHHHH result", result.blockingGet().toString())
        return Single.just(File("test"))
    }
}

