package by.ipps.dao.service.impl;

import by.ipps.dao.entity.FileManager;
import by.ipps.dao.repository.FileManagerRepository;
import by.ipps.dao.service.FileManagerService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FileManagerServiceImpl extends BaseEntityServiceImpl<FileManager, FileManagerRepository>
        implements FileManagerService {
    public FileManagerServiceImpl(FileManagerRepository repository) {
        super(repository);
    }
}
