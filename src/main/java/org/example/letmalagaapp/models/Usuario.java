package org.example.letmalagaapp.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    private String id;
    private String nombre;
    private String email;
    private String password;
    private String role = "USER";


}


