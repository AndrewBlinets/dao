package by.ipps.dao.service.base;

import by.ipps.dao.entity.BaseEntity;
import by.ipps.dao.repository.BaseEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

//@Transactional
public class BaseEntityServiceImpl<T extends BaseEntity, R extends BaseEntityRepository<T>> implements BaseEntityService<T> {

    private final R repository;

    public BaseEntityServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public T create(T t) {
        return repository.save(t);
    }

    @Override
    public Page<T> findPagingRecords(Pageable page) {
        return repository.findByStatusR(page, "A");
    }


    @Override
    public T findById(Long id) {
        return repository.findByIdAndStatusR(id, "A").orElse(null);
    }

    @Override
    public T update(T t) {
        T tFromBD = repository.findById(t.getId()).orElse(null);
        if(tFromBD == null)
            return null;
        else {
            t.setDateChangeStatusR(tFromBD.getDateChangeStatusR());
            t.setDti(tFromBD.getDti());
            return repository.save(t);
        }
    }

    @Override
    public boolean delete(T t) {
        try {
            t.setStatusR("D");
            t.setDateChangeStatusR(new Date());
            repository.save(t);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<T> findAll() {
        return repository.findByStatusR("A");
    }
}
