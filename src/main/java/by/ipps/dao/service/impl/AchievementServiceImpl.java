package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Achievement;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Section;
import by.ipps.dao.repository.AchievementRepository;
import by.ipps.dao.service.AchievementService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AchievementServiceImpl extends BaseEntityServiceImpl<Achievement, AchievementRepository>
        implements AchievementService {
    public AchievementServiceImpl(AchievementRepository repository) {
        super(repository);
    }
}
