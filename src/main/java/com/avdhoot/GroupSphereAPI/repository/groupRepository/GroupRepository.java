package com.avdhoot.GroupSphereAPI.repository.groupRepository;

import com.avdhoot.GroupSphereAPI.entity.Group;
import com.avdhoot.GroupSphereAPI.entity.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Query> findById(Group group);

    @org.springframework.data.jpa.repository.Query("""
    select g from Group g
    where lower(g.groupName)   like :pattern
       or lower(g.subject)     like :pattern
       or lower(g.field)       like :pattern
       or lower(g.description) like :pattern
       or lower(g.tags)        like :pattern
    """)
    Page<Group> searchUsingKeyword(@Param("pattern") String pattern, Pageable pageable);

    boolean existsByGroupName(String groupName);

    Page<Group> findByIsEnableFalse(Pageable pageable);
}
