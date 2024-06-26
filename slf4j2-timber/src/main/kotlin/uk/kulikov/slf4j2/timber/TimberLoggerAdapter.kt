package uk.kulikov.slf4j2.timber

import org.slf4j.helpers.MarkerIgnoringBase
import org.slf4j.helpers.MessageFormatter
import timber.log.Timber

/**
 *
 * A simple implementation that delegates all log requests to the Timber
 * logging facilities. Note that this logger does not support [org.slf4j.Marker].
 * Methods taking marker data as parameter simply invoke the eponymous method
 * without the Marker argument, discarding any marker data in the process.
 *
 *
 * The logging levels specified for SLF4J can be almost directly mapped to
 * the logging method that exist in Timber. The following table
 * shows the mapping implemented by this logger.
 *
 * <table border="1">
 * <tr><th>**SLF4J******</th><th>**Timber**</th></tr>
 * <tr><td>TRACE</td><td>Timber.v(...)</td></tr>
 * <tr><td>DEBUG</td><td>Timber.d(...)</td></tr>
 * <tr><td>INFO</td><td>Timber.i(...)</td></tr>
 * <tr><td>WARN</td><td>Timber.w(...)</td></tr>
 * <tr><td>ERROR</td><td>Timber.e(...)</td></tr>
</table> *
 *
 *
 * Use loggers as usual:
 *
 *  *
 * Declare a logger<br></br>
 * `private static final Logger logger = LoggerFactory.getLogger(MyClass.class);`
 *
 *  *
 * Invoke logging methods, e.g.,<br></br>
 * `logger.debug("Some log message. Details: {}", someObject);`<br></br>
 * `logger.debug("Some log message with varargs. Details: {}, {}, {}", someObject1, someObject2, someObject3);`
 *
 *
 *
 *
 *
 * Logger instances created using the LoggerFactory are named either according to the name
 * or the fully qualified class name of the class given as a parameter.
 * Each logger name will be used as the tag for Timber if Timber has planted [timber.log.Timber.TaggedTree].
 * If tag contains also class package, it will be removed (same way like in [timber.log.Timber.DebugTree]).
 *
 *
 * @author Martin Sloup <arcao></arcao>@arcao.com>
 */
internal class TimberLoggerAdapter(tag: String?) : MarkerIgnoringBase() {
    private enum class LogType {
        TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    /**
     * Package access allows only [AndroidLoggerFactory] to instantiate
     * SimpleLogger instances.
     */
    init {
        this.name = tag
    }

    override fun isTraceEnabled(): Boolean {
        return true
    }

    override fun trace(msg: String) {
        log(LogType.TRACE, msg, null)
    }

    override fun trace(format: String, arg: Any) {
        formatAndLog(LogType.TRACE, format, arg)
    }

    override fun trace(format: String, arg1: Any, arg2: Any) {
        formatAndLog(LogType.TRACE, format, arg1, arg2)
    }

    override fun trace(format: String, vararg argArray: Any) {
        formatAndLog(LogType.TRACE, format, *argArray)
    }

    override fun trace(msg: String, t: Throwable) {
        log(LogType.TRACE, msg, t)
    }

    override fun isDebugEnabled(): Boolean {
        return true
    }

    override fun debug(msg: String) {
        log(LogType.DEBUG, msg, null)
    }

    override fun debug(format: String, arg: Any) {
        formatAndLog(LogType.DEBUG, format, arg)
    }

    override fun debug(format: String, arg1: Any, arg2: Any) {
        formatAndLog(LogType.DEBUG, format, arg1, arg2)
    }

    override fun debug(format: String, vararg argArray: Any) {
        formatAndLog(LogType.DEBUG, format, *argArray)
    }

    override fun debug(msg: String, t: Throwable) {
        log(LogType.DEBUG, msg, t)
    }

    override fun isInfoEnabled(): Boolean {
        return true
    }

    override fun info(msg: String) {
        log(LogType.INFO, msg, null)
    }

    override fun info(format: String, arg: Any) {
        formatAndLog(LogType.INFO, format, arg)
    }

    override fun info(format: String, arg1: Any, arg2: Any) {
        formatAndLog(LogType.INFO, format, arg1, arg2)
    }

    override fun info(format: String, vararg argArray: Any) {
        formatAndLog(LogType.INFO, format, *argArray)
    }

    override fun info(msg: String, t: Throwable) {
        log(LogType.INFO, msg, t)
    }

    override fun isWarnEnabled(): Boolean {
        return true
    }

    override fun warn(msg: String) {
        log(LogType.WARN, msg, null)
    }

    override fun warn(format: String, arg: Any) {
        formatAndLog(LogType.WARN, format, arg)
    }

    override fun warn(format: String, arg1: Any, arg2: Any) {
        formatAndLog(LogType.WARN, format, arg1, arg2)
    }

    override fun warn(format: String, vararg argArray: Any) {
        formatAndLog(LogType.WARN, format, *argArray)
    }

    override fun warn(msg: String, t: Throwable) {
        log(LogType.WARN, msg, t)
    }

    override fun isErrorEnabled(): Boolean {
        return true
    }

    override fun error(msg: String) {
        log(LogType.ERROR, msg, null)
    }

    override fun error(format: String, arg: Any) {
        formatAndLog(LogType.ERROR, format, arg)
    }

    override fun error(format: String, arg1: Any, arg2: Any) {
        formatAndLog(LogType.ERROR, format, arg1, arg2)
    }

    override fun error(format: String, vararg argArray: Any) {
        formatAndLog(LogType.ERROR, format, *argArray)
    }

    override fun error(msg: String, t: Throwable) {
        log(LogType.ERROR, msg, t)
    }

    private fun formatAndLog(logType: LogType, format: String, vararg argArray: Any) {
        val ft = MessageFormatter.arrayFormat(format, argArray)
        log(logType, ft.message, ft.throwable)
    }

    private fun log(logType: LogType, message: String, throwable: Throwable?) {
        val tree: Timber.Tree = Timber.tag(name)

        when (logType) {
            LogType.TRACE -> if (throwable != null) {
                tree.v(throwable, message)
            } else {
                tree.v(message)
            }

            LogType.DEBUG -> if (throwable != null) {
                tree.d(throwable, message)
            } else {
                tree.d(message)
            }

            LogType.INFO -> if (throwable != null) {
                tree.i(throwable, message)
            } else {
                tree.i(message)
            }

            LogType.WARN -> if (throwable != null) {
                tree.w(throwable, message)
            } else {
                tree.w(message)
            }

            LogType.ERROR -> if (throwable != null) {
                tree.e(throwable, message)
            } else {
                tree.e(message)
            }

            else -> if (throwable != null) {
                tree.i(throwable, message)
            } else {
                tree.i(message)
            }
        }
    }

    companion object {
        private const val serialVersionUID = -1227274521521287937L
    }
}