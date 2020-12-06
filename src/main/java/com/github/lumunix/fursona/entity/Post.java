package com.github.lumunix.fursona.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "post")
public class Post {
    @Id
    private String id;

    private String content;
}
