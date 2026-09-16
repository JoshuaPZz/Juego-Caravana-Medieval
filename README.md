<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" />
  <img src="https://img.shields.io/badge/Angular-19-DD0031?style=for-the-badge&logo=angular&logoColor=white" />
  <img src="https://img.shields.io/badge/TypeScript-5-3178C6?style=for-the-badge&logo=typescript&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white" />
  <img src="https://img.shields.io/badge/JPA_/_Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white" />
</p>

# 🏰 Caravana Medieval — Juego de Estrategia, Rutas y Comercio

> **Plataforma web Full-Stack de estrategia y simulación económica medieval**. Los jugadores gestionan sus propias caravanas comerciales, navegan por rutas interurbanas con diferentes niveles de riesgo, comercian bienes bajo leyes dinámicas de oferta/demanda y mejoran su flota mediante servicios urbanos.

---

## 📌 Descripción General

**Caravana Medieval** es un videojuego de estrategia y gestión comercial desarrollado bajo una arquitectura desacoplada Full-Stack. El sistema simula una red económica interconectada de más de 100 ciudades medievales, donde los jugadores asumen roles comerciales, administran capacidad de carga, optimizan trayectos y protegen sus bienes ante los peligros del camino.

---

## 🌟 Características y Mecánicas del Juego

### 1. 📈 Simulación Económica y Comercio Dinámico
- **Red de Ciudades:** Más de 100 ciudades autónomas con catálogo de productos propios y variaciones de inventario.
- **Oferta y Demanda:** Precios fluctuantes según la disponibilidad de más de 50 tipos de mercancías (especias, telas, metales preciosos, armas, grano, hierbas medicinales, etc.).
- **Compra y Venta:** Mercado en tiempo real con validación estricta de fondos y capacidad de bodega.

### 2. 🛡️ Gestión y Optimización de Caravanas
- **Atributos de Flota:** Parámetros de durabilidad, daño acumulado, velocidad de desplazamiento y capacidad máxima de carga.
- **Inventario Modular:** Control estricto del peso y tipo de productos transportados por cada caravana.

### 3. 🗺️ Navegación y Rutas Comerciales con Riesgo
- **Topología de Rutas:** Red de caminos interurbanos con diferentes distancias, duraciones y factores de peligro.
- **Riesgo y Eventos:** Rutas inseguras que infligen daño a la caravana durante el trayecto, requiriendo planificación estratégica.

### 4. 🛠️ Servicios Urbanos y Mejoras
- **Reparación:** Restauración de puntos de durabilidad en talleres locales.
- **Expansión de Bodega:** Aumento de la capacidad máxima de carga.
- **Optimización de Velocidad:** Reducción del tiempo de viaje entre ciudades.
- **Contratación de Guardias:** Escoltas que reducen en un 25% el daño sufrido en rutas de alto riesgo.

### 5. 🔐 Seguridad y Control de Acceso
- **Autenticación con JWT:** Tokens criptográficos seguros para el inicio de sesión y validación de sesiones activas.
- **Control de Acceso Basado en Roles (RBAC):** Perfiles de `COMERCIANTE` y `CARAVANERO` con permisos y vistas diferenciadas.

---

## 🏛️ Arquitectura del Sistema

```text
┌─────────────────────────────────────────────────────────────┐
│                 Frontend SPA (Angular 19)                   │
│   - Componentes reactivos, Guards de autenticación          │
│   - Formularios reactivos, servicios HTTP con RxJS          │
└──────────────────────────────┬──────────────────────────────┘
                               │ JSON / HTTPS (JWT Bearer Token)
                               ▼
┌─────────────────────────────────────────────────────────────┐
│                Backend REST (Spring Boot 3)                 │
│  ┌───────────────────────────────────────────────────────┐  │
│  │   Controladores REST (Caravana, Ciudad, Viajes...)    │  │
│  ├───────────────────────────────────────────────────────┤  │
│  │   Seguridad & Filtro JWT (Spring Security)            │  │
│  ├───────────────────────────────────────────────────────┤  │
│  │   Capa de Servicios y Reglas de Negocio               │  │
│  ├───────────────────────────────────────────────────────┤  │
│  │   Persistencia JPA / Hibernate + Mappers (DTOs)       │  │
│  └───────────────────────────────────────────────────────┘  │
└──────────────────────────────┬──────────────────────────────┘
                               │ JDBC / SQL
                               ▼
┌─────────────────────────────────────────────────────────────┐
│                   Base de Datos Relacional                  │
│       (PostgreSQL / H2 Database con DbInitializer)          │
└─────────────────────────────────────────────────────────────┘
```

---

## 🛠️ Stack Tecnológico

| Módulo | Tecnología | Versión / Detalle |
|---|---|---|
| **Backend Framework** | Spring Boot | 3.x (Java 17) |
| **Seguridad** | Spring Security | Autenticación basada en JSON Web Tokens (JWT) |
| **Persistencia** | Spring Data JPA / Hibernate | Mapeo objeto-relacional y repositorios transaccionales |
| **Frontend Framework** | Angular | 19.x (TypeScript 5) |
| **Programación Reactiva** | RxJS | Manejo de flujos asíncronos y estado |
| **Diseño / Estilos** | CSS3 / Angular Modules | Interfaz temática adaptativa |
| **Construcción & Build** | Maven / Angular CLI | Automatización de compilación y empaquetado |

---

## 📁 Estructura del Repositorio

```text
Juego-Caravana-Medieval/
├── Juego-Caravana-Medieval/          # Servidor Backend (Spring Boot 3)
│   ├── src/main/java/co/edu/javeriana/caravana_medieval/
│   │   ├── config/                   # Configuración de Seguridad y Filtros JWT
│   │   ├── controller/               # Endpoints REST para Caravanas, Ciudades, etc.
│   │   ├── dto/                      # Data Transfer Objects
│   │   ├── mapper/                   # Mapeadores DTO <-> Entity
│   │   ├── model/                    # Entidades JPA (Caravana, Ciudad, Jugador...)
│   │   ├── repository/               # Repositorios JPA
│   │   ├── service/                  # Lógica y reglas de negocio del juego
│   │   └── init/DbInitializer.java   # Poblador inicial de ciudades, rutas y productos
│   └── pom.xml
└── Front-Juego-Caravana-Medieval/    # Cliente Frontend (Angular 19)
    ├── src/app/
    │   ├── authentication/           # Vistas de Login y Registro
    │   ├── caravana/                 # Gestión de inventario y estado de caravana
    │   ├── ciudad/                   # Exploración de ciudades y servicios
    │   ├── productos/                # Compra y venta de mercancías
    │   ├── viaje/                    # Navegación y selección de rutas
    │   └── guards/                   # Protección de rutas con JWT
    ├── angular.json
    └── package.json
```

---

## 🚀 Puesta en Marcha

### Prerrequisitos
- **Java Development Kit (JDK)** 17 o superior
- **Apache Maven** 3.8+ (o usar `./mvnw`)
- **Node.js** 18+ y **npm** / **bun**

### 1. Iniciar el Backend (Spring Boot)
```bash
cd Juego-Caravana-Medieval
./mvnw spring-boot:run
```
El servidor backend estará disponible en `http://localhost:8080`.

### 2. Iniciar el Frontend (Angular)
```bash
cd Front-Juego-Caravana-Medieval
npm install
npm start
```
Abre tu navegador en `http://localhost:4200`.

---

## 📄 Licencia

Desarrollado como proyecto académico y de ingeniería de software en la **Pontificia Universidad Javeriana**.
