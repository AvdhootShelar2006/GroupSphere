package com.avdhoot.GroupSphereAPI.dto.answerQuery;

public record ReplyResponse(
        String content,
        String answeredByName,
        String queryTitle

) {
}
