module com.arcao.slf4j.timber {
    requires org.slf4j;
    provides org.slf4j.spi.SLF4JServiceProvider with com.arcao.slf4j.timber.TimberLoggerServiceProvider;

    exports com.arcao.slf4j.timber;
    opens com.arcao.slf4j.timber to org.slf4j;
}