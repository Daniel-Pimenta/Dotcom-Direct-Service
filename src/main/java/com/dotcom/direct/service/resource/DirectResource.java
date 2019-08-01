package com.dotcom.direct.service.resource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dotcom.direct.service.model.Block;
import com.dotcom.direct.service.repository.BlockRepository;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class DirectResource {
  
  private static final Logger log = LoggerFactory.getLogger(DirectResource.class);
  
  @Autowired
  private BlockRepository br;
  

  @GetMapping("/hello")
  public Collection<String> sayHello() {
    return IntStream.range(0, 10).mapToObj(i -> "Hello number " + i).collect(Collectors.toList());
  }

  @GetMapping("/id")
  public Block getId(HttpServletRequest request) {
    String newId = this.getNewId().toUpperCase();
    String ipAddress = request.getRemoteAddr().toString();

    Block block = new Block();
    block.Constructor(newId, new java.util.Date(), ipAddress, 0, "0", null, "PEDIDO DE ID");
    return br.save(block);
  }
  
  @PostMapping("/id")
  public Block postId(@RequestBody Block block, HttpServletRequest request) {
    System.out.println("Post ID");
    Block b = br.findByIdAndMensagem(block.getId(),"PEDIDO DE ID");
    if (b == null) {
      String newId = this.getNewId().toUpperCase();
      String ipAddress = request.getRemoteAddr().toString();

      block.Constructor(newId, new java.util.Date(), ipAddress, 0, "0", block.getPublicKey(), "PEDIDO DE ID");
      return br.save(block);
    }
    return null;
  }

  @GetMapping("/id/{id}")
  public Block getFirstBlock(@PathVariable("id") String id) {
    log.debug("getFirstBlock("+id+")");
    return br.findByIdAndMensagem(id,"PEDIDO DE ID");
  }

  @GetMapping("/list")
  public List<Block> getAllBlock() {
    return br.findAll();
  }
  
  
  private String getNewId() {
    String[] numero = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
    String[] caracteres = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
        "s", "t", "u", "v", "w", "x", "y", "z" };
    StringBuilder id = new StringBuilder();
    int posicao;
    //
    posicao = (int) (Math.random() * caracteres.length);
    id.append(caracteres[posicao]);
    posicao = (int) (Math.random() * caracteres.length);
    id.append(caracteres[posicao]);
    posicao = (int) (Math.random() * caracteres.length);
    id.append(caracteres[posicao]);
    //
    posicao = (int) (Math.random() * numero.length);
    id.append(numero[posicao]);
    posicao = (int) (Math.random() * numero.length);
    id.append(numero[posicao]);
    posicao = (int) (Math.random() * numero.length);
    id.append(numero[posicao]);
    posicao = (int) (Math.random() * numero.length);
    id.append(numero[posicao]);
    return id.toString();
  }
  
}
