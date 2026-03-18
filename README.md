# The Tragamonedas


**Autor:** Josemanuel Espinoza

---
Proyecto desarrollado en **Java** que simula una máquina tragamonedas por consola. El programa permite ingresar un saldo inicial, realizar apuestas mientras exista saldo disponible y mostrar el resultado de cada tirada junto con el premio obtenido.

---

## Estructura del proyecto

```bash
src/
└── cl/ucn/disc/ads/tragamonedas/
    ├── TheMain.java
    ├── model/
    │   ├── Box.java
    │   └── Rueda.java
    └── services/
        ├── Tragamonedas.java
        └── TragamonedasImpl.java
```
---
## Cómo ejecutar

Este proyecto puede compilarse y ejecutarse desde terminal con Java.

### 1. Compilar

Ubícate en la raíz del proyecto y ejecuta:

```bash
javac -d out src/cl/ucn/disc/ads/tragamonedas/model/*.java src/cl/ucn/disc/ads/tragamonedas/services/*.java src/cl/ucn/disc/ads/tragamonedas/TheMain.java
```
---
### 2. Ejecutar

```bash
java -cp out cl.ucn.disc.ads.tragamonedas.TheMain
```

---
## Documentación adicional

El proyecto incluye una carpeta `docs/` con un archivo PlantUML para apoyar la representación de clases del sistema.

---
(c) 2026 arquitectura de software, Disc,ucn