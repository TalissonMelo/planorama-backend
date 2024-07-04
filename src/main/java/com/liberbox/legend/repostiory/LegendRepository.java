package com.liberbox.legend.repostiory;

import com.liberbox.legend.domain.Legend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LegendRepository extends JpaRepository<Legend, String> {
    @Query("SELECT l FROM Legend l WHERE l.ownerId = :userId")
    List<Legend> findAllByOwnerId(String userId);
}
