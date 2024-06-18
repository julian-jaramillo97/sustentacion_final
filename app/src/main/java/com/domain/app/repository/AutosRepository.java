package com.domain.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.domain.app.model.Auto;

@Repository
public interface AutosRepository extends JpaRepository<Auto, Long> {
    List<Auto> findByModeloContaining(String modelo);
}
