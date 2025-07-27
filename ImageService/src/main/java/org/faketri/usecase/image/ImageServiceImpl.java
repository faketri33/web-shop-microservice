package org.faketri.usecase.image;

import org.faketri.infastructure.image.gateway.ImageService;
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

@Service
public class ImageServiceImpl implements ImageService {

    private final S3AsyncClient s3;
    private final String bucket;

    public ImageServiceImpl(S3AsyncClient s3, @Value("${minio.bucket}") String bucket) {
        this.s3 = s3;
        this.bucket = bucket;
    }

    @Override
    public Mono<String> uploadImage(String fileName, Mono<FilePart> filePartMono) {
        return filePartMono
                .flatMap(filePart -> DataBufferUtils.join(filePart.content()))
                .map(this::dataBufferToByte)
                .flatMap(bytes -> Mono.fromFuture(
                                s3.putObject(putObjectRequestBuilder(fileName), AsyncRequestBody.fromBytes(bytes))
                        ).thenReturn(fileName)
                );
    }

    @Override
    public Mono<byte[]> downloadImage(String fileName) {
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
}
