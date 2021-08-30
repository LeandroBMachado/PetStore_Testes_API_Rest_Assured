//Pacote
package petstore;
//Bibliotecas

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

//Classe
public class Pet {

    //Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; //endereço entidade pet

    //Métodos e funções
    public String lerJason(String caminhoJson) throws IOException {

        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //Incluir create-post
    @Test //Identifica o metodo ou função para o testNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJason("db/pet1.json");


        //Sintaxe Ghenkin

        //Dado Quando Então

        given() //Dado
                .contentType("application/json") //comum em API rest  -antigos text/xml
                .log().all()
                .body(jsonBody)
        .when() //Quando
                .post(uri)
        .then()//Então
                .log().all()
                .statusCode(200)
                .body("name",is("Skin")) // validar nome cachorro
                .body("status",is("available")) //validar status
                .body("category.name",is("dog"))
                .body("tags.name",contains("sta"))


        ;
    }

}
