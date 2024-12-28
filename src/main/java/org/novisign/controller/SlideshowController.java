package org.novisign.controller;

import org.novisign.controller.result.SlideshowImageResult;
import org.novisign.service.SlideshowCheckService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slideShow/")
public class SlideshowController {

    private final SlideshowCheckService service;

    public SlideshowController(SlideshowCheckService service) {
        this.service = service;
    }

    /**
     * GET: Retrieve images in a slideshow ordered by image addition date.
     * As I understand, this request return all images in slideshow ordered by addition date, so maybe I do not need to create a
     * {@link org.novisign.model.SlideshowImage#orderIndex column}
     */
    @GetMapping("/{id}/slideshowOrder")
    public List<SlideshowImageResult> getOrder(@PathVariable("id") Long id) {
        return service.getOrder(id).stream().map(s -> new SlideshowImageResult(s.getImage().getId(), s.getDuration())).toList();
    }

    /**
     * POST: Record an event when an image is replaced by the next one.
     * As I understand, this type of request means that user plays next image, this is just an event, so I do not need to return something
     **/
    @PostMapping("/{id}/proof-of-play/{imageId}")
    public void getProof(@PathVariable("id") Long id, @PathVariable("imageId") Long imageId) {
        service.proof(id, imageId);
    }

}
