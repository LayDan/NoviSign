package org.novisign.controller.request;

import java.util.List;

public record SlideshowRequest(List<SlideshowImage> slideshowImages) {
    public record SlideshowImage(Long id, Double duration) {
    }
}
