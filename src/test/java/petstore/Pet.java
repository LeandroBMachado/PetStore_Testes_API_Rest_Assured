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

//Classe
public class Pet {

    //Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; //endereço pet

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
                .contentType("applicaton/json") //comum em API rest  -antigos text/xml
                .log().all()
                .body(jsonBody)
        .when() //Quando
                .post(uri)
        .then()//Então
                .log().all()
                .statusCode(200)

        ;
    }

}
