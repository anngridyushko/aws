package controller;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class S3Storage {

    String bucket = "bylex-practice-bucket";
    Region region = Region.US_EAST_2;
    S3Presigner presigner;

    Duration putUrlDuration = Duration.ofDays(1);
    Duration getUrlDuration = Duration.ofDays(1);

    public S3Storage() throws IOException {
        presigner = S3Presigner.builder()
                .region(region)
                .build();
    }

    public URL getPutPresignedUrl(String key) {
        var putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();
        var putPresignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(putUrlDuration)
                .putObjectRequest(putObjectRequest)
                .build();
        var presignedRequest = presigner.presignPutObject(putPresignRequest);
        return presignedRequest.url();
    }

    public URL getGetPresignedUrl(String key) {
        var getObjectRequest =
                GetObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build();
        var getPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(getUrlDuration)
                .getObjectRequest(getObjectRequest)
                .build();
        var presignedRequest = presigner.presignGetObject(getPresignRequest);
        return presignedRequest.url();
    }
}
