package com.nexflix.clone.com.nexflix.clone.dao;

import com.nexflix.clone.com.nexflix.clone.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
