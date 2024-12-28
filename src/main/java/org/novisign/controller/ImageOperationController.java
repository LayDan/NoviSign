package org.novisign.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.novisign.controller.request.ImageRequest;
import org.novisign.controller.result.ImageResult;
import org.novisign.model.Image;
import org.novisign.service.ImageOperationService;
import org.springframework.web.bind.annotation.*;

import static org.novisign.util.ImageValidation.isImageUrlValid;

@RestController
@RequestMapping("/image/")
@Tag(name = "Image Operation Controller", description = "API for work with images")
public class ImageOperationController {

    private final ImageOperationService service;

    public ImageOperationController(ImageOperationService service) {
        this.service = service;
    }

    @PostMapping("/addImage")
    @Operation(summary = "Add image", method = "POST")
    public ImageResult addImage(@RequestBody @Valid ImageRequest request) {
        boolean isValidImage = isImageUrlValid(request.url());
        if (!isValidImage) {
            throw new IllegalArgumentException("Image from url is not valid");
        }
        Image image = service.addImage(request);
        return new ImageResult(image.getId(), image.getUrl(), image.getAdditionDate(), image.getDuration());
    }

    @DeleteMapping("/deleteImage/{id}")
    @Operation(summary = "Delete image", method = "DELETE")
    public void addImage(@PathVariable("id") Long id) {
        service.deleteImage(id);
    }
}