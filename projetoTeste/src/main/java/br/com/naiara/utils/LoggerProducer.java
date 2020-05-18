package br.com.naiara.utils;

import java.util.logging.Logger;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.Produces;

public class LoggerProducer {
    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
