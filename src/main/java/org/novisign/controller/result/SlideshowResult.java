package org.novisign.controller.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.novisign.model.Slideshow;
import org.novisign.model.SlideshowImage;
import org.novisign.util.DatePattern;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SlideshowResult {
    @JsonProperty("id")
    Long id;
    @JsonProperty("slides")
    List<SlideshowImageResult> slides;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DatePattern.ISO)
    @JsonProperty("creationDate")
    LocalDateTime creationDate;

    public SlideshowResult(Slideshow slideshow) {
        this.id = slideshow.getId();
        this.slides = slideshow.getImages().stream()
                .sorted(Comparator.comparing(SlideshowImage::getOrderIndex))
                .map(slideshowImage -> new SlideshowImageResult(slideshowImage.getImage().getId(), slideshowImage.getDuration()))
                .collect(Collectors.toList());
        this.creationDate = slideshow.getCreationDate();
    }


}
