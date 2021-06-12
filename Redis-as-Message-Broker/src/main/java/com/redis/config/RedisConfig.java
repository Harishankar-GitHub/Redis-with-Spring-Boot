package com.redis.config;

import com.redis.subscriber.Subscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig
{
    private static final String CHANNEL_TOPIC = "rhsb_channelTopic";

    @Bean   // To establish connection to Redis.
    public JedisConnectionFactory jedisConnectionFactory()
    {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);

        return new JedisConnectionFactory(configuration);
    }

    @Bean // To access Redis from the Application.
    public RedisTemplate<String, Object> redisTemplate()
    {
        RedisTemplate redisTemplate = new RedisTemplate();

        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));

        return redisTemplate;
    }

    @Bean // Need a Channel between Publisher and Subscriber to communicate.
    public ChannelTopic topic()
    {
        return new ChannelTopic(CHANNEL_TOPIC);
    }

    @Bean // Need a Container who can manage Listener and ChannelTopic.
    public RedisMessageListenerContainer redisMessageListenerContainer()
    {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListenerAdapter(), topic());
        return container;
    }

    @Bean // To listen to the message.
    public MessageListenerAdapter messageListenerAdapter()
    {
        return new MessageListenerAdapter(new Subscriber());
    }
}
