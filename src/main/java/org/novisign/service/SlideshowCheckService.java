package org.novisign.service;

import org.novisign.model.SlideshowImage;

import java.util.List;

public interface SlideshowCheckService {

    List<SlideshowImage> getOrder(Long slideshowId);

    void proof(Long slideshowId, Long imageId);
}
