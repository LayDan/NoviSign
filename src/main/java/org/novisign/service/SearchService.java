package org.novisign.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.novisign.controller.result.SearchResult;
import org.novisign.repository.ImageRepository;
import org.novisign.repository.SlideshowImageRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SearchService {

    ImageRepository imageRepository;

    SlideshowImageRepository slideshowImageRepository;

    public SearchService(ImageRepository imageRepository, SlideshowImageRepository slideshowImageRepository) {
        this.imageRepository = imageRepository;
        this.slideshowImageRepository = slideshowImageRepository;
    }

    public SearchResult search(final String url, final Double duration) {
        Set<Long> imageIds = new HashSet<>();
        if (url != null && !url.isBlank()) {
            imageIds = imageRepository.findImageIdsByUrl(url);
        }
        if (duration != null) {
            imageIds = imageRepository.findImageIdsByDuration(duration);
        }
        Set<Long> slideshowIds = slideshowImageRepository.findSlideshowIdsByImageIds(imageIds);
        return new SearchResult(imageIds, slideshowIds);
    }
}
