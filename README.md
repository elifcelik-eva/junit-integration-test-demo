# JUnit & Integration Test Demo

Bu proje, Spring Boot tabanlı bir Java uygulamasında birim testler ve entegrasyon testlerinin nasıl yapılandırıldığını ve çalıştırıldığını göstermek için hazırlanmıştır.

## Özellikler

- Spring Boot 3.5.15
- Java 21
- Spring Web, Spring Data JPA
- JUnit 5 tabanlı testler
- Mockito ile mock tabanlı birim testleri
- `@WebMvcTest`, `@SpringBootTest`, `@DataJpaTest` entegrasyon testleri
- H2 bellek içi veritabanı ile test ortamı

## Proje Yapısı

- `src/main/java/com/elifcelik/testlab`: Ana uygulama paketi
- `src/main/java/com/elifcelik/testlab/integrationTestCases/dataJpaTest`: Ürün CRUD, servis ve REST kontrolcüsü
- `src/test/java/com/elifcelik/testlab/unitTestCases/easyDemoTest`: Basit hesaplama birim testi
- `src/test/java/com/elifcelik/testlab/unitTestCases/hardDemoTest`: Mockito tabanlı servis testleri
- `src/test/java/com/elifcelik/testlab/unitTestCases/midDemoTest`: Kullanıcı servis testi
- `src/test/java/com/elifcelik/testlab/integrationTestCases/WebMvcTest`: `@WebMvcTest` ile kontrolcü testi
- `src/test/java/com/elifcelik/testlab/integrationTestCases/springBootTest`: `@SpringBootTest` ile uygulama testi
- `src/test/java/com/elifcelik/testlab/integrationTestCases/dataJpaTest`: `@DataJpaTest` ile JPA repository testi

## Paketler ve Sınıflar

- `com.elifcelik.testlab`: `Demos2Application`
- `com.elifcelik.testlab.integrationTestCases.dataJpaTest`
  - `Product`, `ProductRepository`, `ProductService`, `ProductController`
- `com.elifcelik.testlab.unitTestCases.easyDemo`
  - `Calculator`
- `com.elifcelik.testlab.unitTestCases.hardDemo`
  - `OrderService`, `OrderRepository`, `PaymentService`, `NotificationService`, `Order`, `PaymentFailedException`
- `com.elifcelik.testlab.unitTestCases.midDemo`
  - `UserService`, `UserRepository`, `User`, `UserNotFoundException`

## Test Türleri

1. Unit Testler
   - `CalculatorTest` — temel hesaplama doğrulamaları
   - `OrderServiceTest` — servis ve ödeme akışı testleri
   - `UserServiceTest` — kullanıcı servisi davranışı testi

2. Entegrasyon Testleri
   - `ProductControllerTest` — `@WebMvcTest` ile REST API kontrolcüsü testi
   - `ProductIntegrationTest` — `@SpringBootTest` ile uygulama ve `MockMvc` testi
   - `ProductRepositoryTest` — `@DataJpaTest` ile JPA repository testi

## Çalıştırma

Projeyi derlemek ve testleri çalıştırmak için Maven kullanabilirsiniz.

```bash
./mvnw clean test
```

Windows üzerinde:

```powershell
mvnw.cmd clean test
```

## Uygulamayı Başlatma

Uygulamayı çalıştırmak için:

```bash
./mvnw spring-boot:run
```

Windows üzerinde:

```powershell
mvnw.cmd spring-boot:run
```

## Örnek API

- `GET /products/{id}` — ID ile ürün getirme
- `POST /products` — JSON gövdesi ile ürün kaydetme

Örnek `POST` JSON:

```json
{
  "name": "telefon",
  "price": 30000
}
```

## Bağımlılıklar

- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-test`
- `org.springframework.boot:spring-boot-starter-data-jpa`
- `com.h2database:h2` (test scope)
- `org.projectlombok:lombok`

## Notlar

- `lombok` sadece derleme sırasında kullanılır; IDE desteği için Lombok eklentisi gerekebilir.
- Testler H2 bellek içi veritabanı ile çalışacak şekilde yapılandırılmıştır.
