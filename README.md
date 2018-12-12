# Sistema de Biblioteca Escolar

## Manual de Instruções
Tendo todas as ferramentas citadas mais abaixo já instaladas, siga as instruções abaixo:
- Clone o projeto que está no repositório https://github.com/lclucasgr/tcc.git em seu workspace.
- Descompacte esse projeto
- Com o Netbeans já instalado abra o projeto que foi clonado
- Faça o download das dependências do Maven.
- Acesse esse endereço http://localhost/phpmyadmin/ e crie o banco de dados `tcc_db`.
- Mude os dados de acesso ao banco de dados local de acordo com os da sua máquina local, no arquivo `persistence.xml`, localizado em `/src/main/resources/META-INF`.
- Exemplo:
  - `<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/seu_db"/>`
  - `<property name="javax.persistence.jdbc.user" value="seuuser"/>`
  - `<property name="javax.persistence.jdbc.password" value="suasenha"/>`
- Se for a primeira execução do sistema na máquina ou a primeira após deletar o banco de dados, altere o parametro de `value` para  `create` como mostra a linha abaixo, caso contrario altere para `none`
  - `<property name="javax.persistence.schema-generation.database.action" value="create"/>`
- CRIE A TABELA `roles` com os campos com o nome `email` do tipo `VARCHAR(255)` e campo `name` do tipo `VARCHAR(100)`
- CRIE A TABELA `users` com os campos com o nome `email` do tipo `VARCHAR(255)` e campo `senha` do tipo `VARCHAR(100)`

OBS: Se deletar o banco de dados é necessário recriar essas 2 tabelas novamente

## Instruções para Teste de Autenticação
- Crie um registro na tabela roles no mysql com o `email` fulanodetal@gmail.com e `name` `biblio`
- Crie um registro na tabela users no mysql com o `email` fulanodetal@gmail.com e com a `senha` `biblioteca` encriptada nesse site: https://passwordsgenerator.net/sha256-hash-generator/

OBS: o email não precisa ser esse porem deve estar presente nas duas tabelas, somente é autenticado usuários que tiverem o campo com o nome `biblio` na tabela `roles` e o email igual na tabela `users` e `roles`, a senha pode ser qualquer coisa desde que esteja encriptado no site mostrado anteriormente

- Execute a pagina `index.xhtml` e autentique-se com esses dados.

## Tecnologias
- JSF 2.2
- JPA (Hibernate)
- Primefaces
- MaterializeCSS

## Ferramentas que devem ser instaladas (irei dar do meu HD externo)
- Netbeans 8.2
- Xampp
- Tomcat 8.0.41
- Maven
- WinRAR

OBS: O Netbeans poderá ser instalado junto com o Tomcat, quando for a instalar o Netbeans desmarque a opções instalar TOMCAT e instalar Glassfish, instale a versão do Tomcat 8.0.41 que eu vou mandar no HD externo, na pasta que eu eu te mandei vai ter um Tutorial de Instalação do TOMCAT no NetBeans

## Requisitos 
- Windows 10 (64 bits)

## Versão Corrente
0.0.2 - Release 10/12/2018

## Duvidas
- email : lclucasgr@gmail.com
- whatsapp : (42)99842-4123
