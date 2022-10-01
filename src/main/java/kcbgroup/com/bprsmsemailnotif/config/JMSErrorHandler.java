package kcbgroup.com.bprsmsemailnotif.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Service
public class JMSErrorHandler implements ErrorHandler{

    private static final Logger logger = LoggerFactory.getLogger(JMSErrorHandler.class);

    @Override
    public void handleError(Throwable t) {
        logger.error(t.getMessage());
        logger.error(t.getLocalizedMessage());
    }

}
