package uk.kulikov.slf4j2.timber

import org.slf4j.ILoggerFactory
import org.slf4j.IMarkerFactory
import org.slf4j.helpers.BasicMarkerFactory
import org.slf4j.helpers.NOPMDCAdapter
import org.slf4j.spi.MDCAdapter
import org.slf4j.spi.SLF4JServiceProvider

/**
 * The binding of [org.slf4j.LoggerFactory] class with an actual instance of
 * [ILoggerFactory] is performed using information returned by this class.
 *
 * @author Martin Sloup <arcao></arcao>@arcao.com>
 */
class TimberLoggerServiceProvider : SLF4JServiceProvider {
    private val loggerFactory: ILoggerFactory = TimberLoggerFactory()
    private val markerFactory: IMarkerFactory = BasicMarkerFactory()
    private val mdcAdapter: MDCAdapter = NOPMDCAdapter()

    override fun getLoggerFactory(): ILoggerFactory {
        return loggerFactory
    }

    override fun getMarkerFactory(): IMarkerFactory {
        return markerFactory
    }

    override fun getMDCAdapter(): MDCAdapter {
        return mdcAdapter
    }

    override fun getRequestedApiVersion(): String {
        return REQUESTED_API_VERSION
    }

    override fun initialize() {
    }

    companion object {
        /**
         * Declare the version of the SLF4J API this implementation is compiled against.
         * The value of this field is modified with each major release.
         */
        var REQUESTED_API_VERSION: String =
            "2.0.99".intern() /* avoid constant folding by the compiler */
    }
}