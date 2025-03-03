package com.example.demo.stringRedisTemplate.service;

import java.util.concurrent.TimeUnit;

/**
 * @Discription:
 * @User: LiChenHao
 * @Date: 2025/3/3 下午4:59
 */

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 定义与 StringRedisTemplate 功能对应的接口，涵盖常见的 Redis 字符串操作。
 * 此接口旨在提供对 Redis 字符串类型数据操作的统一抽象，方便在不同业务场景中使用。
 */
public interface myRedisTemplateService {

    /**
     * 设置指定键的值。如果键已存在，新值将覆盖旧值。
     *
     * @param key 要设置的键
     * @param value 要设置的值
     */
    void set(String key, String value);

    /**
     * 设置指定键的值，并附带过期时间。
     *
     * @param key 要设置的键
     * @param value 要设置的值
     * @param timeout 过期时间的值
     * @param unit 过期时间的时间单位，如 TimeUnit.SECONDS（秒）、TimeUnit.MINUTES（分钟）等
     * @return 如果设置成功返回 true，否则返回 false
     */
    boolean set(String key, String value, long timeout, TimeUnit unit);

    /**
     * 获取指定键的值。如果键不存在，返回 null。
     *
     * @param key 要获取值的键
     * @return 键对应的值，如果键不存在则返回 null
     */
    String get(String key);

    /**
     * 设置新值并返回该键的旧值。如果键不存在，则返回 null。
     *
     * @param key 要操作的键
     * @param value 要设置的新值
     * @return 该键的旧值，如果键不存在则返回 null
     */
    String getAndSet(String key, String value);

    /**
     * 对存储在指定键的数字值执行增量操作。
     * 如果键不存在，将其初始化为 0 后再执行增量操作。
     * 如果值的类型不是数字，操作将失败。
     *
     * @param key 要操作的键
     * @param delta 要增加的值，可以为负数以执行减法操作
     * @return 操作后的新值
     */
    Long increment(String key, long delta);

    /**
     * 对存储在指定键的数字值执行增量操作。
     * 如果键不存在，将其初始化为 0 后再执行增量操作。
     * 如果值的类型不是数字，操作将失败。
     * 这里使用 double 类型的增量值。
     *
     * @param key 要操作的键
     * @param delta 要增加的值，可以为负数以执行减法操作
     * @return 操作后的新值
     */
    Double increment(String key, double delta);

    /**
     * 将多个键值对存储到 Redis 中。
     * 如果任何一个键已存在，对应的新值将覆盖旧值。
     *
     * @param map 包含键值对的 Map 对象
     */
    void multiSet(Map<String, String> map);

    /**
     * 仅当所有给定键都不存在时，才将多个键值对存储到 Redis 中。
     * 如果有任何一个键已存在，整个操作将被取消，所有键值对都不会被存储。
     *
     * @param map 包含键值对的 Map 对象
     * @return 如果所有键值对都成功存储返回 true，否则返回 false
     */
    boolean multiSetIfAbsent(Map<String, String> map);

    /**
     * 获取多个键对应的值。
     * 返回的 List 中元素顺序与传入的键集合顺序一致，不存在的键对应的值为 null。
     *
     * @param keys 要获取值的键的集合
     * @return 包含对应键值的 List，如果键不存在则对应位置为 null
     */
    List<String> multiGet(Collection<String> keys);

    /**
     * 从存储在指定键的字符串值中截取子字符串。
     * start 和 end 是基于 0 的索引，负数表示从字符串末尾开始计数。
     *
     * @param key 要操作的键
     * @param start 子字符串的起始位置
     * @param end 子字符串的结束位置
     * @return 截取的子字符串
     */
    String getRange(String key, long start, long end);

    /**
     * 将指定值追加到存储在指定键的字符串值末尾。
     * 如果键不存在，将创建一个新的键值对，值为追加的字符串。
     *
     * @param key 要操作的键
     * @param value 要追加的值
     * @return 追加后字符串的长度
     */
    Integer append(String key, String value);

    /**
     * 设置存储在指定键的字符串值中指定偏移量处的字符。
     * 如果偏移量超出当前字符串长度，字符串将自动扩展，中间部分用空字符填充。
     *
     * @param key 要操作的键
     * @param offset 字符的偏移量
     * @param value 要设置的字符
     */
    void setBit(String key, long offset, boolean value);

    /**
     * 获取存储在指定键的字符串值中指定偏移量处的字符。
     * 如果键不存在，返回 false。
     *
     * @param key 要操作的键
     * @param offset 字符的偏移量
     * @return 指定偏移量处的字符（布尔值）
     */
    boolean getBit(String key, long offset);






    /**
     * 检查指定的键是否存在于 Redis 中。
     *
     * @param key 要检查的键
     * @return 如果键存在返回 true，否则返回 false
     */
    boolean hasKey(String key);

    /**
     * 删除指定的键及其对应的值。
     *
     * @param key 要删除的键
     * @return 如果键被成功删除返回 true，否则返回 false
     */
    boolean delete(String key);

    /**
     * 设置指定键的过期时间。
     *
     * @param key 要设置过期时间的键
     * @param timeout 过期时间的值
     * @param unit 过期时间的时间单位，如 TimeUnit.SECONDS（秒）、TimeUnit.MINUTES（分钟）等
     * @return 如果成功设置过期时间返回 true，否则返回 false
     */
    boolean expire(String key, long timeout, TimeUnit unit);


    /**
     * 获取指定键剩余的过期时间。
     *
     * @param key 要获取过期时间的键
     * @param timeUnit 期望返回的时间单位，如 TimeUnit.SECONDS（秒）、TimeUnit.MINUTES（分钟）等
     * @return 剩余的过期时间，如果键不存在或没有设置过期时间，返回 -1
     */
    Long getExpire(String key, TimeUnit timeUnit);

    /**
     * 将指定键重命名为新的键。
     * 如果新键已存在，它将被覆盖。
     *
     * @param oldKey 要重命名的旧键
     * @param newKey 新的键名
     */
    void rename(String oldKey, String newKey);

    /**
     * 将指定键移动到指定的数据库索引。
     * Redis 支持多个数据库，通过数据库索引来区分。
     *
     * @param key 要移动的键
     * @param dbIndex 目标数据库索引
     * @return 如果键被成功移动返回 true，否则返回 false
     */
    boolean move(String key, int dbIndex);

    /**
     * 获取存储在指定键的值的类型。
     * 返回值是 Redis 内部表示的类型，如 "string"、"hash" 等。
     *
     * @param key 要获取类型的键
     * @return 值的类型，如果键不存在返回 null
     */
    String type(String key);

    /**
     * 获取所有匹配指定模式的键。
     * 模式支持通配符，如 "*" 匹配任意字符，"?" 匹配单个字符。
     * 注意：在大数据量情况下，此操作可能会影响性能。
     *
     * @param pattern 用于匹配键的模式
     * @return 匹配的键的集合
     */
    Set<String> keys(String pattern);
}
