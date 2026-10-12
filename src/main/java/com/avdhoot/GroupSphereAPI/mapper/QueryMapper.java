package com.avdhoot.GroupSphereAPI.mapper;


import com.avdhoot.GroupSphereAPI.dto.answerQuery.ReplyResponse;
import com.avdhoot.GroupSphereAPI.dto.queryDto.CreateQueryResponse;
import com.avdhoot.GroupSphereAPI.entity.Reply;
import com.avdhoot.GroupSphereAPI.entity.Query;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QueryMapper {

    @Mapping(source = "postedBy.name", target = "postedByName")
    @Mapping(source = "group.id", target = "groupId")
    CreateQueryResponse toCreateQueryResponse(Query query);


//    @Mapping(source = "group.id", target = "groupId")
//    @Mapping(source = "postedBy.name", target = "postedByName")
//    List<CreateQueryResponse> toCreateQueryResponses(List<Query> groupQueries);


    @Mapping(source = "user.name", target = "answeredByName")
    @Mapping(source = "query.title", target = "queryTitle")
    ReplyResponse toAnswerQueryResponse(Reply reply);

//    List<ReplyResponse> toAnswerQueryResponses(List<Reply> answerQueries);
}
