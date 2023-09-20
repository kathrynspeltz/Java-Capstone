package com.devmountain.bookclubApp.services;

import com.devmountain.bookclubApp.dtos.BookDto;
import com.devmountain.bookclubApp.dtos.GroupDto;
import com.devmountain.bookclubApp.dtos.UserDto;
import com.devmountain.bookclubApp.entities.Book;
import com.devmountain.bookclubApp.entities.Group;
import com.devmountain.bookclubApp.entities.User;
import com.devmountain.bookclubApp.repositories.GroupRepository;
import com.devmountain.bookclubApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupServiceImpl {
    @Autowired
    private GroupRepository groupRepository;


    @Override
    @Transactional
    public List<String> addGroup(GroupDto groupDto) {
        List<String> response = new ArrayList<>();
        Group group = new Group(groupDto);
        groupRepository.saveAndFlush(group);
        response.add("Group Membership added");
        return response;
    }

    @Override
    public Optional<GroupDto> geGroupById(Long groupId) {
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if (groupOptional.isPresent()){
            return Optional.of(new BookDto(groupOptional.get()));
        }
        return Optional.empty();

    }
}
