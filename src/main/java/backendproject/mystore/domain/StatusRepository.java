package backendproject.mystore.domain;

import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository <Status, Long> {
    Status findByStatus(String status);
}
