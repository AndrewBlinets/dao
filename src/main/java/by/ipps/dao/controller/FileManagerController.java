package by.ipps.dao.controller;

import by.ipps.dao.entity.FileManager;
import by.ipps.dao.entity.Logger;
import by.ipps.dao.entity.UserPortal;
import by.ipps.dao.service.FileManagerService;
import by.ipps.dao.service.LoggerService;
import by.ipps.dao.utils.view.ViewFile;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileManagerController {

  private FileManagerService fileManagerService;
  private LoggerService loggerService;

  public FileManagerController(FileManagerService fileManagerService, LoggerService loggerService) {
    this.fileManagerService = fileManagerService;
    this.loggerService = loggerService;
  }

  @PostMapping
  @ResponseBody
  public FileManager saveImage(
      @RequestBody FileManager fileManagers, @RequestParam(value = "user") UserPortal userPortal) {
    fileManagers = fileManagerService.save(fileManagers);
    if (fileManagers != null) {
      loggerService.create(
          new Logger(
              userPortal, String.valueOf(fileManagers.getClass()), fileManagers.getId(), "CREATE"));
    }
    return fileManagers;
  }

  @JsonView(ViewFile.BaseClass.class)
  @GetMapping(value = "/{id}")
  public ResponseEntity<FileManager> getImage(@PathVariable long id) {
    log.info("getImage " + id);
    FileManager fileManager = fileManagerService.findById(id);
    return new ResponseEntity<>(
        fileManager, fileManager != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
  }
}
