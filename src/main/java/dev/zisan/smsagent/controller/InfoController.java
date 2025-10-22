package dev.zisan.smsagent.controller;

import dev.zisan.smsagent.dto.InfoRequest;
import dev.zisan.smsagent.services.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("info")
@RequiredArgsConstructor
public class InfoController {
    private final InfoService infoService;

    @PostMapping("ask")
    public Map<String, String> info(@RequestBody InfoRequest request) {
        return Map.of(
                "response", infoService.getInfoResponse(request.getQuestion()));
    }
}
