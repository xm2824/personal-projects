package pgdp.stream;

@FunctionalInterface
public interface ThrowingPredicate<T> {
    boolean test(T t) throws Exception;
}
