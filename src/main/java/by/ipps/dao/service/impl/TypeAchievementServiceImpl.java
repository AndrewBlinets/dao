package by.ipps.dao.service.impl;

import by.ipps.dao.entity.TypeAchievement;
import by.ipps.dao.repository.TypeAchievementRepository;
import by.ipps.dao.service.TypeAchievementService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TypeAchievementServiceImpl
    extends BaseEntityServiceImpl<TypeAchievement, TypeAchievementRepository>
    implements TypeAchievementService {

  public TypeAchievementServiceImpl(TypeAchievementRepository repository) {
    super(repository);
  }
}
