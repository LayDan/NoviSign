package org.novisign.controller;

import org.novisign.controller.request.SlideshowRequest;
import org.novisign.controller.result.SlideshowResult;
import org.novisign.model.Slideshow;
import org.novisign.service.SlideshowOperationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class SlideshowOperationController {

    private final SlideshowOperationService service;

    public SlideshowOperationController(SlideshowOperationService service) {
        this.service = service;
    }

    @PostMapping("/addSlideshow")
    public SlideshowResult addSlideshow(@RequestBody SlideshowRequest request) {
        Slideshow slideshow = service.addSlideShow(request);
        return new SlideshowResult(slideshow);
    }

    @DeleteMapping("/deleteSlideshow/{id}")
    public void addImage(@PathVariable("id") Long id) {
        service.deleteSlideshow(id);
    }
}
