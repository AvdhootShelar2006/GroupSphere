package com.avdhoot.GroupSphereAPI.exception;

import com.avdhoot.GroupSphereAPI.entity.Query;
import com.avdhoot.GroupSphereAPI.entity.Reply;
import com.avdhoot.GroupSphereAPI.entity.User;
import com.avdhoot.GroupSphereAPI.entity.Group;
import com.avdhoot.GroupSphereAPI.repository.UserRepository;
import com.avdhoot.GroupSphereAPI.repository.groupRepository.GroupRepository;
import com.avdhoot.GroupSphereAPI.repository.queryRepository.GroupQueryRepository;
import com.avdhoot.GroupSphereAPI.repository.queryRepository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityAndRelationshipsFinder {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupQueryRepository groupQueryRepository;
    private final ReplyRepository replyRepository;

    public Group getGroupOrThrow(int groupId){
        return groupRepository.findById(groupId).orElseThrow(()-> new ResourceNotFoundException("Group with ID: " + groupId + " not found."));
    }

    public User getUserOrThrow(int userId){
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with ID: " + userId + " not found."));
    }

    public Query getQueryInGroupOrThrow(int groupId, int queryId) {
        Query query = groupQueryRepository.findById(queryId)
                .orElseThrow(() -> new ResourceNotFoundException("Query with ID: " + queryId + " not found"));

        if (!query.getGroup().getId().equals(groupId)) {
            throw new ResourceNotFoundException("Group with ID: " + groupId + " does not contain this query");
        }
        return query;
    }

    public Reply getReplyInQueryAndGroupOrThrow(Integer groupId, Integer queryId, Integer replyId) {
        Reply reply = replyRepository.findByReplyIdAndQuery_QueryIdAndGroup_Id(replyId, queryId, groupId).orElseThrow(() -> new ResourceNotFoundException("Reply with ID: " + replyId + " does not belong to this query/group"));
        return reply;
    }
}
