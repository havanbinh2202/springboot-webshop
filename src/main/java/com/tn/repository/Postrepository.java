package com.tn.repository;

import com.tn.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Postrepository extends JpaRepository<Post, Integer> {
}
