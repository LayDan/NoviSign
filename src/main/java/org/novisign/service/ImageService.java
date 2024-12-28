package org.novisign.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.novisign.controller.request.ImageRequest;
import org.novisign.model.Image;
import org.novisign.repository.ImageRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ImageService implements ImageOperationService {

    ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image addImage(ImageRequest request) {
        if (imageRepository.existsByUrl(request.url())) {
            throw new DuplicateKeyException("There is already created image with the url: %s".formatted(request.url()));
        }
        var newImage = imageRepository.save(Image.builder()
                .url(request.url())
                .additionDate(LocalDateTime.now(ZoneOffset.UTC))
                .duration(request.duration())
                .build());
        log.info("Image was add with id %d".formatted(newImage.getId()));
        return newImage;
    }

    @Override
    @CacheEvict(value = "images", key = "#id")
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
        log.info("Image was delete with id %d".formatted(id));
    }

    @Cacheable(value = "images", key = "#id")
    public Image findById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Image not found by id " + id));
    }
}
