package org.example.expert.data;

import org.example.expert.domain.comment.entity.Comment;

public class CommentFixture {
    public static final String DEFAULT_CONTENTS = "comment contents";

    public static Comment createComment() {
        return new Comment(DEFAULT_CONTENTS, UserFixture.createUserUserRole(), TodoFixture.createTodo());
    }
}
