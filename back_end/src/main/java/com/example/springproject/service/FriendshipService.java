package com.example.springproject.service;

import com.example.springproject.domain.Friendship;
import com.example.springproject.domain.Users;

import java.util.List;

public interface FriendshipService {
    void addFriend(Friendship friendship);

    void delete(Friendship friendship);

    Friendship get(Users user1, Users user2);

    List<Friendship> getFriendship(Users users);
}
