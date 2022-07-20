# Customer Repo

## Outros Idiomas

* [English](https://github.com/eduardodsl/customer-repo/blob/main/README.md)
* [中文](https://github.com/eduardodsl/customer-repo/blob/main/README.zh.md)

Customer Repo é uma aplicação desenvolvida para o desafio do bootcamp da DIO/TQI. Essa aplicação basicamente permite
que se crie um cliente com nome, sobrenome e seu número CEP. Além da proposta original de construir uma API REST com
o Spring Framework, ele também contém um cliente em ReactJS na rota raíz que pode interagir com a API. O banco de dados
em memória H2 também foi substituído por um banco de dados SQLite.

## Como Usar

Tudo o que você precisa fazer é rodar a aplicação Spring como de costume e abrir `http://localhost:8080` em seu navegador para começar a usar.

## React App

O código do cliente ReactJS está no diretório `/react-app`. Esse cliente já é pré-compilado em uma view, mas se você quiser compilar novamente,
você pode executar o comando `npm run build-spring` dentro de seu diretório e todos os arquivos serão compilados com o comando `npm run build`
e copiados para a área de arquivos estáticos do Spring Framework.