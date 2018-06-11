package net.kang.main.mysql.service;

import net.kang.main.mysql.domain.CompanyPhoto;

import java.util.List;

public interface CompanyPhotoService {
    List<CompanyPhoto> findByCompId(String compId);
    CompanyPhoto findOne(long id);
}
