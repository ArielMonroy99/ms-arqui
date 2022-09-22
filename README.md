# Sistema de gestión de citas y ventas de una veterinaria
## Problema 

Una clinica veterinaria y tienda de articulos para mascotas lleva el registro de ventas y de citas de forma manual en cuaderno. Esto conduce a problemas con el inventario y la contabilidad del establecimiento. Ademas los clientes tienen que apersonarse al establecimiento o llamar para hacer una cita, lo que en muchas ocasiones no es comodo para el cliente.

---

## Propuesta general de solucion 

Se propone realizar una aplicacion web donde los usuarios se registren para poder hacer citas con el veterinario, consultando los horarios disponibles dentro de la semana siguiente. La aplicacion también proporcionara la opcion de realizar compras dentro de la misma pagina, estos podran ser recogidos en la misma tienda o ser enviados a una direccion proporcionada por el cliente. 

Para la administracion se podran añadir nuevos doctores con sus respectivos horarios, cancelar citas con una justificacion, realizar modificaciones al inventario y gestionar los pedidos realizados por la pagina. 

---

## Valor para el negocio 
 
 Implementar un sistema de informacion que permita el control de citas en la veterinaria, que facilite el registro de ventas y permita llevar un control de inventario mas eficaz. 

 ### Tangible 
 - Automatizar los procesos de registro de citas y ventas 
 - Controlar el inventario evitando perdidas de productos
 - Evitar la perdida de clientes 

### Intangible 
- Mayor conformidad para los clientes y empleados 
- Mayor confianza en las transacciones y citas dentro de la veterinaria 

---

## Factibilidad (Análisi de riesgos)

### Economica 

- El sistema tendra un costo 14500 Bs.

### Técnica 

- Se realaizara el diseño de interfaces de usuarios intuitivas 
- Se implementara el backend en el 
framework de Java, Spring Boot 
- Se diseñara la base de datos en Postgresql 
- Se implementara el frontend en el framework Angular en su version 13.0

---

## Listado de requerimientos con historias de usuario 

### Historias de Usuario

---

#### [001 - Registro de cliente](https://github.com/ArielMonroy99/ms-arqui/issues/1)  

**Fecha**: 12/09/2022   
**Prioridad**: ALTA

#### Narrativa 

Como *cliente* quiero poder registrarme en el sistema mediante un formulario en la pagina web, proporcionando mis datos como correo, nombre, usuario y password de forma que ya pueda empezar a usar el sistema. 

---

#### [002 -  Registro de Citas](https://github.com/ArielMonroy99/ms-arqui/issues/2) 

**Fecha**: 12/09/2022   
**Prioridad**: ALTA

#### Narrativa 

Como *cliente* quiero agendar una cita mediante la página, conociendo los horarios y dias que estas disponibles de forma que tenga que ya una hora fija para asistir con el veterinario. 

---

### [003 - Venta de productos](https://github.com/ArielMonroy99/ms-arqui/issues/3) 

**Fecha**: 12/09/2022   
**Prioridad**: ALTA

#### Narrativa 

Como *cliente* quiero realizar compras compras mediante la página web y que se entreguen en la ubicacion que decida o directamente en la tienda de forma que pueda recibir mis productos o recogerlos yo mismo. 

---

### [004 - Registro de empleados](https://github.com/ArielMonroy99/ms-arqui/issues/4) 

**Fecha**: 12/09/2022   
**Prioridad**: ALTA

#### Narrativa 

Como *Administrador* quiero poder agregar empleados con sus datos como nombre, ci, usuario, password de forma que ellos puedan acceder al sistema para administrar ciertos aspectos. 

---

### [005 - Registro de Veterinario](https://github.com/ArielMonroy99/ms-arqui/issues/5) 

**Fecha**: 12/09/2022   
**Prioridad**: ALTA

#### Narrativa 

Como *Administrador* quiero añadir nuevos Veterinarios con sus respectivos horarios de forma que los clientes ya puedan agendar citas con ellos. 

---

### [006 - Administracion de Inventario](https://github.com/ArielMonroy99/ms-arqui/issues/6) 

**Fecha**: 12/09/2022   
**Prioridad**: ALTA

#### Narrativa 

Como *Administrador* quiero agregar, editar o eliminar los productos que se muestren dentro de la tienda de forma que se muestren siempre un catalogo acorde con lo que tenemos en la tienda. 

---

### [007 - Inicio de Sesion](https://github.com/ArielMonroy99/ms-arqui/issues/7) 

**Fecha**: 12/09/2022  
**Prioridad**: ALTA 
 
 #### Narrativa 

 Como *Usuario* quiero inicar sesion dentro del sistema de forma que pueda utilizar las herramientas que ofrece. 

 #### Dependencia

 Depende de Registro de Cliente #1 , Registro de Usuario #4

---

 ### [008 - Reportes de Ventas](https://github.com/ArielMonroy99/ms-arqui/issues/8) 

**Fecha**: 12/09/2022  
**Prioridad**: Media 

#### Narrativa 

Como *Administrador* quiero ver reportes de los productos más vendidos, productos sin stock de forma que pueda tomar las decisiones pertinentes al respecto. 

#### Dependencia 

Esta historia depende de Venta de productos, Administracion de Inventario

---

### [009 - Reportes sobre Veterinarios](https://github.com/ArielMonroy99/ms-arqui/issues/9)

**Fecha**: 12/09/2022  
**Prioridad**: Media

#### Narrativa 

Como *Administrador* quiero ver reportes sobre los veterinaios mas solicitados en base a la citas que se agendan con ellos de forma que pueda tomar desiciones al respecto. 


#### Dependencia 

Dependene de Registro de Veterinario, 
Registro de citas. 
 
---

### [010 - Cancelacion de Cita](https://github.com/ArielMonroy99/ms-arqui/issues/10)

**Fecha**: 12/09/2022  
**Prioridad**: Media

#### Narrativa 

Como *Usuario* quiero cancelar una cita dando un justificativo de forma que no perjudique al personal o al cliente que haya realizado la cita. 

#### Dependencia 

Depende de Registro de Citas

### [011 - Gestion de Direcciones](https://github.com/ArielMonroy99/proyectoArqui/issues/11)

**Fecha**: 15/09/2022
**Prioridad**:Media 

#### Narrativa 

Como *Usuario* quiero adicionar y editar mis direcciones de entrega de forma que pueda recibir los productos que  compre en la tienda

#### Dependencia 

Depende de Registro de Usuario #1

---
###[012 - Gestion de Pedidos](https://github.com/ArielMonroy99/proyectoArqui/issues/12)

**Fecha**: 20/09/2022  
**Prioridad**: Alta

#### Narrativa 

Como *Administrador* aceptar o rechazar los pedidos que se realizan a la tienda de forma que tenga manera de controlar los pedidos  que me llegan

#### Dependencia 

Depende de Gestion de Inventario #6 , Registro de Empleados #4 
