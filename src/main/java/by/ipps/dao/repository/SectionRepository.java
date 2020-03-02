package by.ipps.dao.repository;

import by.ipps.dao.entity.Section;

public interface SectionRepository extends BaseEntityRepository<Section> {

  //  @Query(value = "select s from Section s where s.statusR = 'A' and s.page.id = :id")
  //  List<Section> findSectionByIdPage(@Param("id") long id);
}
