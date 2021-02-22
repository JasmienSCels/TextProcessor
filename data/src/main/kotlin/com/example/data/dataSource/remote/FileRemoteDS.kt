package com.example.data.dataSource.remote

import android.content.Context
import android.util.Log
import com.example.data.dataSource.remote.apiService.BookApiService
import com.example.data.repository.FileRepositoryImpl
import io.reactivex.Single
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class FileRemoteDS @Inject constructor(
    private val context: Context,
    private val service: BookApiService
) :
    FileRepositoryImpl.RemoteDS<File?> {

    override fun fetch(title: String): Single<File?> {
        return service.loadBook(title)
            .map { toFile(title, it) }
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
            Log.d("AHHHH", e.toString());
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
}

