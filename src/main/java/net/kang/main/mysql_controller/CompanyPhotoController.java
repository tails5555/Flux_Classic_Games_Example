package net.kang.main.mysql_controller;

import net.kang.main.mysql_domain.CompanyPhoto;
import net.kang.main.mysql_repository.CompanyPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("company_photo")
// 이는 MySQL와 MongoDB끼리 서로 연동이 가능한지에 대해 확인 차원해서 올린 예제입니다. 계속 진행하면서 채우겠습니다.
public class CompanyPhotoController {
    @Autowired CompanyPhotoRepository companyPhotoRepository;
    @GetMapping("all_photo")
    public List<CompanyPhoto> findAll(){
        return companyPhotoRepository.findAll();
    }

    @GetMapping("one_photo/view/{photoId}")
    public ResponseEntity<?> photoView(@PathVariable("photoId") long id) throws ServletException, IOException {
        CompanyPhoto photo = companyPhotoRepository.findById(id).orElse(null);
        if(photo!=null) {
            HttpHeaders headers = new HttpHeaders();
            switch(photo.getSuffix()) {
                case JPEG :
                    headers.setContentType(MediaType.IMAGE_JPEG);
                    break;
                case JPG :
                    headers.setContentType(MediaType.IMAGE_JPEG);
                    break;
                case PNG :
                    headers.setContentType(MediaType.IMAGE_PNG);
                    break;
                case GIF :
                    headers.setContentType(MediaType.IMAGE_GIF);
                    break;
            }
            return new ResponseEntity<byte[]>(photo.getData(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Found", HttpStatus.NO_CONTENT);
    }
}
