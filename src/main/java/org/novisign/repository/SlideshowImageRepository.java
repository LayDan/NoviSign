package org.novisign.repository;

import org.novisign.model.SlideshowImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface SlideshowImageRepository extends JpaRepository<SlideshowImage, Long> {

    @Query("""
            SELECT DISTINCT(slide_image.slideshow.id) FROM SlideshowImage slide_image
            JOIN slide_image.image image where image.id in(:imageIds)
            """)
    Set<Long> findSlideshowIdsByImageIds(@Param("imageIds") Set<Long> imageIds);
}
