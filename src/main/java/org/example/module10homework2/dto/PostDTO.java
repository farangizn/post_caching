package org.example.module10homework2.dto;

import lombok.Value;
import org.example.module10homework2.entity.Attachment;

import java.io.Serializable;

/**
 * DTO for {@link org.example.module10homework2.entity.Post}
 */
@Value
public class PostDTO implements Serializable {
    String title;
    String body;
    Attachment photo;
}