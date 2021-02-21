package com.example.data.dataSource.remote

import android.net.Uri
import com.example.data.repository.FileRepositoryImpl
import java.io.File
import java.net.URI
import java.util.concurrent.Callable
import javax.inject.Inject

class FileRemoteDS @Inject constructor() : FileRepositoryImpl.RemoteDS<File> {

    override fun fetch(uri: URI): File=
       File(uri.path)

}