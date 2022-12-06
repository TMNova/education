package ru.lanit.education.service.tag;

import ru.lanit.education.dto.tag.TagDto;

import java.util.List;

/**
 * Сервис для взаимодействия с тегами
 */
public interface TagService {

    /**
     * Метод для получения списка имеющихся тегов
     *
     * @return список {@link TagDto}
     */
    List<TagDto> getAllTags();

    /**
     * Метод для добавления нового тега
     *
     * @param tagDto ДТО, содержащий данные по тегу
     * @return {@link TagDto} ДТО, содержащий данные по созданному тегу
     */
    TagDto addTag(TagDto tagDto);

    /**
     * Метод для редактирования тега
     *
     * @param tagDto ДТО, содержащий данные по тегу
     * @return {@link TagDto} ДТО, содержащий данные по отредактированному тегу
     */
    TagDto editTag(TagDto tagDto);

    /**
     * Метод для удаления тега
     *
     * @param tagId ID тега, который необходимо удалить
     */
    void deleteTag(Long tagId);
}
