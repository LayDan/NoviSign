package org.novisign.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.novisign.controller.request.SlideshowRequest;
import org.novisign.model.Image;
import org.novisign.model.Slideshow;
import org.novisign.model.SlideshowImage;
import org.novisign.repository.SlideshowRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SlideshowService implements SlideshowOperationService, SlideshowCheckService {

    SlideshowRepository repository;

    ImageService imageService;

    public SlideshowService(SlideshowRepository repository, ImageService imageService) {
        this.repository = repository;
        this.imageService = imageService;
    }

    @Override
    public Slideshow addSlideShow(SlideshowRequest request) {
        var slideshow = Slideshow.builder()
                .creationDate(LocalDateTime.now(ZoneOffset.UTC))
                .images(new ArrayList<>())
                .build();
        fillSlideshowImages(request, slideshow);
        var newSlideshow = repository.save(slideshow);
        log.info("Slideshow was add with id %d".formatted(newSlideshow.getId()));
        return newSlideshow;

    }

    @Override
    @CacheEvict(value = {"slideshow", "slideshow_images"}, key = "#id")
    public void deleteSlideshow(Long id) {
        repository.deleteById(id);
        log.info("Slideshow was delete by id %d".formatted(id));
    }

    @Cacheable(value = "slideshow", key = "#id")
    public Slideshow findById(Long id) {
        return repository.findSlideshowById(id);
    }

    private void fillSlideshowImages(SlideshowRequest request, Slideshow slideshow) {
        for (int i = 0; i < request.slideshowImages().size(); i++) {
            var slideshowImage = request.slideshowImages().get(i);
            Image image = imageService.findById(slideshowImage.id());
            var slideImage = SlideshowImage.builder()
                    .orderIndex(i)
                    .image(image)
                    .duration(slideshowImage.duration())
                    .slideshow(slideshow)
                    .build();
            slideshow.getImages().add(slideImage);
        }
    }

    @Override
    @Cacheable(value = "slideshow_images", key = "#id")
    public List<SlideshowImage> getOrder(Long id) {
        Slideshow slideshow = findById(id);
        if (slideshow == null) {
            throw new EntityNotFoundException("Slideshow not found by id %s".formatted(id));
        }
        List<SlideshowImage> images = slideshow.getImages();
        images.sort(Comparator.comparing(slideshowImage -> slideshowImage.getImage().getAdditionDate()));
        return images;
    }

    @Override
    public void proof(Long slideshowId, Long imageId) {
        //I do not understand why interviewer needs this method, because he/she does not specify and special logic here, so I add kafka log here
        log.info("Proof event for %d slideshow and %s image".formatted(slideshowId, imageId));
    }
}