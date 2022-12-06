package ru.lanit.education.service.tests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lanit.education.domain.entity.Test;
import ru.lanit.education.domain.entity.TestProgressInfo;
import ru.lanit.education.domain.entity.TestProgressResult;
import ru.lanit.education.domain.entity.User;
import ru.lanit.education.domain.repository.TestProgressInfoRepository;
import ru.lanit.education.domain.repository.TestRepository;
import ru.lanit.education.dto.test.TestDto;
import ru.lanit.education.dto.test.TestProgressInfoDto;
import ru.lanit.education.exception.TestException;
import ru.lanit.education.mapper.TestMapper;
import ru.lanit.education.mapper.TestProgressInfoMapper;
import ru.lanit.education.service.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final UserService userService;

    private final TestProgressInfoRepository testProgressInfoRepository;

    private final TestRepository testRepository;

    private final TestProgressInfoMapper testProgressInfoMapper;

    private final TestMapper testMapper;

    @Override
    public List<TestDto> getTests() {
        List<Test> tests = testRepository.findAll();
        return testMapper.toDto(tests);
    }

    @Override
    public TestDto getTestById(Long testId) {
        Test test = getTestEntity(testId);
        return testMapper.toDto(test);
    }

    @Override
    public List<TestProgressInfoDto> getUserProgressTests(Long userId) {
        List<TestProgressInfo> tests = testProgressInfoRepository.findAllByUser_Id(userId);
        return testProgressInfoMapper.toDto(tests);
    }

    @Override
    public TestProgressInfoDto getUserProgressTest(Long userId, Long testId) {
        TestProgressInfo test = testProgressInfoRepository.findByUser_IdAndTest_Id(userId, testId);
        return testProgressInfoMapper.toDto(test);
    }

    @Override
    public Test getTestEntity(Long testId) {
        return testRepository.findById(testId)
                .orElseThrow(() -> {
                    log.error(TestException.TEST_NOT_EXIST_ERROR);
                    return new TestException(TestException.TEST_NOT_EXIST_ERROR);
                });
    }

    @Override
    public Long addUserProgressTest(Long userId, Long testId) {
        Test test = getTestEntity(testId);

        User user = userService.getUserEntityById(userId);

        TestProgressInfo testProgressInfo = new TestProgressInfo();
        testProgressInfo.setTest(test);
        testProgressInfo.setUser(user);
        testProgressInfo.setProgress(0);
        testProgressInfo.setResult(TestProgressResult.NEW);

        TestProgressInfo savedTestProgressInfo = testProgressInfoRepository.save(testProgressInfo);

        return savedTestProgressInfo.getId();
    }

    @Override
    public void cancelUserTestProgress(Long userId, Long testId) {
        testProgressInfoRepository.deleteByUser_IdAndTest_Id(userId, testId);
    }
}
