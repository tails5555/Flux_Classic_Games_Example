package net.kang.main.mysql.repository;

import net.kang.main.mysql.domain.CompanyPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyPhotoRepository extends JpaRepository<CompanyPhoto, Long> {
    List<CompanyPhoto> findByCompId(String compId);
}
