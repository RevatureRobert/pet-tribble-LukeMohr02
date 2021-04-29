package dao;

public interface GenericDAO<T, K> {

    T[] getAll();

    T get(K id);

    void insert(T t);

    void update(K id, String column, String value);

    void delete(K id);

}
