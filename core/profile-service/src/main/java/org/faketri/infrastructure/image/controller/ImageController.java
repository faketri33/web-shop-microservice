package org.faketri.infrastructure.image.controller;

import org.faketri.infrastructure.image.gateway.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Mono<ResponseEntity<String>> upload(@RequestPart("file") Mono<FilePart> file) {
        String fileName = UUID.randomUUID() + ".jpg";
        return imageService.uploadImage(fileName, file)
                .map(name -> ResponseEntity.ok("Uploaded as: " + name));
    }

    @RequestMapping(value = "/{fileName}", method = RequestMethod.GET)
    public Mono<ResponseEntity<byte[]>> get(@PathVariable String fileName) {
        return imageService.downloadImage(fileName)
                .map(data -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(data));
    }
}
