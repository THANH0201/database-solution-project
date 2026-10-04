package com.thanh.project.repository;

import com.thanh.project.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactRepo extends JpaRepository<Contact, Integer> {} 
