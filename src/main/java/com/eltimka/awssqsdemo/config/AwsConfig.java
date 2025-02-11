package com.eltimka.awssqsdemo.config;
import com.amazonaws.service.sqs.AmazonSQSAsync;
import com.amazonaws.service.sqs.AmazonSQSAsyncClientBuilder;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;


@Configuration
public class AwsConfig {
    @Bean
    public AWSCredentialsProvider awsCredentialsProvider(@Value("${spring.cloud.aws.credentials.access-key}") String accessKey,
                                                         @Value("${spring.cloud.aws.credentials.secret-key}") String secretKey){

        return AWSCredentialsProvider(new BasicAWSCredentials(accessKey,secretKey));
}

    @Bean
    public AwsCredentialsProvider awsAsyncCredentialsProvider (@Value("${spring.cloud.aws.credentials.access-key}") String accessKey,
                                                         @Value("${spring.cloud.aws.credentials.secret-key}") String secretKey){
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey,secretKey));

    }
    public AmazonSQSAsync amazonSQSAsync(AWSCredentialsProvider awsCredentialsProvider,
                                         @Value("${sqs.notifications.queue.region}") String region) {
        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(region)
                .build();
    }
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync){
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

}
