package org.novisign.controller.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.novisign.util.DatePattern;

import java.time.LocalDateTime;

public record ImageResult(@JsonProperty("id") Long id,
                          @JsonProperty("url") String url,
                          @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DatePattern.ISO)
                          @JsonProperty("creationDate") LocalDateTime creationDate,
                          @JsonProperty("duration") Double duration) {
}
