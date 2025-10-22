package dev.zisan.smsagent.controller;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Message;
import dev.zisan.smsagent.services.InfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/twilio/webhook")
@RequiredArgsConstructor
public class TwilioController {

    private final InfoService infoService;


    @PostMapping(value = "/whatsapp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> handleWhatsappMessageWithSDK(@RequestParam("From") String from,
                                                        @DefaultValue("Tell me about Zisan.") @RequestParam("Body") String body,
                                                        @RequestParam("MessageSid") String messageSid) {

        log.info("Received WhatsApp message from: {}, MessageSid: {}, Body: {}", from, messageSid, body);

        String response = infoService.getInfoResponse(body);

        Message message = new Message.Builder(response).build();
        MessagingResponse res = new MessagingResponse.Builder().message(message).build();

        System.out.println(res.toXml());

        return ResponseEntity.ok(res.toXml());
    }
}
