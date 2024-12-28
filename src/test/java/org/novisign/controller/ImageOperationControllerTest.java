package org.novisign.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.novisign.controller.request.ImageRequest;
import org.novisign.controller.result.ImageResult;
import org.novisign.model.Image;
import org.novisign.service.ImageOperationService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ImageOperationControllerTest {
    private final ImageOperationService service = Mockito.mock(ImageOperationService.class);
    private final ImageOperationController controller = new ImageOperationController(service);

    @Test
    void addImage() {
        ImageRequest request = new ImageRequest("https://telemart.ua/theme/main/i/logo.svg", 1.);
        Image image = new Image(1L, "https://telemart.ua/theme/main/i/logo.svg", 1., LocalDateTime.now());
        when(service.addImage(request)).thenReturn(image);

        ImageResult result = controller.addImage(request);

        assertEquals(1L, result.id());
        assertEquals("https://telemart.ua/theme/main/i/logo.svg", result.url());
    }

    @Test
    void deleteImage() {
        Long imageId = 1L;

        controller.addImage(imageId);

        verify(service, times(1)).deleteImage(imageId);
    }
}