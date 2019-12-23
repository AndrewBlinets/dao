package by.ipps.dao.service;

import by.ipps.dao.entity.FileManager;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileManagerService extends BaseEntityService<FileManager> {
    List<FileManager> save(List<FileManager> paths);
}
