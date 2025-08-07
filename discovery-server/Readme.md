## 🛰️ Eureka Server

**Eureka Server** — это сервис регистрации и обнаружения (Service Discovery) в микросервисной архитектуре, основанный на Spring Cloud Netflix Eureka.

Этот сервис позволяет другим микросервисам автоматически регистрироваться и обнаруживать друг друга, обеспечивая гибкую маршрутизацию без жёстко заданных URL.

---

### 🧱 Технологический стек

* Java 17
* Spring Boot
* Spring Cloud Netflix Eureka Server
* Maven
* Docker

---

### 🚀 Запуск проекта

#### 📦 Сборка

```bash
./mvnw clean package
```

#### ☕ Запуск локально

```bash
./mvnw spring-boot:run
```

#### 🐳 Запуск с помощью Docker (опционально)

```bash
docker build -t faketri-eureka .
docker run -p 8761:8761 faketri-eureka
```

---

### ⚙️ Конфигурация

Пример `application.properties`:

```properties
server.port=8761

spring.application.name=eureka-server

eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
eureka.client.server.wait-time-in-ms-when-sync-empty=0
eureka.client.server.enable-self-preservation=false

eureka.instance.hostname=eureka-server
eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka

spring.security.user.name=${EUREKA_USER}
spring.security.user.password=${EUREKA_PASSWORD}
```

> Здесь `register-with-eureka: false` и `fetch-registry: false` — это настройки, чтобы сам Eureka-сервер не регистрировался в другом экземпляре.

---

### 📡 Доступ к Eureka Dashboard


```
http://localhost:8761
```

Здесь находится панель мониторинга Eureka со списком зарегистрированных микросервисов.

---

### 🔗 Регистрация клиента

Другие сервисы (например, `user-service`, `gateway`, `product-service`) регистрируются в Eureka, добавив следующее в их `application.properties`:

```properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

---

### 📂 Структура проекта

```
EurekaServer/
├── src/
│   └── main/
│       ├── java/org/faketri/
│       │   └── EurekaServerApplication.java
│       └── resources/
│           ├── application.properties
└── pom.xml
```

---