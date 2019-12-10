package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.Teg;
import by.ipps.dao.service.TegService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teg")
public class TegController extends BaseEntityAbstractController<Teg, TegService>
        implements BaseEntityController<Teg> {

    protected TegController(TegService tegService) {
        super(tegService);
    }


}
