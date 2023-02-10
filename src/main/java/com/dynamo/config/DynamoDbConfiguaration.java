package com.dynamo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDbConfiguaration {
	@Value("${aws.dynamodb.endpoint}")
	private String dynamoDbEndpoint;
	@Value("${aws.dynamodb.accessKey}")
	private String awsAccessKey;
	@Value("${aws.dynamodb.secretKey}")
	private String awsSecretKey;

	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(amazonDynamoDB());
	}

	private AmazonDynamoDB amazonDynamoDB() {
		// TODO Auto-generated method stub
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDbEndpoint, "ap-northeast-1"))
				.withCredentials(amazonDynamoDBCrendentials()).build();
	}

	private AWSCredentialsProvider amazonDynamoDBCrendentials() {
		return new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey));

	}

}
