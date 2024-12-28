package org.novisign.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.novisign.controller.result.SlideshowImageResult;
import org.novisign.model.Image;
import org.novisign.model.SlideshowImage;
import org.novisign.service.SlideshowCheckService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SlideshowControllerTest {
    private final SlideshowCheckService service = Mockito.mock(SlideshowCheckService.class);
    private final SlideshowController controller = new SlideshowController(service);

    @Test
    void getOrder() {
        Long slideshowId = 1L;
        List<SlideshowImage> images = List.of(
                new SlideshowImage(1L, null, Image.builder().id(1L).build(), 1., 1),
                new SlideshowImage(2L, null, Image.builder().id(2L).build(), 1., 2)
        );

        when(service.getOrder(slideshowId)).thenReturn(images);

        List<SlideshowImageResult> results = controller.getOrder(slideshowId);

        assertEquals(2, results.size());
        assertEquals(1, results.get(0).duration());
    }

    @Test
    void getProof() {
        Long slideshowId = 1L;
        Long imageId = 2L;

        controller.getProof(slideshowId, imageId);

        verify(service, times(1)).proof(slideshowId, imageId);
    }
}