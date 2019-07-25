package com.dotcom.direct.service.resource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class Direct {
 
  
  @GetMapping("/hello")
  public Collection<String> sayHello() {
    return IntStream.range(0, 10).mapToObj(i -> "Hello number " + i).collect(Collectors.toList());
  }

  @GetMapping("/hello2")
  public String sayHello2() {
    String[] numero     = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] caracteres = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    StringBuilder senha = new StringBuilder();
    int posicao;
    //
    posicao = (int) (Math.random() * caracteres.length);
    senha.append(caracteres[posicao]);
    posicao = (int) (Math.random() * caracteres.length);
    senha.append(caracteres[posicao]);
    posicao = (int) (Math.random() * caracteres.length);
    senha.append(caracteres[posicao]);
    //
    posicao = (int) (Math.random() * numero.length);
    senha.append(numero[posicao]);
    posicao = (int) (Math.random() * numero.length);
    senha.append(numero[posicao]);
    posicao = (int) (Math.random() * numero.length);
    senha.append(numero[posicao]);
    posicao = (int) (Math.random() * numero.length);
    senha.append(numero[posicao]);
    return senha.toString().toUpperCase();
  }
  
}
