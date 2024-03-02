package com.myproject.BackendApp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.BackendApp.model.Poll;
import com.myproject.BackendApp.model.Post;
import com.myproject.BackendApp.repository.PollRepository;
import com.myproject.BackendApp.repository.PostsRepository;
import com.myproject.BackendApp.service.PollService;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepo;

    @Autowired
    private PostsRepository postRepo;

    @Override
    public String addPoll(String profileId, String postId, String pollValue) {

        if (!isValidPollType(pollValue)) {
            return "Invalid poll value";
        }

        Optional<Post> postOpt = postRepo.findById(postId);

        if (postOpt.isPresent()) {
            Post post = postOpt.get();

            switch (pollValue) {
                case "low":
                    post.setLowPoll(post.getLowPoll() + 1);
                    break;
                case "moderate":
                    post.setModeratePoll(post.getModeratePoll() + 1);
                    break;
                case "high":
                    post.setDangerPoll(post.getDangerPoll() + 1);
                    break;
                default:
                    return "Invalid poll Value";
            }

            post.setTotalPollCounts(post.getTotalPollCounts() + 1);

            postRepo.save(post);

            Poll poll = new Poll();
            poll.setProfileId(profileId);
            poll.setPostId(postId);
            poll.setPollvalue(pollValue);

            pollRepo.save(poll);

            return "Poll added";
        }

        else return "Post not found";

    }

    private boolean isValidPollType(String pollValue) {
        return pollValue.equalsIgnoreCase("low") || pollValue.equalsIgnoreCase("moderate") || pollValue.equalsIgnoreCase("high");
    }
    
    
}
