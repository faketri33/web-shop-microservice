package org.shared.image.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;

import java.net.URI;

@Configuration
public class S3Config {

    private static final Logger log = LoggerFactory.getLogger(S3Config.class);
    @Value("${minio.endpoint}")
    private String uri;

    @Value("${minio.region}")
    private String region;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Bean(destroyMethod = "close")
    public SdkAsyncHttpClient nettyHttpClient() {
        return NettyNioAsyncHttpClient.builder()
                .maxConcurrency(200)
                .maxPendingConnectionAcquires(10_000)
                .connectionAcquisitionTimeout(java.time.Duration.ofSeconds(60))
                .connectionTimeout(java.time.Duration.ofSeconds(20))
                .readTimeout(java.time.Duration.ofSeconds(120))
                .build();
    }

    @Bean(destroyMethod = "close")
    public S3AsyncClient s3AsyncClient(SdkAsyncHttpClient netty) {
        log.info("Creating S3AsyncClient");
        log.info("Region: {}", region);
        log.info("Access Key: {}", accessKey);

        return S3AsyncClient.builder()
                .httpClient(netty)
                .endpointOverride(URI.create(uri))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .region(Region.of(region))
                .serviceConfiguration(builder -> builder.pathStyleAccessEnabled(true))
                .build();
    }
}
