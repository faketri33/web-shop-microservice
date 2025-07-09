package org.faketri.infastructure.image.controller;

import org.faketri.infastructure.image.gateway.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
