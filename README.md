# **Gestion des Projets** 📋

## 📂 Description
Ce projet est une application de gestion des projets et de l'affectation des utilisateurs aux projets. Elle utilise Spring Boot pour le backend, avec des fonctionnalités telles que :
- Gestion des projets.
- Affectation des utilisateurs aux projets.
- Gestion des utilisateurs et des rôles.

L'architecture suit une approche orientée services avec des entités, DTOs et mappers pour une séparation claire des responsabilités.

---

## ⚙️ **Technologies utilisées**
- **Backend :** Spring Boot (Spring Data JPA, Spring MVC)
- **Base de données :** MySQL (ou une autre base compatible JPA)
- **Mapper :** MapStruct
- **Langage :** Java 17
- **Autres dépendances :**
  - Lombok
  - Hibernate Validator (pour la validation des données)
  - Spring Boot DevTools (facilite le développement)
  - Spring Boot Starter Web (pour exposer les API REST)

---

## 📂 **Structure du projet**
Voici un aperçu des principaux packages du projet :
- `controller` : Gère les API REST.
- `dto` : Définit les objets de transfert de données.
- `entity` : Contient les entités JPA représentant les tables de la base de données.
- `mapper` : Gère les conversions entre entités et DTOs.
- `service` : Implémente la logique métier.
- `repository` : Contient les interfaces JPA pour les opérations CRUD.
- `exception` : Gère les exceptions globales via un `@ExceptionHandler` personnalisé pour centraliser la gestion des erreurs.
- `security` : Implémente la sécurité de l'application avec Spring Security, incluant l'authentification et l'autorisation, ainsi que la gestion des JWT et OAuth2.

---

## 🛠️ **Configuration et installation**

### **1. Prérequis**
- Java 17 ou plus
- Maven
- MySQL (ou une base de données compatible)
- Un IDE comme IntelliJ IDEA

### **2. Étapes d'installation**
1. Clonez le projet :
   ```bash
   git clone https://github.com/alichaaben/RESTful-API-For-Management_CV.git
   cd hr
   ```

2. Configurez la base de données MySQL :
   - Créez une base de données nommée `gestion_projets` :
     ```sql
     CREATE DATABASE gestion_projets;
     ```
   - Ajoutez vos identifiants MySQL dans `application.properties` :
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/gestion_projets
     spring.datasource.username=VOTRE_USERNAME
     spring.datasource.password=VOTRE_PASSWORD
     ```

3. Installez les dépendances Maven :
   ```bash
   mvn clean install
   ```

4. Lancez l'application :
   ```bash
   mvn spring-boot:run
   ```

---

## 📖 **API Endpoints**

### **Projets**
| Méthode | URL                  | Description                          |
|---------|----------------------|--------------------------------------|
| `GET`   | `/projets/{id}`       | Récupérer un projet par ID           |
| `GET`   | `/projets`            | Récupérer tous les projets           |
| `POST`  | `/projets`            | Ajouter un nouveau projet            |
| `PUT`   | `/projets`            | Mettre à jour un projet              |
| `DELETE`| `/projets/{id}`       | Supprimer un projet par ID           |

### **Affectation d'utilisateurs aux projets**
| Méthode | URL                         | Description                          |
|---------|-----------------------------|--------------------------------------|
| `POST`  | `/affectations`             | Affecter un utilisateur à un projet |

---

## 🧪 **Tests**
- Pour exécuter les tests, utilisez la commande suivante :
  ```bash
  mvn test
  ```

---

## 🚀 **Fonctionnalités futures**
- Ajout de l'authentification et de l'autorisation (Spring Security).
- Gestion des rôles et permissions.
- Intégration avec un frontend basé sur Angular.

---

## 📝 **Contributeurs**
- **Ali chaabane** : [Email](alichaaben85@gmail.com)

---

## 📄 **Licence**
Ce projet est sous licence de Tek Up University. Vous êtes libre de l'utiliser, de le modifier et de le distribuer tant que vous mentionnez les auteurs originaux.