package by.ipps.dao.controller;

import by.ipps.dao.entity.FileManager;
import by.ipps.dao.service.FileManagerService;
import by.ipps.dao.utils.view.ViewFile;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/file")
public class FileManagerController {

    private FileManagerService fileManagerService;

    public FileManagerController(FileManagerService fileManagerService) {
        this.fileManagerService = fileManagerService;
    }

    @PostMapping
    @ResponseBody
    public List<FileManager> saveImage(@RequestBody List<FileManager> fileManagers) {
        return fileManagerService.save(fileManagers);
    }

    @JsonView(ViewFile.BaseClass.class)
    @GetMapping(value = "/{id}")
    public ResponseEntity<FileManager> getImage(@PathVariable long id) {
        FileManager fileManager = fileManagerService.findById(id);
        return new ResponseEntity<>(fileManager, fileManager != null ? HttpStatus.OK :HttpStatus.NOT_FOUND);
    }
}
