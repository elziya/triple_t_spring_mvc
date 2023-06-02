package ru.kpfu.itis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.models.Tag;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagResponse {

    private Long id;

    private String name;

    public static TagResponse from(Tag tag) {
        return TagResponse.builder()
                .id(tag.getId())
                .name(tag.getTagName())
                .build();
    }

    public static Set<TagResponse> from(Set<Tag> tags) {
        return tags.stream().map(TagResponse::from).collect(Collectors.toSet());
    }
}
