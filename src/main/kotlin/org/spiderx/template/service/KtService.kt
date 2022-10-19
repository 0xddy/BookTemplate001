package org.spiderx.template.service

import org.spiderx.template.extensions.unzip

open class KtService {

    companion object {

        fun <T> toList(vararg elements: T): List<T> {
            return listOf(*elements)
        }

        fun unzip(array: ByteArray):String {
            return array.unzip()
        }
    }

}