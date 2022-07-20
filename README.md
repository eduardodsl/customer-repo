# Customer Repo

## Other Languages

* [Português](https://github.com/eduardodsl/customer-repo/blob/main/README.pt.md)
* [中文](https://github.com/eduardodsl/customer-repo/blob/main/README.zh.md)

Customer Repo is an application developed for the DIO/TQI Bootcamp Challenge. This application basically allows you to
add an customer with name, surname and his CEP code (Brazilian Zip Code). Expanding on the original proposal of building
an REST API using the Spring Framework, it also contains a pre-built ReactJS client in the root route that can interact
with the API. The H2 in-memory database also was replaced by the SQLite Database.

## How to Use

All you need to do is to run the Spring application as usual and open `http://localhost:8080` in your browser to start using it.

## React App

The source of the ReactJS client is in `/react-app` directory. This client is already pre-built into a view, but if you want
to build it, you can run `npm run build-spring` from inside the client directory and all files will be built with the `npm run build`
command and be copied to the Spring Framework static files.