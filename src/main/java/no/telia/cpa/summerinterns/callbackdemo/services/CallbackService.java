package no.telia.cpa.summerinterns.callbackdemo.services;

import no.telia.cpa.summerinterns.ws.smscallback.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CallbackService {
    private final static Logger logger = LoggerFactory.getLogger(CallbackService.class);


    public DeliverRsp handleMoMessage(DeliverMessage message) {
        // do something with message...
        logger.debug("Incoming message from {}", message.getOA().getValue());

        // create & return response
        return createMoResponse(message);
    }

    private DeliverRsp createMoResponse(DeliverMessage message) {
        Status status = new Status();
        status.setCode(200);
        status.setValue("SUCCESS");

        ReportMessage reportMessage = new ReportMessage();
        reportMessage.setId(message.getId());
        reportMessage.setStatus(status);

        DeliverRsp response = new DeliverRsp();
        response.setReportMessage(reportMessage);
        return response;
    }

}
