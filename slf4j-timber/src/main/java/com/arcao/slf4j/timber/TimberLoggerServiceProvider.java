package com.arcao.slf4j.timber;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.NOPMDCAdapter;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

/**
 * The binding of {@link org.slf4j.LoggerFactory} class with an actual instance of
 * {@link ILoggerFactory} is performed using information returned by this class.
 *
 * @author Martin Sloup <arcao@arcao.com>
 */
public class TimberLoggerServiceProvider implements SLF4JServiceProvider {
  /**
   * Declare the version of the SLF4J API this implementation is compiled against.
   * The value of this field is modified with each major release.
   */
  public static String REQUESTED_API_VERSION = "2.0.99".intern(); /* avoid constant folding by the compiler */

  private final ILoggerFactory loggerFactory = new TimberLoggerFactory();
  private final IMarkerFactory markerFactory = new BasicMarkerFactory();
  private final MDCAdapter mdcAdapter = new NOPMDCAdapter();

  @Override
  public ILoggerFactory getLoggerFactory() {
    return loggerFactory;
  }

  @Override
  public IMarkerFactory getMarkerFactory() {
    return markerFactory;
  }

  @Override
  public MDCAdapter getMDCAdapter() {
    return mdcAdapter;
  }

  @Override
  public String getRequestedApiVersion() {
    return REQUESTED_API_VERSION;
  }

  @Override
  public void initialize() {

  }
}
