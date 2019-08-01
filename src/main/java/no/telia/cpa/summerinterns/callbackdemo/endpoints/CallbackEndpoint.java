package no.telia.cpa.summerinterns.callbackdemo.endpoints;

import no.telia.cpa.summerinterns.callbackdemo.services.CallbackService;
import no.telia.cpa.summerinterns.ws.smscallback.DeliverReq;
import no.telia.cpa.summerinterns.ws.smscallback.DeliverRsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CallbackEndpoint {
    private static final String NAMESPACE_URI = "http://differitas.no/2006/09/messaging/sms";

    private final CallbackService callbackService;

    @Autowired
    public CallbackEndpoint(CallbackService callbackService) {
        this.callbackService = callbackService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeliverReq")
    @ResponsePayload
    public DeliverRsp deliverReq(@RequestPayload DeliverReq request) throws Exception {
        return callbackService.handleMoMessage(request.getDeliverMessage());
    }



}
