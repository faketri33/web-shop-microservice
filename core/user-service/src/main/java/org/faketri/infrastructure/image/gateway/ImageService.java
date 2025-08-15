package org.faketri.infrastructure.image.gateway;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface ImageService {
    Mono<String> uploadImage(String fileName, Mono<FilePart> filePartMono);
    Mono<byte[]> downloadImage(String fileName);
}
