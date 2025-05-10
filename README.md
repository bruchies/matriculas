# 📊 Matriculas Backend

Backend do projeto da disciplina **Projeto e Arquitetura de Software**, da UCS. Desenvolvido em grupo utilizando **Spring Boot**, **JDBC**, **MySQL** e o padrão **MVC**.

Integrantes: Bruna Chies, Felipe Castilhos, Jhonatan Martins

---

## 🚀 Tecnologias Utilizadas

### Backend
- Java 21
- Spring Boot 3.4.5
- Spring Web
- Spring JDBC
- MySQL
- OpenCSV (leitura de arquivos `.csv`)
- Maven
- Lombok
- DevTools
- Swagger (documentação da API)
### Frontend
- React
- React Router DOM
- Recharts
- CSS Puro

---

## 📁 Estrutura Base do Projeto e Arquitetura Implementada

A aplicação `matriculas-backend` foi desenvolvida seguindo o padrão arquitetural **MVC (Model-View-Controller)**, uma escolha que proporciona separação de responsabilidades, escalabilidade e facilidade de manutenção do código.

### Backend

```bash
src/
├── main/
│   ├── java/
│   │   └── br/edu/ucs/matriculas/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── dao/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       ├── seed/
│       │   └── matriculas.csv
│       └── application.properties
```

### Frontend
```bash
src/
├── components/
│   ├── GraficoLinhasAno.jsx
│   ├── GraficoTopCursosModalidade.jsx
│   └── GraficoTopCursosEstadoModalidade.jsx
├── pages/
│   ├── Menu.jsx
│   ├── Dashboard.jsx
│   ├── GraficoTotalAno.jsx
│   ├── RankingModalidade.jsx
│   ├── RankingEstadoModalidade.jsx
│   └── TopCursos2022.jsx
├── services/
│   └── api.js
├── App.jsx
├── index.css
└── main.jsx
```
---

## 🧠 Funcionalidades Implementadas no Frontend

O frontend da aplicação implementa todas as funcionalidades previstas no escopo do projeto, com foco em visualização, interatividade e navegação intuitiva:

#### 📊 Dashboard Completo
- Combinação de filtros por **estado** e **modalidade**
- Exibição simultânea de:
  - 📈 Gráfico de linha com total de matrículas por ano
  - 🏠 Ranking dos 10 cursos mais matriculados por modalidade
  - 🗺️ Ranking dos 10 cursos mais matriculados por estado e modalidade
- Histórico de consultas:
  - Registra todas as buscas aplicadas
  - Permite **reaplicar consultas anteriores**
  - Permite **limpar o histórico**

#### 📈 Total de Matrículas por Ano
- Exibido em gráfico de linha
- Filtro interno para selecionar modalidade: **EaD** ou **Presencial**

#### 🏠 Ranking por Modalidade
- Gráfico de barras com os cursos mais populares em 2022
- Filtro de modalidade

#### 🗺️ Ranking por Estado e Modalidade
- Gráfico de barras
- Filtros combinados de estado e modalidade

#### 🏅 Top 10 Cursos com Mais Matrículas em 2022
- Gráfico de barras com os cursos mais matriculados no Brasil em 2022
- Sem filtros — exibe o panorama geral nacional

#### 🔙 Menu Principal
- Tela inicial com acesso para todas as funcionalidades
- Botões personalizados com ícones
- Navegação via React Router sem recarregamento da página

---

## 🔹 Padrão MVC (Model-View-Controller)

O projeto está estruturado nos três principais componentes do MVC:

**1. Model (br.edu.ucs.matriculas.model)**

- Contém as classes de domínio que representam as entidades do sistema. No caso, são:

  - `RegistroMatricula`: Representa os dados de matrícula de cada curso.
  - `ConsultaFiltro`: Representa os critérios de filtro para as consultas no histórico.
  - `ConsultaResultado`: Encapsula os resultados das consultas realizadas.
  - `HistoricoConsulta`: Armazena os dados das últimas consultas realizadas para acesso rápido.

**2. View**

- Neste projeto, como é uma aplicação backend, a camada View é representada pelos **endpoints REST da API**. A interface gráfica e interação com o usuário é realizada pelo frontend.

**3. Controller (br.edu.ucs.matriculas.controller)**

- Contém os controladores REST que são responsáveis por expor os endpoints para o frontend.
- A classe `MatriculaController` gerencia as requisições HTTP e direciona as operações para os serviços correspondentes.
- Endpoints:
  - `/api/consultas/total-por-ano`
  - `/api/consultas/total-por-ano/modalidade`
  - `/api/consultas/total-por-ano/estado`
  - `/api/consultas/total-por-ano/estado-modalidade`
  - `/api/consultas/top-cursos-2022/estado`
  - `/api/consultas/top-cursos-2022/estado-modalidade`
  - `/api/consultas/ranking/cursos-2022`
  - `/api/consultas/ranking/cursos-2022-modalidade`
  - `/api/consultas/historico`
- Os métodos recebem os parâmetros necessários e invocam a camada de serviço para processar a lógica de negócio.

---

## 🔹 Service Layer (Camada de Serviço)

A lógica de negócios é gerida pela classe `MatriculaService`, localizada em:

