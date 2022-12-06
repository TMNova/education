package ru.lanit.education.service.tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lanit.education.domain.entity.Tag;
import ru.lanit.education.domain.repository.TagRepository;
import ru.lanit.education.dto.tag.TagDto;
import ru.lanit.education.exception.TagException;
import ru.lanit.education.mapper.TagMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    private final TagMapper tagMapper;

    @Override
    public List<TagDto> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tagMapper.toDto(tags);
    }

    @Override
    public TagDto addTag(TagDto tagDto) {
        if (tagRepository.existsByName(tagDto.getName())) {
            log.error(TagException.TAG_EXIST_ERROR);
            throw new TagException(TagException.TAG_EXIST_ERROR);
        }

        Tag tag = tagMapper.toEntity(tagDto);
        Tag newTag = tagRepository.save(tag);

        return tagMapper.toDto(newTag);
    }

    @Override
    public TagDto editTag(TagDto tagDto) {
        Tag tag = tagRepository.findById(tagDto.getId())
                .orElseThrow(() -> {
                    log.error(TagException.TAG_NOT_EXIST_ERROR);
                    return new TagException(TagException.TAG_NOT_EXIST_ERROR);
                });
        tag.setName(tag.getName());

        tagRepository.save(tag);

        return tagMapper.toDto(tag);
    }

    @Override
    public void deleteTag(Long tagId) {
        if (!tagRepository.existsById(tagId)) {
            log.error(TagException.TAG_NOT_EXIST_ERROR);
            throw new TagException(TagException.TAG_NOT_EXIST_ERROR);
        }

        tagRepository.deleteById(tagId);
    }
}
