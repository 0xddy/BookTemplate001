package org.spiderx.template.extensions

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

val UTF_8: Charset = Charset.forName("utf-8")

fun String.gzip(): ByteArray {
    var retByteArray = ByteArray(0)
    val byteArrayOut = ByteArrayOutputStream()
    runCatching {
        val gzip = GZIPOutputStream(byteArrayOut)
        gzip.write(toByteArray(UTF_8))
        gzip.close()
        retByteArray = byteArrayOut.toByteArray()
        byteArrayOut.close()
    }.onFailure {
        it.printStackTrace()
    }
    return retByteArray
}

fun ByteArray.unzip(): String {
    var retStr = ""
    val byteArrayOutputStream = ByteArrayOutputStream()
    val byteArrayInputStream = ByteArrayInputStream(this)

    runCatching {
        val gzipInputStream = GZIPInputStream(byteArrayInputStream)
        val buffer = ByteArray(256)
        var len: Int
        while ((gzipInputStream.read(buffer).apply {
                len = this
            }) >= 0) {
            byteArrayOutputStream.write(buffer, 0, len);
        }

        retStr = byteArrayOutputStream.toString("utf-8")

        gzipInputStream.close()
        byteArrayInputStream.close()
        byteArrayOutputStream.close()

    }.onFailure {
        it.printStackTrace()
    }

    return retStr
}

fun String.md5Encode(): String {

    var reStr = ""
    try {
        val md5: MessageDigest = MessageDigest.getInstance("MD5")
        val bytes: ByteArray = md5.digest(this.toByteArray())
        val stringBuffer: StringBuilder = StringBuilder()
        for (b in bytes) {
            val bt: Int = b.toInt() and 0xff
            if (bt < 16) {
                stringBuffer.append(0)
            }
            stringBuffer.append(Integer.toHexString(bt))
        }
        reStr = stringBuffer.toString()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }

    return reStr
}

fun String.hexEncode(): Int {
    var rc = 0
    for (i in indices) {
        rc += codePointAt(i).toString(10).toInt()
    }
    return rc
}