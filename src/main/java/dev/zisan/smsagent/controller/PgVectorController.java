package dev.zisan.smsagent.controller;

import dev.zisan.smsagent.services.PgVectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("vector")
@RequiredArgsConstructor
public class PgVectorController {

    private final PgVectorService pgVectorService;

    @PostMapping(value = "load", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<Object, Object> addVector(@RequestPart("resume") MultipartFile resumePdf) {
        try {
            pgVectorService.addToDB(resumePdf.getResource());
            return Map.of("success", "Resume data loaded into vector store.");
        } catch (Exception e) {
            return Map.of("failure", "Failed to load resume data: " + e.getMessage());
        }
    }

    @DeleteMapping("delete")
    public Map<Object, Object> deleteFromDB(@DefaultValue("resume.pdf") @RequestParam(required = false) String filename) {
        try {
            pgVectorService.deleteResumeFromDB(filename);
        } catch (Exception e) {
            return Map.of("failure", "Failed to delete resume data: " + e.getMessage());
        }
        return Map.of("success", "Resume data deleted from vector store.");
    }
}
