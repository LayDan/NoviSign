package org.novisign.repository;

import org.novisign.model.Slideshow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideshowRepository extends JpaRepository<Slideshow, Long> {

    //    @EntityGraph(attributePaths = {"images.image"})
    @Query("SELECT s FROM Slideshow s " +
            "LEFT JOIN FETCH s.images si " +
            "LEFT JOIN FETCH si.image i " +
            "WHERE s.id = :id")
    Slideshow findSlideshowById(@Param("id") Long id);
}
