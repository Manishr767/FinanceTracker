import java.util.List;

public interface Repository<T> {
    void save(T items);
    List<T> findAll();
}
