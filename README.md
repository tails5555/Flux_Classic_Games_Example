<h1>Classic Games REST API Example<br/>
<sub>With Spring Web Flux</sub></h1>

Web MVC 패턴을 벗어나서 이제는 Reactive한 Web Flux의 시대를 맞아보겠습니다.

그 전에 Web Flux 패턴에 대해서도 인지할 필요가 있으니 KangBakSa Note에 이 Tutorial를 읽어보고 요약해서 올리겠습니다.

## Issues
- Web Flux의 기본 패턴을 적용한 REST API를 연습할 수 있습니다.
- Reactive MongoDB에 대해 적용할 수 있습니다.
- Java 8에서 제공하는 Lambda Expression에 대해서 연습할 수 있습니다.

## Reactive Programming Basic Issues

Flux 는 반응적이고, Non-Blocking을 제공하는 시스템을 구축하는데 특히 적은 Event-Driven으로 처리하여 문맥 교환을 최소화한 점을 토대로 최대의 효율을 보여줍니다.

Flux 메카니즘은 효율적으로 동작하는 고성능 Web Application 개발과 서비스 간 호출이 많은 Micro Service Architecture 에 적합합니다.

Flux 메카니즘을 적용할 때 필요한 Reactive Programming을 철저히 이용하기 위하여 객체 지향 프로그래밍에다가 약간의 함수 프로그래밍을 염두하고 있어야 합니다.

**Reactive Programming 의 장점**
- 다양한 비동기 처리 상황에 대처하기 좋습니다. 대표적인 사례는 다음과 같습니다.
    - 순차적으로 실행되는 비동기 통신을 진행할 때.
    - 복수의 비동기 처리 완료 하고, 결과 값을 합산할 때. 
- 콜백 Hell 해결에 도움을 줍니다.
    - 그러나 콜백 Hell은 함수형 프로그래밍에서도 여전히 겪고 있는 어려운 과제입니다. 
- Thread 관리가 쉬워집니다.
- 코드가 무슨 역할을 하는지 짐작할 수 있을 정도로 간결해집니다.

**Reactive Programming 의 단점**
- 높은 진입장벽. 처음 접하는 개발자들에겐 어렵습니다.
    - 처음에 Reactive MongoDB를 이용한 CRUD 작업도 <small>솔직히 힘들었습니다...</small>
    - 다양한 오퍼레이션 제공으로 인한 러닝커브
    - 멀티스레드와 함수형 프로그래밍 개념이해 필수
- 코드가 오히려 복잡해 질 수 있습니다.(**써야 할 때를 구분**. 안 그러면 조삼모사와 같은 결과를 초래합니다.)

## What Is Reactive MongoDB?

Reactive MongoDB는 평소에 쓰는 MongoDB와 전혀 다릅니다.

Reactive MongoDB는 설정하는 작업부터 달라지는데, Web Flux에서 데이터를 가져와 보여주기 위해 Mono와 Flux를 이용해야 합니다.

그렇지만 Spring Data MongoDB는 Optional<T>와 List<T>로 데이터를 반환해야 합니다.

그래서 이를 적용하기 위하여 Reactive MongoDB를 이용해야 하는데 각 운영체제 환경에 MongoDB가 설치되어 있다면 그대로 적용할 수는 있지만 실제 설정파일 작성하는 것부터 힘드니 이 소스 코드 자료에 있는 ReactiveMongoConfig.java를 꼭 읽어보시길 바랍니다.

하지만 MongoDB처럼 DBRef 기능을 그대로 이용이 불가능합니다.

이러한 한계에 대해서 우선 Embedded Document를 이용한 객체 불러오는 방법으로 작성하였습니다.

## Maven pom.xml

`pom.xml`를 기반으로 Maven Dependency를 구성하여 Update Maven은 필수입니다.

```
<dependencies>
    <!-- 1. Spring Data Reactive MongoDB Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb-reactive</artifactId>
    </dependency>
    <!-- 2. Spring Web Flux Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>
    <!-- 3. Lombok Project -->
    <!-- Lombok은 각 인스턴스들에 대해서 getter, setter, toString, equals, hashCode 등의 구현을 자동으로 해 주는 프로젝트이다. -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <!-- 4. Tomcat Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <scope>provided</scope>
    </dependency>
    <!-- 5. Spring Test Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <!-- 6. Reactive Test -->
    <dependency>
        <groupId>io.projectreactor</groupId>
        <artifactId>reactor-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## MongoDB Database References

![reactive_mongo_classic_games](/image/reactive_mongo_classic_games.png)

데이터베이스에 있는 요소는 다음과 같은 특성을 가집니다.

- Company : 게임 제작 회사 혹은 게임기(콘솔) 제작 회사 정보입니다.
- Console : 게임기(콘솔) 정보. 고전 게임은 대부분 게임기로 작동하여 게임 별 기종을 파악하기 위해 이를 추가하였습니다.
- Genre : 게임 장르.
- Game : 고전 게임. 내부에 게임기 정보, 장르 정보, 회사 정보를 받지만, 게임기 제작 회사와 게임 제작 회사는 서로 다를 수 있습니다.
    - 예를 들어 패밀리 컴퓨터 게임 중에 록맨 시리즈는 CAPCOM에서 제작했지만, 게임기는 닌텐도에서 제작했습니다.
    
## Screen Shots

![reactive_result_01](/image/reactive_result_01.png)

게임 회사의 전체 목록을 조회합니다.

![reactive_result_02](/image/reactive_result_02.png)

게임 회사 중 하나를 조회합니다.

![reactive_result_03](/image/reactive_result_03.png)

게임 회사 하나를 추가합니다.

![reactive_result_04](/image/reactive_result_04.png)

게임 회사 정보를 일부 변경합니다.

![reactive_result_05](/image/reactive_result_05.png)

게임 회사 정보를 일부 제거합니다.

![reactive_result_06](/image/reactive_result_06.png)

실제 데이터베이스에 삭제되었음을 발견할 수 있습니다.

## Author
- [강인성](https://github.com/tails5555)
