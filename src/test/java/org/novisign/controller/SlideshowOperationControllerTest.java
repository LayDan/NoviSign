package org.novisign.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.novisign.controller.request.SlideshowRequest;
import org.novisign.controller.result.SlideshowResult;
import org.novisign.model.Slideshow;
import org.novisign.service.SlideshowOperationService;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SlideshowOperationControllerTest {
    private final SlideshowOperationService service = Mockito.mock(SlideshowOperationService.class);
    private final SlideshowOperationController controller = new SlideshowOperationController(service);

    @Test
    void addSlideshow() {
        SlideshowRequest request = new SlideshowRequest(new ArrayList<>());
        Slideshow slideshow = new Slideshow(1L, new ArrayList<>(), LocalDateTime.now());
        when(service.addSlideShow(request)).thenReturn(slideshow);

        SlideshowResult result = controller.addSlideshow(request);

        assertNotNull(result);
        verify(service, times(1)).addSlideShow(request);
    }

    @Test
    void deleteSlideshow() {
        Long slideshowId = 1L;

        controller.addImage(slideshowId);

        verify(service, times(1)).deleteSlideshow(slideshowId);
    }
}