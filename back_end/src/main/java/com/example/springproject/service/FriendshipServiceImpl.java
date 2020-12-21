package com.example.springproject.service;

import com.example.springproject.api.FriendshipRepository;
import com.example.springproject.domain.Friendship;
import com.example.springproject.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {
    @Autowired
    private FriendshipRepository friendshipRepository;

    @Override
    public void addFriend(Friendship friendship) {
        friendship.setAddTime(new Date(System.currentTimeMillis()));
        friendshipRepository.save(friendship);
    }

    @Override
    public void delete(Friendship friendship) {
        friendshipRepository.delete(friendship);
    }

    @Override
    public Friendship get(Users user1, Users user2) {
        return friendshipRepository.findByUserAndUser(user1, user2);
    }

    @Override
    public List<Friendship> getFriendship(Users users) {
        return friendshipRepository.findByUser(users);
    }
}
