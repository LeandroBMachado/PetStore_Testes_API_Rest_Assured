//Pacote
package petstore;
//Bibliotecas

import org.testng.annotations.BeforeClass;
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
    @Test(priority = 1) //Identifica o metodo ou função para o testNG
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
                .body("category.name",is("LBM245678"))
                .body("tags.name",contains("data"))

        ;

    }
    @Test(priority = 2)
    public void consultarPet(){
        String petid = "19881231";

        String token =
        given()
                .contentType("application/json")
                .log().all()
        .when()
            .get(uri + "/" + petid)
        .then()
            .log().all()
            .statusCode(200)
            .body("name",is("Skin"))
            .body("category.name",is("LBM245678"))
            .body("status",is("available"))
        .extract()
                .path("category.name")
         ;

        System.out.println("O token é:" + token);

    }
    @Test(priority = 3)
    public void  alterarPet() throws Exception {

        String jsonBody = lerJason("db/pet2.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)

        .when()
                .put(uri)

        .then()
                .log().all()
                .statusCode(200)
                .body("name",is("Skin"))
                .body("status",is("sold"))





        ;
    }


    @Test(priority = 4)
    public void excluirPet() throws IOException {
        String petid = "19881231";
        String jsonBody = lerJason("db/pet2.json");


        given()
                .contentType("application/json")
                .log().all()

        .when()
                .delete(uri + "/" + petid)
        .then()
                .log().all()
                .statusCode(200)
                .body("code",is(200))
                .body("type",is ("unknown"))
                .body("message",is(petid))


        ;
    }

}
