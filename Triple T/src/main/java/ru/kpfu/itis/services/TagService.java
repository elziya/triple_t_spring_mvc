package ru.kpfu.itis.services;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.itis.dto.request.TagRequest;
import ru.kpfu.itis.dto.response.TagResponse;

import java.util.Set;

public interface TagService {

    Set<TagResponse> getTagsOfAccount(UserDetails userDetails);

    Set<TagResponse> addTag(TagRequest tagRequest, UserDetails userDetails);
}
