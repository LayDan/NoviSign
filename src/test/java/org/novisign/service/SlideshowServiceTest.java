package org.novisign.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.novisign.controller.request.SlideshowRequest;
import org.novisign.model.Slideshow;
import org.novisign.model.SlideshowImage;
import org.novisign.repository.SlideshowRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SlideshowServiceTest {

    private final SlideshowRepository repository = Mockito.mock(SlideshowRepository.class);
    private final ImageService imageService = Mockito.mock(ImageService.class);
    private final SlideshowService service = new SlideshowService(repository, imageService);

    @Test
    void addSlideShow() {
        SlideshowRequest request = new SlideshowRequest(new ArrayList<>());
        Slideshow slideshow = new Slideshow();
        when(repository.save(any(Slideshow.class))).thenReturn(slideshow);

        Slideshow result = service.addSlideShow(request);

        assertNotNull(result);
        verify(repository, times(1)).save(any(Slideshow.class));
    }

    @Test
    void deleteSlideshow() {
        Long slideshowId = 1L;

        service.deleteSlideshow(slideshowId);

        verify(repository, times(1)).deleteById(slideshowId);
    }

    @Test
    void findById() {
        Long slideshowId = 1L;
        Slideshow slideshow = new Slideshow();
        when(repository.findSlideshowById(slideshowId)).thenReturn(slideshow);

        Slideshow result = service.findById(slideshowId);

        assertNotNull(result);
        assertEquals(slideshow, result);
    }

    @Test
    void getOrder_EntityNotFoundException() {
        Long slideshowId = 1L;
        when(repository.findSlideshowById(slideshowId)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> service.getOrder(slideshowId));
    }

    @Test
    void getOrder() {
        Long slideshowId = 1L;
        List<SlideshowImage> images = new ArrayList<>();
        when(repository.findSlideshowById(slideshowId)).thenReturn(Slideshow.builder().images(images).build());

        List<SlideshowImage> result = service.getOrder(slideshowId);

        assertNotNull(result);
    }

    @Test
    void proof() {
        Long slideshowId = 1L;
        Long imageId = 2L;

        service.proof(slideshowId, imageId);
    }
}