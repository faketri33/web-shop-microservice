package org.catalog.infrastructure.image.controller;

import org.shared.image.gateway.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/catalog/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping("/{fileName}")
    public ResponseEntity<Mono<byte[]>> getImages(@PathVariable String fileName) {
        return ResponseEntity.ok(imageService.downloadImage(fileName));
    }
}
