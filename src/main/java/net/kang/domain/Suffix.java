package net.kang.domain;

public enum Suffix {
    // 각 이미지 별 확장자를 ENUM을 통해 설정한다.
    JPEG("JPEG"),
    JPG("JPG"),
    PNG("PNG"),
    GIF("GIF");

    // Suffix에서는 오로지 파일 확장자만 저장이 된다.
    private String suffix;
    Suffix(String suffix){
        this.suffix=suffix;
    }
    // ENUM으로 설정 된 확장자를 가져온다.
    public String getSuffix(){
        return suffix;
    }
}
