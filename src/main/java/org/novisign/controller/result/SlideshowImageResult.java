package org.novisign.controller.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SlideshowImageResult(@JsonProperty("imageID") Long image,
                                   @JsonProperty("duration") Double duration) {
}