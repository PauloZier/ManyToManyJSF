package net.ddns.zierservices.util;

/**
 *
 * @author zier
 */
@FunctionalInterface
public interface Action<T> {
    void execute(T t);
}
