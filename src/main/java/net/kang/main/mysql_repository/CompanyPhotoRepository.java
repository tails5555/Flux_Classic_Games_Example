package net.kang.main.mysql_repository;

import net.kang.main.mysql_domain.CompanyPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyPhotoRepository extends JpaRepository<CompanyPhoto, Long> {
}
