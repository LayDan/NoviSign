package org.novisign.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.novisign.controller.request.ImageRequest;
import org.novisign.model.Image;
import org.novisign.repository.ImageRepository;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImageServiceTest {
    private final ImageRepository repository = Mockito.mock(ImageRepository.class);
    private final ImageService service = new ImageService(repository);

    @Test
    void addImage() {
        ImageRequest request = new ImageRequest("https://telemart.ua/theme/main/i/logo.svg", 1.);
        when(repository.existsByUrl(request.url())).thenReturn(false);

        Image savedImage = new Image(1L, "https://telemart.ua/theme/main/i/logo.svg", 1., LocalDateTime.now());
        when(repository.save(any(Image.class))).thenReturn(savedImage);

        Image result = service.addImage(request);

        assertNotNull(result);
        assertEquals("https://telemart.ua/theme/main/i/logo.svg", result.getUrl());
    }

    @Test
    void addImage_DuplicateKeyException() {
        ImageRequest request = new ImageRequest("https://telemart.ua/theme/main/i/logo.svg", 1.);
        when(repository.existsByUrl(request.url())).thenReturn(true);

        assertThrows(DuplicateKeyException.class, () -> service.addImage(request));
    }

    @Test
    void deleteImage() {
        Long imageId = 1L;

        service.deleteImage(imageId);

        verify(repository, times(1)).deleteById(imageId);
    }

    @Test
    void findById() {
        Long imageId = 1L;
        Image image = new Image(imageId, "https://telemart.ua/theme/main/i/logo.svg", 1., LocalDateTime.now());
        when(repository.findById(imageId)).thenReturn(Optional.of(image));

        Image result = service.findById(imageId);

        assertNotNull(result);
        assertEquals(imageId, result.getId());
    }

    @Test
    void findById_EntityNotFoundException() {
        Long imageId = 1L;
        when(repository.findById(imageId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findById(imageId));
    }
}