package ru.lanit.education.service.theme;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lanit.education.domain.entity.Test;
import ru.lanit.education.domain.repository.ThemeRepository;
import ru.lanit.education.dto.theme.ThemeDto;
import ru.lanit.education.dto.theme.ThemeReferenceDto;
import ru.lanit.education.exception.ThemeException;
import ru.lanit.education.mapper.ThemeMapper;
import ru.lanit.education.service.tests.TestService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ThemeServiceImpl implements ThemeService {

    private final TestService testService;

    private final ThemeRepository themeRepository;

    private final ThemeMapper themeMapper;

    @Override
    public List<ThemeDto> getThemesByTestId(Long testId) {
        List<ru.lanit.education.domain.entity.Theme> themes = themeRepository.findIdThemeDescriptionByTestId(testId);
        if (themes.isEmpty()) {
            log.error(ThemeException.THEME_NOT_EXIST_ERROR);
            throw new ThemeException(ThemeException.THEME_NOT_EXIST_ERROR);
        }

        return themeMapper.toDto(themes);
    }

    @Override
    public ThemeDto getThemeByTestIdAndThemeId(Long testId, Long themeId) {
        Optional<ru.lanit.education.domain.entity.Theme> theme = themeRepository.findByIdAndTest_Id(themeId, testId);
        if (theme.isEmpty()) {
            log.error(ThemeException.THEME_NOT_EXIST_ERROR);
            throw new ThemeException(ThemeException.THEME_NOT_EXIST_ERROR);
        }

        ThemeDto themeDto = themeMapper.toDto(theme.get());

        themeRepository.findIdAndThemeById(testId - 1L)
                .ifPresent(entity -> themeDto.setPrev(
                        ThemeReferenceDto.builder()
                            .id(entity.getId())
                            .theme(entity.getTheme())
                            .build()
                        )
                );

        themeRepository.findIdAndThemeById(testId + 1L)
                .ifPresent(entity -> themeDto.setNext(
                        ThemeReferenceDto.builder()
                                .id(entity.getId())
                                .theme(entity.getTheme())
                                .build()
                    )
                );

        return themeDto;
    }

    @Override
    public ThemeDto editTheme(ThemeDto themeDto) {
        ru.lanit.education.domain.entity.Theme theme = themeRepository.findById(themeDto.getId())
                .map(entity -> {
                    entity.setNumber(themeDto.getNumber());
                    entity.setTheme(themeDto.getTheme());
                    entity.setDescription(themeDto.getDescription());
                    entity.setContent(themeDto.getContent());
                    return entity;
                })
                .orElseThrow(() -> {
                    log.error(ThemeException.THEME_NOT_EXIST_ERROR);
                    return new ThemeException(ThemeException.THEME_NOT_EXIST_ERROR);
                });

        return themeMapper.toDto(theme);
    }

    @Override
    public ThemeDto addTheme(ThemeDto themeDto, Long testId) {
        Test test = testService.getTestEntity(testId);

        ru.lanit.education.domain.entity.Theme theme = themeMapper.toEntity(themeDto);
        theme.setTest(test);

        ru.lanit.education.domain.entity.Theme savedTheme = themeRepository.save(theme);
        return themeMapper.toDto(savedTheme);
    }

    @Override
    public void deleteTheme(Long themeId) {
        if (!themeRepository.existsById(themeId)) {
            log.error(ThemeException.THEME_NOT_EXIST_ERROR);
            throw new ThemeException(ThemeException.THEME_NOT_EXIST_ERROR);
        }

        themeRepository.deleteById(themeId);
    }
}
