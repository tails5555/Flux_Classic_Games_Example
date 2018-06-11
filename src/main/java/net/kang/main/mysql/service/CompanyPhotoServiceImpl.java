package net.kang.main.mysql.service;

import net.kang.main.mysql.domain.CompanyPhoto;
import net.kang.main.mysql.repository.CompanyPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyPhotoServiceImpl implements CompanyPhotoService{
    @Autowired CompanyPhotoRepository companyPhotoRepository;
    @Override
    public List<CompanyPhoto> findByCompId(String compId){
        return companyPhotoRepository.findByCompId(compId);
    }
    @Override
    public CompanyPhoto findOne(long id){
        CompanyPhoto companyPhoto = companyPhotoRepository.findById(id).orElse(new CompanyPhoto());
        return companyPhoto;
    }
}
