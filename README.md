# TJF Checkin Signup :: Inscrição

Fornece o serviço para a inscrição.


## Modo de uso

Incluir a biblioteca como dependência no `pom.xml` da aplicação:

```xml
<dependency>
    <groupId>com.totvs.tjf</groupId>
    <artifactId>tjf-checkin-signup</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```


## Configurações (application.yml)

No arquivo de configurações _src/main/resources/application.yml_ teremos configurado as informações do banco de dados (PostgreSQL):

```
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/checkin
    username: checkin
    password: checkin!123
```
    
OBS: Deve ser alterado de acordo com o banco criado.

## Subindo o Serviço

Para subirmos o serviço, basta executar como Spring Boot App.

#### Enviando para o Endpoint

De acordo com as configurações, o serviço será exposto na URL http://localhost:8180/api/v1/signup, sendo necessário enviar um POST, conforme exemplo a seguir:

```
{
	"email": "participante@email.com",
	"macAddress": "C3-5B-28-43-C2-82",
	"name": "Nome do participante",
	"provider": "fornecedor"
}
```

* Os campos _email_ e _macAddress_ são do tipo texto (String) e são obrigatórios.

#### Validações

O serviço valida caso o mesmo Email já tenha sido informado.

O serviço também valida caso o mesmo endereço MAC já tenha sido informado.

## Licença

Copyright &copy; 2018 by [TOTVS](https://www.totvs.com)
