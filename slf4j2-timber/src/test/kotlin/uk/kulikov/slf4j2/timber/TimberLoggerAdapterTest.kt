package uk.kulikov.slf4j2.timber

import org.fest.assertions.api.Assertions.assertThat
import java.util.List
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
import android.util.Log

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

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TimberLoggerAdapterTest::class.java)
    }
}