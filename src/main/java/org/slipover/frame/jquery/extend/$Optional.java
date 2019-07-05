package org.slipover.frame.jquery.extend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 扩展 java.util.Optional
 * PS：由于 java.util.Optional 无法继承，copy java.util.Optional 实现
 */
@SuppressWarnings("unchecked")
public class $Optional<T> implements Serializable {

    /**
     * @see Optional#EMPTY
     */
    private static final $Optional<?> EMPTY = new $Optional<>();

    /**
     * @see Optional#value
     */
    private final T value;

    /**
     * @see Optional#Optional()
     */
    private $Optional() {
        this.value = null;
    }

    /**
     * @see Optional#empty()
     */
    public static <T> $Optional<T> empty() {
        return ($Optional<T>) EMPTY;
    }

    /**
     * @see Optional#Optional(java.lang.Object)
     */
    private $Optional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * @see Optional#of(java.lang.Object)
     */
    public static <T> $Optional<T> of(T value) {
        return new $Optional<>(value);
    }

    /**
     * @see Optional#ofNullable(java.lang.Object)
     */
    public static <T> $Optional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * @see Optional#get()
     */
    public T get() {
        return Objects.requireNonNull(value, "No value $Present");
    }

    /**
     * @see Optional#ifPresent(java.util.function.Consumer)
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * @see Optional#of(java.lang.Object)
     */
    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null)
            consumer.accept(value);
    }

    /**
     * @see Optional#filter(java.util.function.Predicate)
     */
    public $Optional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent())
            return this;
        else
            return predicate.test(value) ? this : empty();
    }

    /**
     * @see Optional#map(java.util.function.Function)
     */
    public<U> $Optional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return $Optional.ofNullable(mapper.apply(value));
        }
    }

    /**
     * @see Optional#flatMap(java.util.function.Function)
     */
    public<U> $Optional<U> flatMap(Function<? super T, $Optional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    /**
     * @see Optional#orElse(java.lang.Object)
     */
    public T orElse(T other) {
        return value != null ? value : other;
    }

    /**
     * @see Optional#orElseGet(java.util.function.Supplier)
     */
    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    /**
     * @see Optional#orElseThrow(java.util.function.Supplier)
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    /**
     * @see Optional#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof $Optional)) {
            return false;
        }

        $Optional<?> other = ($Optional<?>) obj;
        return Objects.equals(value, other.value);
    }

    /**
     * @see Optional#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /**
     * @see Optional#toString()
     */
    @Override
    public String toString() {
        return value != null ? String.format("Optional[%s]", value) : "Optional.empty";
    }

    /**
     * 得到 java.util.Optional 包装对象
     * @return
     */
    public Optional<T> optional() {
        return Optional.ofNullable(value);
    }

    /**
     * 是否可以序列化
     * @return
     */
    public boolean isSerializable(){
        return value instanceof Serializable;
    }

    /**
     * 如果可以序列化返回序列化结果，否则返回toString
     * @return
     */
    public String toSerializable() {
        if (isSerializable()) {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream)) {
                objectOutput.writeObject(get());
                return outputStream.toString();
            } catch (Exception ignored) { }
        }
        return get().toString();
    }

    /**
     * 将 对象 转为JSON字符串
     * @return
     */
    public String toJSONString() {
        return JSON.toJSONString(get());
    }

    /**
     * 将 对象 转为JSONObject
     * @return
     */
    public JSONObject toJSONObject() {
        return JSON.parseObject(toJSONString());
    }

    public String toGetParam() {
        JSONObject jsonObject = toJSONObject();
        if (jsonObject.size() > 0) {
            StringBuilder builder = new StringBuilder("?");
            for (Field field : get().getClass().getDeclaredFields()) {
                String fieldName = field.getName();
                if (field.isAnnotationPresent(JSONField.class)) {
                    JSONField jsonField = field.getAnnotation(JSONField.class);
                    if (!StringUtils.isEmpty(jsonField.name().trim())) {
                        fieldName = jsonField.name().trim();
                    }
                }
                if (jsonObject.containsKey(fieldName)) {
                    Object value = jsonObject.get(fieldName);
                    String param = URLEncoder.encode(value.toString(), StandardCharsets.UTF_8);
                    builder.append(fieldName).append("=").append(param).append("&");
                }
            }
            return builder.substring(0, builder.length() - 1);
        }
        return "";
    }

}
