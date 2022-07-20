# Customer Repo

## 其他言语

* [English](https://github.com/eduardodsl/customer-repo/blob/main/README.md)
* [Português](https://github.com/eduardodsl/customer-repo/blob/main/README.pt.md)

Customer Repo 程序是作为 DIO/TQI 训练营挑战开发的。与此用程序可以创作一个客户，客户有名字，姓和CEP号码（巴西的邮编）。
除了一个 Spring Framework REST API 以外，也包括一个 ReactJS 前端在根路由作为 API 接口。H2 数据库 被 SQLite 取代。

## 如何用

您需要 Spring 用程序执行和打开 `http://localhost:8080` 在网页浏览器为了开始用。

## React App

前端源代码是在 `/react-app`。前端程序是已经编译到一个 view，您需要再编译的话，可以执行 `npm run build-spring` 命令在它的目录，所有文件会被 `npm run build`
命令编译和复制到 Spring Framework 静止文件目录。