package com.avdhoot.GroupSphereAPI.service;

import com.avdhoot.GroupSphereAPI.dto.answerQuery.ReplyRequest;
import com.avdhoot.GroupSphereAPI.dto.answerQuery.ReplyResponse;
import com.avdhoot.GroupSphereAPI.entity.*;
import com.avdhoot.GroupSphereAPI.exception.EntityAndRelationshipsFinder;
import com.avdhoot.GroupSphereAPI.exception.ResourceNotFoundException;
import com.avdhoot.GroupSphereAPI.mapper.QueryMapper;
import com.avdhoot.GroupSphereAPI.repository.groupRepository.GroupMembershipRepository;
import com.avdhoot.GroupSphereAPI.repository.queryRepository.ReplyRepository;
import com.avdhoot.GroupSphereAPI.repository.queryRepository.GroupQueryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final QueryMapper queryMapper;
    private final GroupMembershipRepository groupMembershipRepository;
    private final EntityAndRelationshipsFinder entityAndRelationshipsFinder;
    private final GroupQueryRepository groupQueryRepository;

    @Transactional
    public ReplyResponse createReply(int groupId, int questionQueryId, ReplyRequest request, int replyCreatorId) {
        Group group = entityAndRelationshipsFinder.getGroupOrThrow(groupId);
        Query query = entityAndRelationshipsFinder.getQueryInGroupOrThrow(groupId,questionQueryId);
        User user = entityAndRelationshipsFinder.getUserOrThrow(replyCreatorId);

        if(!groupMembershipRepository.existsByGroupAndUser(group, user)) {
            throw  new ResourceNotFoundException(" User with ID:" + user.getId() + " does not belong to "+ group.getGroupName()+" group. Please join the Group before posting Reply");
        }

        Reply reply = Reply
                .builder()
                .content(request.content())
                .user(user)
                .query(query)
                .group(group).createdAt(LocalDate.now())
                .build();
        replyRepository.save(reply);
        return queryMapper.toAnswerQueryResponse(reply);
    }

    public Page<ReplyResponse> getAllSolutions(Pageable pageable, int groupId, int queryId) {
        Group group = entityAndRelationshipsFinder.getGroupOrThrow(groupId);
        Query query = entityAndRelationshipsFinder.getQueryInGroupOrThrow(groupId, queryId);

        Page<Reply> replies = replyRepository.findByGroup_IdAndQuery_QueryId(
                group.getId(),
                query.getQueryId(),
                pageable);

        return replies.map(queryMapper::toAnswerQueryResponse);
    }

    @Transactional
    public void deleteReply(Integer groupId, Integer queryId, Integer replyId) {
        Reply reply = entityAndRelationshipsFinder.getReplyInQueryAndGroupOrThrow(groupId, queryId, replyId);
        replyRepository.delete(reply);
    }
}
