package net.kang.main.mysql_domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Lob;
import javax.persistence.Basic;
import javax.persistence.FetchType;
import java.time.LocalDateTime;

@Data
@Entity
public class CompanyPhoto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long id;

    // 실제로 저장 되는 사진 이름. 그렇지만 보안을 위해 암호롸를 적용시켜야 한다.
    @Column(name="photoName")
    String name;

    // 사진 확장자
    @Enumerated(EnumType.STRING)
    @Column(name="photoSuffix")
    Suffix suffix;

    // 사진 너비
    @Column(name="photoWidth")
    int width;

    // 사진 높이
    @Column(name="photoHeight")
    int height;

    // 사진 용량. 단위는 Byte.
    @Column(name="photoSize")
    int size;

    // 사진 업로드 시간. 업로드 시간은 현재 시각을 기본 값으로 설정한다.
    LocalDateTime uploadTime;

    // 사진 Data의 실제 BLOB(Binary LOB).
    @JsonIgnore
    @Basic(fetch=FetchType.LAZY)
    @Lob
    @Column(name="photoData")
    byte[] data;

    // MongoDB에서 실제로 쓰이는 Console(게임 기기) ID.
    @Column(name="companyId")
    String compId;
}
