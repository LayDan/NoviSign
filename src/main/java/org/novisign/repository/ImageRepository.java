package org.novisign.repository;

import org.novisign.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT i.id FROM Image i WHERE LOWER(i.url) LIKE LOWER(CONCAT('%', :url, '%'))")
    Set<Long> findImageIdsByUrl(@Param("url") String url);

    @Query("SELECT i.id FROM Image i WHERE i.duration =:duration")
    Set<Long> findImageIdsByDuration(@Param("duration") Double duration);

    boolean existsByUrl(String url);
}
