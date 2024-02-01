package org.example.phoneduck.repository;

import org.example.phoneduck.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, Long> {

}
