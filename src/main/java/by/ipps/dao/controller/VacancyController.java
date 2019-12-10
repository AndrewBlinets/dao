package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.Vacancy;
import by.ipps.dao.service.VacancyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacancy")
public class VacancyController extends BaseEntityAbstractController<Vacancy, VacancyService>
        implements BaseEntityController<Vacancy> {
    protected VacancyController(VacancyService vacancyService) {
        super(vacancyService);
    }
}
