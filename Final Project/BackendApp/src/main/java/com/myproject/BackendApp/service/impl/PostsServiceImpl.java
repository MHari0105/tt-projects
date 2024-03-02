package com.myproject.BackendApp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.BackendApp.exception.NotFoundException;
import com.myproject.BackendApp.model.Post;
import com.myproject.BackendApp.model.UserProfile;
import com.myproject.BackendApp.repository.PostsRepository;
import com.myproject.BackendApp.repository.UserProfileRepository;
import com.myproject.BackendApp.security.repository.UserRepository;
import com.myproject.BackendApp.security.user.User;
import com.myproject.BackendApp.service.PostsService;

@Service
public class PostsServiceImpl implements PostsService {
    
    @Autowired
    private PostsRepository postsRepo;

    @Autowired
    private UserProfileRepository userProfileRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public Post createPost(String profileId, Post post) throws NotFoundException {
        Optional<UserProfile> existing = userProfileRepo.findById(profileId);

        if (existing.isPresent()) {
            UserProfile existingProfile = existing.get();
            String userId = existingProfile.getUserId();

            Optional<User> existingUser = userRepo.findById(userId);

            if (existingUser.isPresent()) {
                User user = existingUser.get();

                post.setProfileId(profileId);
                post.setDateTime(new Date(System.currentTimeMillis()));
                post.setLowPoll(0);
                post.setModeratePoll(0);
                post.setDangerPoll(0);
                post.setTotalPollCounts(0);
                post.setPostStatus("pending");
                post.setPinCode(user.getPinCode());

                Post savedPost = postsRepo.save(post);
                
                existingProfile.getAllPosts().add(savedPost);
                existingProfile.setTotalPosts(existingProfile.getTotalPosts() + 1);

                userProfileRepo.save(existingProfile);

                return savedPost;
            }

            else throw new NotFoundException("User not found");

            
        }

        else throw new NotFoundException("User profile not found");

    }

    @Override
    public List<Post> getAllPosts(String userId) {
        Optional<UserProfile> existing = userProfileRepo.findById(userId);

        if (existing.isPresent()) {
            UserProfile userProfile = existing.get();
            userProfile.getTotalPosts();
        }
        return postsRepo.findAll();
    }

    @Override
    public Post userUpdatePost(String postId, Post post) throws NotFoundException {
        Optional<Post> existing = postsRepo.findById(postId);

        if (existing.isPresent()) {
            Post existingPost = new Post();
            existingPost.setDescription(post.getDescription());
            return postsRepo.save(existingPost);
        }

        else throw new NotFoundException("Post not found");
        
    }

    @Override
    public Post updatePostStatus(String postId, Post post) throws NotFoundException {
        Optional<Post> existing = postsRepo.findById(postId);

        if (existing.isPresent()) {
            Post existingPost = new Post();
            existingPost.setPostStatus(post.getPostStatus());

            return postsRepo.save(existingPost);
        }

        else throw new NotFoundException("Status update failed");
    }

    

    @Override
    public String deletePost(String profileId, String postId) {
        Optional<UserProfile> existing = userProfileRepo.findById(profileId);

        if (existing.isPresent()) {
            UserProfile existingProfile = existing.get();
            List<Post> userPosts = existingProfile.getAllPosts();

            Optional<Post> postToDelete = userPosts.stream().filter(
                post -> post.getId().equals(postId)
            ).findFirst();

            if (postToDelete.isPresent()) {
                postsRepo.deleteById(postId);

                userPosts.remove(postToDelete.get());
                existingProfile.setAllPosts(userPosts);
                existingProfile.setTotalPosts(existingProfile.getTotalPosts() - 1);

                userProfileRepo.save(existingProfile);

                return "Post deleted";
            }
            
            else return "Post not found";
        }

        else return "User not found";
    }
    
}
