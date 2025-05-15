# Okey-App

[![Docker Image](https://img.shields.io/badge/Docker-OK-brightgreen)](https://hub.docker.com/repository/docker/erdincdagli/okey-app/general)

## Proje Hakkında

**Okey-App**, geleneksel Türk oyunu Okey'in dijital ortamda oynanabilmesi için geliştirilmiş modern ve modüler bir masaüstü/çevrimiçi oyun uygulamasıdır. Proje, sağlam yazılım prensipleri, esnek mimari ve güncel teknolojiler kullanılarak tasarlanmıştır.

---

## İçindekiler

- [Proje Özellikleri](#proje-özellikleri)
- [Kullanılan Teknolojiler](#kullanılan-teknolojiler)
- [Mimari ve Design Patternler](#mimari-ve-design-patternler)
- [SOLID Prensiplerine Uyum](#solid-prensiplerine-uyum)
- [Testler ve Test Sonuçları](#testler-ve-test-sonuçları)
- [Docker Kullanımı](#docker-kullanımı)
- [Dokümantasyon](#dokümantasyon)
- [Kurulum ve Çalıştırma](#kurulum-ve-çalıştırma)
- [Katkıda Bulunma](#katkıda-bulunma)
- [Lisans](#lisans)

---

## Proje Özellikleri

- Çok oyunculu çevrimiçi Okey oyunu deneyimi
- Oyun kuralları tamamen uygulandı
- Kullanıcı arayüzü ve oyun akışı optimize edildi
- Modüler ve test edilebilir kod yapısı
- Kolayca genişletilebilir ve bakım yapılabilir mimari

---

## Kullanılan Teknolojiler

- **Backend:** Java 17, Spring Boot
- **Build:** Maven
- **Container:** Docker
- **Test:** JUnit 5, Mockito, Jacoco

---

## Mimari ve Design Patternler

Proje tasarımında aşağıdaki tasarım kalıpları (Design Patterns) kullanılmıştır:

- **Singleton:** Oyun oturumu ve konfigürasyonların tekil olarak yönetimi için.
- **Factory Pattern:** Oyun taşları ve oyuncu nesnelerinin yaratılması için.
- **Observer Pattern:** Oyuncuların hamleleri ve oyun durumunun gerçek zamanlı olarak diğer katılımcılara bildirilmesi için.
- **Strategy Pattern:** Farklı oyun kurallarının veya hamle stratejilerinin uygulanabilmesi için.
- **Command Pattern:** Oyun içi hareketlerin (taş çekme, taş koyma vb.) komut olarak yönetilmesi için.
- **MVC (Model-View-Controller):** Kullanıcı arayüzü ile iş mantığını ayrıştırmak için.

---

## SOLID Prensiplerine Uyum

Proje mimarisi SOLID prensiplerine uygun olarak geliştirilmiştir:

- **Single Responsibility Principle (SRP):** Her sınıfın sadece tek bir sorumluluğu vardır. Örneğin, `GameManager` sadece oyun akışını yönetir.
- **Open/Closed Principle (OCP):** Mevcut sınıflar değişikliğe kapalı, genişletmeye açık olacak şekilde tasarlandı. Yeni oyun kuralları veya özellikler eklenebilir.
- **Liskov Substitution Principle (LSP):** Türetilmiş sınıflar, temel sınıflar yerine kullanılabilir ve beklenen davranışı korur.
- **Interface Segregation Principle (ISP):** Büyük arayüzler yerine, daha küçük ve spesifik arayüzler kullanılarak gereksiz bağımlılıklar azaltıldı.
- **Dependency Inversion Principle (DIP):** Yüksek seviyeli modüller, düşük seviyeli modüllere bağlı değil; her ikisi de soyutlamalara bağlıdır. Bağımlılıklar inversion ve IoC container (Spring) ile yönetiliyor.

---

## Testler ve Test Sonuçları

- **Test Kütüphaneleri:** JUnit 5 ve Mockito kullanılarak birim testler yazıldı.
- **Test Kapsamı:** Oyun mantığı, kullanıcı hareketleri, kural doğrulamaları, hata durumları.
- **Test Senaryoları:**
    - Oyun başlangıcı ve taş dağıtımı
    - Taş çekme ve koyma işlemleri
    - Kazanma durumu kontrolü
    - Oyuncu hamlelerinin geçerliliği
- **Test Sonuçları:** Tüm testler başarılı olarak tamamlanmıştır (test sonuçları `document/test-results` klasöründe mevcuttur).
- **Test Raporları ve Diyagramlar:** Proje içerisindeki `document` klasöründe, test sonuçlarına ait grafikler ve diyagramlar bulunmaktadır. Bu raporlar CI/CD süreçlerinde de otomatik olarak üretilmektedir.

---

## Docker Kullanımı ve GitHub Üzerinden Kurulum

Proje Docker ile containerize edilmiştir. Docker Hub üzerinden hazır imaj aşağıdaki linkten erişilebilir:

- [Docker Hub - Okey-App](https://hub.docker.com/repository/docker/erdincdagli/okey-app/general)
- [GitHub Repository - Okey-App](https://github.com/Erdincdagli35/Okey-App)
- [GitHub Repository - Okey-Algo](https://github.com/Erdincdagli35/Okey)

### Docker İmajı Çalıştırma 
### Proje kaynak kodunu doğrudan GitHub üzerinden klonlayarak da kurulumu gerçekleştirebilirsiniz:

```bash
docker pull erdincdagli/okey-app
docker run -d -p 8080:8080 erdincdagli/okey-app

git clone https://github.com/Erdincdagli35/Okey-App.git
cd Okey-App 

git clone https://github.com/Erdincdagli35/Okey.git
cd Okey