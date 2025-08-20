package org.shared.image.gateway;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface ImageService {
    Mono<String> uploadImage(String fileName, FilePart filePartMono);
    Mono<byte[]> downloadImage(String fileName);
}
