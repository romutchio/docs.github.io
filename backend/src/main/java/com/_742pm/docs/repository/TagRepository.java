package com._742pm.docs.repository;

import com._742pm.docs.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
    List<Tag> findTagsByUserId(UUID user_id);
    List<Tag> findTagsByName(String name);
}
