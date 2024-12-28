package org.novisign.service;

import org.novisign.controller.request.ImageRequest;
import org.novisign.model.Image;

public interface ImageOperationService {

    Image addImage(ImageRequest request);

    void deleteImage(Long id);
}
