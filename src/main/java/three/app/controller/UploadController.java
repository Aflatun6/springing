package three.app.controller;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Controller
@RequestMapping("/file")
public class UploadController {
    @GetMapping
    public String getHandle() {
        return "upload-file";
    }

    @SneakyThrows
    @ResponseBody
    @PostMapping
    public String postHandle(@RequestParam("filecontents") MultipartFile file) {
        byte[] bytes = file.getBytes();
        String content = new String(bytes);
        return String.format("file :%s  ;  name %s", content, file.getOriginalFilename());
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> download(@PathVariable("filename") String filename) {
        Resource classPathResource = new ClassPathResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", classPathResource.getFilename())
                ).body(classPathResource);
    }


}
