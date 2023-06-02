package ru.kpfu.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dto.request.TagRequest;
import ru.kpfu.itis.dto.response.TagResponse;
import ru.kpfu.itis.exceptions.TripleTDuplicateTagException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.models.Tag;
import ru.kpfu.itis.repositories.TagRepository;
import ru.kpfu.itis.security.details.AccountUserDetails;
import ru.kpfu.itis.services.TagService;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Set<TagResponse> getTagsOfAccount(UserDetails userDetails) {
        Account account = ((AccountUserDetails)userDetails).getAccount();
        return TagResponse.from(tagRepository.findAllByAccount_Id(account.getId()));
    }

    @Override
    public Set<TagResponse> addTag(TagRequest tagRequest, UserDetails userDetails) {

        if (tagRepository.findByTagNameAndAccount_Email(tagRequest.getName(), userDetails.getUsername())
                .isPresent()) {
            throw new TripleTDuplicateTagException();
        }

        Account account = ((AccountUserDetails)userDetails).getAccount();
        Tag tag = Tag.builder()
                .tagName(tagRequest.getName())
                .account(account)
                .build();

        tagRepository.save(tag);
        return TagResponse.from(tagRepository.findAllByAccount_Id(account.getId()));
    }
}
