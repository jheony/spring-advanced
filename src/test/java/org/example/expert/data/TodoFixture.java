package org.example.expert.data;

import org.example.expert.domain.todo.entity.Todo;

public class TodoFixture {
    public static final String DEFAULT_TITLE = "title";
    public static final String DEFAULT_CONTENTS = "todo contents";
    public static final String DEFAULT_WEATHER = "weather";

    public static Todo createTodo(){
        return new Todo(DEFAULT_TITLE, DEFAULT_CONTENTS, DEFAULT_WEATHER, UserFixture.createUserAdminRole());
    }
}
