const fse = require('fs-extra');

const SPRING_STATIC = "../src/main/resources/static";
const SPRING_TEMPLATE = "../src/main/resources/templates/";

/**
 * Copies the react app into the spring framework static structure
 * @author Eduardo Augusto da Silva Leite <eduardodsl@gmail.com>
 */
const main = () => {
    console.log("Copying files to the Spring Framework Structure");
    try{
        fse.copySync('build/static', SPRING_STATIC+"/static");
        fse.copySync('build/index.html', SPRING_TEMPLATE+"/index.html");
        console.log("All files successfully copied!");
    }catch(e){
        console.error(e);
    }
}

main();