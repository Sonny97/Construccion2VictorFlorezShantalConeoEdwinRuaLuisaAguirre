# Construccion de software II
 - Horario Lunes - Miercoles : 8 - 10 pm
## Integrantes del equipo
- Victor Manuel Florez Taborda (https://github.com/Sonny97)
- Shantal Coneo Garcia (github)
- Edwing xavier rua dominguez (github)
- Luisa Fernanda Aguirre (github)

## Tecnologia de desarrollo 
- Java v???
- SpringBoot
- un framework raro que menciono el profe pero no recuerdo
- Git
- sepa la chingada que mas...

## 🔁 Clonar el repositorio

Para clonar el repositorio en tu máquina local, utiliza los siguientes comandos:
1. git clone url //para clonar el repo en el local
2. git add . // para tomar los cambios listos a subir
3. git commit -m "mensaje" // para comentar sobre el cambio realizado
4. git pull origin develop // para actualizar los cambios mas recientes de la rama develop (usar solo 1 vez terminado todo el desarrollo)
5. git push origin feature/nombre-rama // para subir los cambios al repositorio

## comandos aparte:
  - git checkout -b feature/nombre-rama //para crear una rama nueva
  - git checkout feature/nombre-rama // para cambiarse a otra rama
  - git fetch // para actualizar las ramas y hashes que esten en develop

# 📌 Proyecto: Sistema de Gestión de Información Clínica (Clinica IPS 2024)

## 🏫 Contexto Académico
Este proyecto se desarrolla en la materia **Construcción del Software 2** bajo la guía de **Víctor**, con la participación de **Shandal** y **Xavier** , **Luisa** como integrantes del equipo de desarrollo.

---

## 🎯 Objetivo del Proyecto
Diseñar e implementar un **sistema de gestión de información clínica** que permita administrar de manera eficiente:
- Pacientes y su historia clínica.
- Gestión de órdenes médicas.
- Facturación y control de pólizas de seguros.
- Inventarios de medicamentos, procedimientos y ayudas diagnósticas.
- Control de accesos según roles del personal de la clínica.

El proyecto sigue el patrón **Arquitectura Hexagonal (Ports and Adapters)** para mantener el sistema desacoplado, testeable y adaptable a cambios tecnológicos.

---

## 🏗 Arquitectura Hexagonal

### Capas Principales
1. **Domain Layer (Dominio)**  
   - Entidades, Objetos de Valor, Servicios de Dominio, Interfaces de Repositorios, Fábricas.  
   - Contiene las **reglas de negocio** y lógica pura.

2. **Application Layer (Aplicación)**  
   - Casos de Uso (Use Cases) que coordinan la ejecución de las reglas del dominio.
   - Puertos de Entrada (Input Ports) y Puertos de Salida (Output Ports).

3. **Infrastructure Layer (Infraestructura)**  
   - Implementaciones técnicas de repositorios, servicios externos, persistencia SQL/NoSQL.

4. **Interface Layer (Interfaz)**  
   - Adaptadores de entrada como controladores REST, CLI u otros.

---

## 📂 Estructura de Carpetas Java

```plaintext
src/main/java/com/tuempresa/clinica
│
├── main.java                     # Clase main (Spring Boot)
│
├── domain                        # Lógica del negocio, núcleo del sistema
│   ├── model                      # Interfaces y clases abstractas
│   ├── repository                 # NP& (No Precisado)
│   └── service                    # Servicios de dominio (reglas de negocio)
│
├── infrastructure                # Implementaciones técnicas (BD, etc.)
│
├── mocks                      # Datos mockeados para pruebas desde main
│
├── application                   # Capa de aplicación (Casos de uso)
│   ├── port                       # Puertos (si se usan)
│   └── usecase                    # Implementaciones de casos de uso
│
├── adapter                       # Adaptadores
│   ├── in                         # Middleware
│   └── out                        # Interceptor
│
└── config                        # NP& (No Precisado)
```

