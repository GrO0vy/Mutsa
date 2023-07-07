package com.example.file;

import com.example.file.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Slf4j
@RestController
public class FileController {

    @RequestMapping(value = "/multipart", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto multipart(@RequestParam("name") String name,
                                 @RequestParam(value = "photo") MultipartFile multipartFile) throws IOException {


        // media 디렉토리 생성
        Files.createDirectories(Path.of("media"));

        // 파일 이름 정하기 1 - 생성 날짜를 이용해서 정하기
        LocalDateTime now = LocalDateTime.now();
        log.info(now.toString());

        Path uploadTo = Path.of(String.format("media/%s.txt",now).replace(":", " "));
        multipartFile.transferTo(uploadTo);
        // 파일 업로드 - 1
//        Path uploadTo = Path.of("filename.txt");
//        multipartFile.transferTo(uploadTo);

        // 파일 업로드 - 2
//        File file = new File("./filename.txt");
//        try(OutputStream outputStream =new FileOutputStream(file)){
//            byte[] fileBytes = multipartFile.getBytes();
//            outputStream.write(fileBytes);
//        }


        ResponseDto response = new ResponseDto();
        response.setMessage("success");

        return response;
    }
}
