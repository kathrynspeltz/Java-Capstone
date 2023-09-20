package com.devmountain.bookclubApp.repositories;

import com.devmountain.bookclubApp.entities.Comment;
import com.devmountain.bookclubApp.entities.Group;
import com.devmountain.bookclubApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByGroupId(String id);

}