```bash
src/main/java/br/edu/ucs/matriculas/service/MatriculaService.java
```

- Recebe as requisições dos controllers e processa as regras de negócio.
- Consulta a camada DAO para obter os dados do banco.
- Armazena as duas últimas consultas realizadas no histórico, conforme a regra de negócio definida pelo projeto.

---

## 🗄️ Persistência de Dados

A aplicação utiliza o padrão de persistência baseado em Repository + DAO, utilizando um SGBD relacional (MySQL) para gerenciar os dados conforme recomendado para o projeto. A camada de persistência é responsável por gerenciar o ciclo de vida dos dados no database relacional MySQL, permitindo a execução de operações de leitura e gravação de forma eficiente e estruturada.

## 🔹 DAO Layer (Data Access Object)

A implementação da persistência é realizada através da classe `RegistroMatriculaDAO`, localizada em:

```bash
src/main/java/br/edu/ucs/matriculas/dao/RegistroMatriculaDAO.java
```

A classe utiliza o NamedParameterJdbcTemplate do Spring para:

- Persistir em massa os registros importados do arquivo CSV para a tabela matriculas;
- Realizar consultas agregadas e filtradas diretamente no banco de dados;
- Garantir a execução de comandos SQL de forma otimizada e segura, evitando SQL Injection.

## 🔹 Repository Layer

A interface `MatriculaRepository` define a assinatura do método que gerencia persistência:

```bash
public interface MatriculaRepository {
    void salvarTodos(List<RegistroMatricula> registros);
}
```

Atualmente, a interface está implementada por RegistroMatriculaDAO, utiliza da abstração respeitando o princípio de inversão de dependência (DIP) e permitindo, no futuro, uma fácil migração para Spring Data JPA, caso desejado.

---

## 🌐 Gerenciamento de Perfis (Profiles)

A aplicação possui dois perfis distintos para controle do processo de persistência:

- `dev`: Executa o seed a partir do arquivo matriculas.csv para popular o banco de dados durante o desenvolvimento.
- `prod`: Ignora o processo de seed, assumindo que o banco já está populado.

A configuração dos perfis é definida no arquivo `application.properties`:

```bash
# Ambiente de Desenvolvimento (Dev) — realiza seed no startup
spring.profiles.active=dev

# Ambiente de Produção (Prod) — não realiza seed, assume banco populado
# spring.profiles.active=prod
```

Para alternar entre os perfis, basta comentar/descomentar a linha correspondente no arquivo de configuração.

---

## 🐘 Configuração do Banco

Banco de dados: **MySQL**
Nome do banco: **matriculas_db**

Execute no terminal MySQL:

```bash
mysql -u root -p
CREATE DATABASE matriculas_db;
USE matriculas_db;
```

Após a importação dos dados (via seed ou dump), é possível verificar a carga com:

```bash
SELECT COUNT(*) FROM matriculas;
```

---

## ⚙️ Configuração de Ambiente

_Pré-requisitos necessários para rodar o projeto com sucesso._

**Instale:**

- Java 17+
- Maven
- MySQL
- (Opcional) DBeaver para visualizar o banco

---

## 🚀 Como Executar Localmente

Clone o repositório, que possui tanto o backend quanto o frontend:

```bash
git clone https://github.com/bruchies/matriculas.git
```
#### Backend
1. Acesse o diretório do backend:

```bash
cd matriculas-backend
```
2. Adicione o arquivo CSV `matriculas.csv`:

- Baixe o arquivo através do link: https://drive.google.com/drive/folders/1btHnmneCD6Yh_6_SfEtJxos47KzgIDF2?usp=sharing
- Colar o arquivo em:

```bash
src/main/resources/seed/matriculas.csv
```

Esse arquivo será utilizado para importar os dados para o banco (seed).  
Ao iniciar a aplicação com o perfil **dev**, os dados do CSV serão automaticamente carregados no banco de dados.  
⚠️ O arquivo `matriculas.csv` é muito grande para estar no repositório (limite do GitHub), por isso é necessário adicioná-lo uma vez nesse diretório.  
✅ Caso não deseje utilizar o seed, mas sim importar os dados diretamente por SQL, utilize o arquivo matriculas_dump.sql e execute:

```bash
mysql -u root -p matriculas_db < ~/Downloads/matriculas_dump.sql
```

3. Compile o projeto:

```bash
mvn clean install
```

4. Rode a aplicação do backend:

```bash
mvn spring-boot:run
```
#### Frontend

1. Acesse o diretório do frontend:

```bash
cd matriculas-frontend
```

2. Instale as dependências:

```bash
npm install
```

3. Execute o projeto:

```bash
npm run dev
```

A aplicação estará disponível em http://localhost:5173.

⚠️ Certifique-se de que o backend está rodando em http://localhost:8080 para que o frontend funcione corretamente.

---

## 🧪 Documentação da API com Swagger

A documentação e testes da API REST estão disponíveis automaticamente ao rodar o projeto.
Acesse via navegador:

```bash
http://localhost:8080/swagger-ui/index.html
```

📌 Permite visualizar e testar as rotas disponíveis da API do sistema de matrículas.

---

## 📄 Licença

Este projeto é apenas para fins educacionais, conforme solicitado na disciplina da UCS.
