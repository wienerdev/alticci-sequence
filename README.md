<br>
<h1 align="center">
Calculadora Alticci Sequence (Backend) ⚛️
</h1>
<br>

## 💬 Sobre o repositório

A Calculadora Alticci Sequence calcula um valor da sequência Alticci com base em um índice passado, na qual é definida da seguinte forma:

n=0 => a(0) = 0

n=1 => a(1) = 1

n=2 => a(2) = 1

n>2 => a(n) = a(n-3) + a(n-2)

O serviço principal tira partido de cálculos passados para acelerar cálculos futuros por meio do caching (utilizando memoização). Além do serviço principal, há também um serviço pelo Spring Cache, para demonstrar a diferença de performance quando não é utilizada a memoização.

## ⚠ Pré-requisitos para execução do projeto

* Java 11 ou versões superiores
* Maven

## 📌 Como utilizar?

Para utilizar a Calculadora Alticci Sequence em produção, basta acessá-lo diretamente no seu navegador. [Clique aqui!](https://wienerdev.github.io/alticci-sequence-angular/)

Para executar o projeto, digite o seguinte comando no diretório raiz:

```
mvn spring-boot:run 
```

Após o build da aplicação, acesse o [Swagger](http://localhost:8080/swagger-ui/index.html#/) para testar os endpoints.

Você também pode rodar o front-end localmente, e testar via interface. [Clique aqui para navegar ao repositório](https://github.com/wienerdev/alticci-sequence-angular).

## 📲 Serviços disponíveis para testes

### Alticci Sequence Number com Memoization
```
Method: GET
URL: http://localhost:8080/alticci/memoizationCache/{n}
{n}: índice a ser passado.
```

### Checar Cache Memoizado
```
Method: GET
URL: http://localhost:8080/alticci/checkMemoCache
```

### Limpar Cache Memoizado
```
Method: POST
URL: http://localhost:8080/alticci/clearMemoCache
```

### Alticci Sequence Number com Spring Cache
```
Method: GET
URL: http://localhost:8080/alticci/springCache/{n}
{n}: índice a ser passado.
```

## 🧠 Links importantes

* [Documentação oficial do Lombok](https://projectlombok.org/)
* [Documentação oficial do Map Struct](https://mapstruct.org/)
* [Referência para o padrão arquitetural REST](https://restfulapi.net/)
* [Palheta de atalhos de comandos do IntelliJ](https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
* [Site oficial do Spring](https://spring.io/)
* [Site oficial do Spring Initialzr para setup do projeto](https://start.spring.io/)
* [SDKMan! para gerenciamento e instalação do Java e Maven](https://sdkman.io/)

---
