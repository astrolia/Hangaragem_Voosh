# Sistema de Gerenciamento de Hangaragem (Hangaragem_Voosh)

Este é um sistema de gerenciamento de hangaragem de aeronaves desenvolvido em Java, utilizando JavaFX para a interface gráfica e Hibernate (JPA) para a persistência de dados.

## 🚀 Tecnologias Utilizadas

*   **Java 21:** Linguagem de programação principal.
*   **JavaFX:** Framework para a construção da interface gráfica do usuário (GUI).
*   **Hibernate / Jakarta Persistence (JPA):** Mapeamento Objeto-Relacional (ORM) para interação com o banco de dados.
*   **MySQL:** Sistema de gerenciamento de banco de dados relacional.
*   **Maven:** Ferramenta de automação de compilação e gerenciamento de dependências.

## 📦 Estrutura e Funcionalidades

O sistema é focado no gerenciamento das seguintes entidades:
*   **Aeronaves:** Cadastro e controle de aeronaves.
*   **Hangares:** Gerenciamento dos hangares disponíveis.
*   **Localidades:** Controle das localidades (aeroportos/bases) onde os hangares se encontram.
*   **Locações:** Registro e gerenciamento do aluguel/uso dos espaços de hangaragem pelas aeronaves.

A interface gráfica é dividida em módulos para facilitar o uso (Gerenciar Aeronave, Gerenciar Hangar, Gerenciar Locação, Gerenciar Localidade).

## 🛠️ Pré-requisitos

Para executar este projeto localmente, você precisará ter instalado em sua máquina:

1.  **Java Development Kit (JDK) 21**
2.  **Apache Maven**
3.  **MySQL Server** (rodando localmente)

## ⚙️ Configuração do Banco de Dados

1.  Certifique-se de que o servidor MySQL esteja em execução na sua máquina local (`localhost:3306`).
2.  O sistema está configurado para conectar-se ao banco de dados utilizando o usuário `root` sem senha (senha em branco). Se o seu banco de dados possuir senha ou outro usuário, você precisará alterar as credenciais no arquivo `src/main/resources/META-INF/persistence.xml`:

```xml
<property name="jakarta.persistence.jdbc.user" value="root" />
<property name="jakarta.persistence.jdbc.password" value="sua_senha_aqui" />
```

3.  O sistema criará o banco de dados `sahthanDB` automaticamente caso ele não exista (`createDatabaseIfNotExist=true`).
4.  O Hibernate está configurado com `hibernate.hbm2ddl.auto = update`, o que significa que ele criará/atualizará as tabelas automaticamente com base nas classes de modelo (`@Entity`).

## ▶️ Como Executar o Projeto

Com os pré-requisitos instalados e o banco de dados configurado, siga os passos abaixo para rodar o projeto:

1.  Clone este repositório ou faça o download do código-fonte.
2.  Abra o terminal e navegue até o diretório raiz do projeto (onde está localizado o arquivo `pom.xml`).
3.  Compile e execute o projeto utilizando o Maven e o plugin do JavaFX com o seguinte comando:

```bash
mvn clean javafx:run
```

Este comando fará o download de todas as dependências necessárias, compilará o código e iniciará a aplicação gráfica.


# Diagrama de caso de uso

<img width="569" height="410" alt="diagrama_casos_de_uso_hangar" src="https://github.com/user-attachments/assets/d783fea5-c216-4bec-9fdb-dd33ca89461b" />

# Diagrama de classes

<img width="1003" height="616" alt="image" src="https://github.com/user-attachments/assets/4196a9e8-c572-4546-8481-f05be2eb0ce3" />


