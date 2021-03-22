package com.poxiao.gateway.handle;

import com.google.code.kaptcha.Producer;
import com.poxiao.common.core.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author qinqi
 * @date 2020/9/11
 */
@Slf4j
@Component
public class ImageCodeHandler implements HandlerFunction<ServerResponse> {

    @Resource
    private Producer producer;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {

        // 生成验证码
        String kapText = producer.createText();
        String questionStr = kapText.substring(0, kapText.lastIndexOf("@"));
        String answerStr = kapText.substring(kapText.lastIndexOf("@") + 1);
        BufferedImage kapImage = producer.createImage(questionStr);

        // 保存验证码信息
        String randomStr = UUID.randomUUID().toString().replaceAll("-", "");
        stringRedisTemplate.opsForValue().set(Constants.DEFAULT_CODE_KEY+randomStr,answerStr,60, TimeUnit.SECONDS);

        // 转换流信息写出
        FastByteArrayOutputStream fastByteArrayOutputStream = new FastByteArrayOutputStream();

        try {
            ImageIO.write(kapImage,"jpg",fastByteArrayOutputStream);
        } catch (IOException e) {
            log.error("ImageIO write err", e);
            return Mono.error(e);
        }
        return ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .header("randomStr",randomStr)
                .body(BodyInserters.fromResource(new ByteArrayResource(fastByteArrayOutputStream.toByteArray())));
    }
}
