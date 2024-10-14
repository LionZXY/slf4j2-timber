package uk.kulikov.slf4j2.timber

import android.util.Log
import org.fest.assertions.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import org.robolectric.shadows.ShadowLog.LogItem
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import timber.log.Timber

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class TimberLoggerAdapterTest {
    @Before
    @After
    fun setUpAndTearDown() {
        Timber.uprootAll()
    }

    @Test
    fun debugTest() {
        Timber.plant(Timber.DebugTree())

        logger.debug("Hello, world!")

        val logs = ShadowLog.getLogs()
        assertThat(logs).hasSize(1)
        val log: LogItem = logs[0]
        assertThat(log.type).isEqualTo(Log.DEBUG)
        assertThat(log.tag).isEqualTo("TimberLoggerAdapterTest")
        assertThat(log.msg).isEqualTo("Hello, world!")
        assertThat(log.throwable).isNull()
    }

    @Test
    fun nullsTest() {
        Timber.plant(Timber.DebugTree())

        logger.trace(null)
        logger.trace(null as String?, null)
        logger.trace(null as String?, null, null)
        logger.trace(null as String?, null, null, null)
        logger.trace(null as String?, null as? Throwable?)

        logger.debug(null)
        logger.debug(null as String?, null)
        logger.debug(null as String?, null, null)
        logger.debug(null as String?, null, null, null)
        logger.debug(null as String?, null as? Throwable?)

        logger.info(null)
        logger.info(null as String?, null)
        logger.info(null as String?, null, null)
        logger.info(null as String?, null, null, null)
        logger.info(null as String?, null as? Throwable?)

        logger.warn(null)
        logger.warn(null as String?, null)
        logger.warn(null as String?, null, null)
        logger.warn(null as String?, null, null, null)
        logger.warn(null as String?, null as? Throwable?)

        logger.error(null)
        logger.error(null as String?, null)
        logger.error(null as String?, null, null)
        logger.error(null as String?, null, null, null)
        logger.error(null as String?, null as? Throwable?)

        val logs = ShadowLog.getLogs()
        assertThat(logs).isEmpty()
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TimberLoggerAdapterTest::class.java)
    }
}