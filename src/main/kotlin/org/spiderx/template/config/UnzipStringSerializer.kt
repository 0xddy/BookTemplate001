package org.spiderx.template.config

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase
import org.spiderx.template.extensions.unzip

class UnzipStringSerializer : ToStringSerializerBase {

    constructor() : super(Any::class.java)
    constructor(handledType: Class<*>?) : super(handledType)

    val instance = ToStringSerializer()

    override fun valueToString(value: Any): String {
        if (value is ByteArray) {
            return value.unzip()
        }
        return value.toString()
    }
}