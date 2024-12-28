package org.novisign.service;

import org.novisign.controller.request.SlideshowRequest;
import org.novisign.model.Slideshow;

public interface SlideshowOperationService {
    Slideshow addSlideShow(SlideshowRequest request);

    void deleteSlideshow(Long id);
}
