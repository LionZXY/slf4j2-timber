package uk.kulikov.slf4j2.timber

import org.slf4j.ILoggerFactory
import org.slf4j.Logger
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.regex.Pattern

/**
 * TimberLoggerFactory is an implementation of [ILoggerFactory] returning
 * the appropriately named [org.slf4j.LoggerFactory] instance.
 *
 * @author Martin Sloup <arcao></arcao>@arcao.com>
 */
class TimberLoggerFactory : ILoggerFactory {
    private val loggerMap: ConcurrentMap<String, Logger> = ConcurrentHashMap()

    /**
     * Return an appropriate [TimberLoggerAdapter] instance by name.
     */
    override fun getLogger(name: String): Logger {
        val tag = createTag(name)
        var logger = loggerMap[tag]
        if (logger == null) {
            val newInstance: Logger = TimberLoggerAdapter(tag)
            val oldInstance = loggerMap.putIfAbsent(tag, newInstance)
            logger = oldInstance ?: newInstance
        }
        return logger
    }

    companion object {
        private val ANONYMOUS_CLASS: Pattern = Pattern.compile("\\$\\d+$")

        private fun createTag(name: String): String {
            var tag = name

            val m = ANONYMOUS_CLASS.matcher(tag)
            if (m.find()) {
                tag = m.replaceAll("")
            }
            return tag.substring(tag.lastIndexOf('.') + 1)
        }
    }
}