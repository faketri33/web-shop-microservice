package org.shared.image.usecase;

import org.shared.image.gateway.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.BytesWrapper;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;


@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);
    private final S3AsyncClient s3;
    private final String bucket;

    public ImageServiceImpl(S3AsyncClient s3, @Value("${minio.bucket}") String bucket) {
        log.info("ImageServiceImpl - {}", bucket);
        this.s3 = s3;
        this.bucket = bucket;
    }

    public Mono<String> uploadImage(String fileName, FilePart filePart) {
        log.info("Uploading image to S3 - {}", fileName);
        return DataBufferUtils.join(filePart.content())
                .map(this::dataBufferToByte)
                .flatMap(bytes -> Mono.fromFuture(() ->
                        s3.putObject(
                                putObjectRequestBuilder(fileName),
                                AsyncRequestBody.fromBytes(bytes)
                        )
                ))
                .thenReturn(buildPublicUrl(fileName))
                .doOnSuccess(r -> log.info("Image {} uploaded successfully", fileName))
                .doOnError(err -> log.error("Error uploading image {}", fileName, err));
    }

    public Mono<byte[]> downloadImage(String fileName) {
        log.info("Download image to S3 - {}", fileName);
        return Mono.fromFuture(
                s3.getObject(getObjectRequestBuilder(fileName), AsyncResponseTransformer.toBytes())
        ).map(BytesWrapper::asByteArray);
    }

    private byte[] dataBufferToByte(DataBuffer dataBuffer) {
        byte[] bytes = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(bytes);
        DataBufferUtils.release(dataBuffer);
        return bytes;
    }

    private GetObjectRequest getObjectRequestBuilder(String fileName) {
        return GetObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build();
    }

    private PutObjectRequest putObjectRequestBuilder(String fileName) {
        return PutObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .contentType("image/jpeg")
                .build();
    }

    private String buildPublicUrl(String fileName) {
        URI endpoint = s3.serviceClientConfiguration().endpointOverride()
                .orElseThrow(() -> new IllegalStateException("No endpoint configured for S3 client"));
        return String.format("%s/%s/%s", endpoint.toString(), bucket, fileName);
    }
}
