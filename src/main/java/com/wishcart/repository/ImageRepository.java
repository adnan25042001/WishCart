package com.wishcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishcart.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
