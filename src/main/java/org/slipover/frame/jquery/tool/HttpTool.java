package org.slipover.frame.jquery.tool;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 对 http 请求进行封装
 * @author slipover
 */
@SuppressWarnings("unchecked")
public interface HttpTool {

    RestTemplate restTemplate = new RestTemplate();

    default String get(String url) {
        return get(url, String.class);
    }

    default <T> T get(String url, Class<T> requiredType) {
        return restTemplate.getForObject(url, requiredType);
    }

    default String get(String url, Consumer<HttpHeaders> consumer) {
        return get(url, consumer, String.class);
    }

    default <T> T get(String url, Consumer<HttpHeaders> consumer, Class<T> requiredType) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        consumer.accept(httpHeaders);
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), requiredType).getBody();
    }

    default String get(URI uri) {
        return get(uri, String.class);
    }

    default <T> T get(URI uri, Class<T> requiredType) {
        return restTemplate.getForObject(uri, requiredType);
    }

    default String get(URI uri, Consumer<HttpHeaders> consumer) {
        return get(uri, consumer, String.class);
    }

    default <T> T get(URI uri, Consumer<HttpHeaders> consumer, Class<T> requiredType) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        consumer.accept(httpHeaders);
        return restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(httpHeaders), requiredType).getBody();
    }

    default String post(String url) {
        return post(url, new HashMap<>());
    }

    default String post(String url, BiConsumer<HttpHeaders, Map<String, Object>> consumer) {
        return post(url, consumer, String.class);
    }

    default <T> T post(String url, BiConsumer<HttpHeaders, Map<String, Object>> consumer, Class<T> requiredType) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final HashMap param = new HashMap<>();
        consumer.accept(httpHeaders, param);
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(param, httpHeaders), requiredType).getBody();
    }

    default String post(String url, Object param) {
        return post(url, param, String.class);
    }

    default <T> T post(String url, Object param, Class<T> requiredType) {
        return restTemplate.postForObject(url, param, requiredType);
    }

    default String post(URI uri) {
        return post(uri, new HashMap<>());
    }

    default String post(URI uri, BiConsumer<HttpHeaders, Map<String, Object>> consumer) {
        return post(uri, consumer, String.class);
    }

    default <T> T post(URI uri, BiConsumer<HttpHeaders, Map<String, Object>> consumer, Class<T> requiredType) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final HashMap param = new HashMap<>();
        consumer.accept(httpHeaders, param);
        return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(param, httpHeaders), requiredType).getBody();
    }

    default String post(URI uri, Object param) {
        return post(uri, param, String.class);
    }

    default <T> T post(URI uri, Object param, Class<T> requiredType) {
        return restTemplate.postForObject(uri, param, requiredType);
    }

}
