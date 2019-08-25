package com.watson.annababy.service.aa;

import com.watson.annababy.model.aa.ArtifactEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtifactRepository extends CrudRepository<ArtifactEntity, Long> {
}
