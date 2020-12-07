package com.example.springproject.api;

import com.example.springproject.domain.Friendship;
import com.example.springproject.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    @Query("select f from Friendship f where f.user1 = ?1 or f.user2 = ?1")
    List<Friendship> findByUser(Users users);

    @Query("select f from Friendship f where (f.user1 = ?1 and f.user2 = ?2) or (f.user1 = ?2 and f.user2 = ?1)")
    Friendship findByUserAndUser(Users user1, Users user2);
}
