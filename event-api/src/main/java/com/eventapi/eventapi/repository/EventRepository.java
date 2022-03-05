package com.eventapi.eventapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eventapi.eventapi.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
