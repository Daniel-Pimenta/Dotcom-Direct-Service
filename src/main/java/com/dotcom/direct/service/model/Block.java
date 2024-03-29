package com.dotcom.direct.service.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Block {

  @Id
  private String id;
  private Date data;
  private String ip;
  private int porta;
  private String mensagem;
  private String hash;
  private String hashAnt;
  private String publicKey;

  public void Constructor(String id, Date data, String ip, int porta, String hashAnt, String publicKey, String mensagem) {
    this.id = id;
    this.data = data;
    this.ip = ip;
    this.porta = porta;
    this.mensagem = mensagem;
    this.hashAnt = hashAnt;
    this.publicKey = publicKey;
  }

  public String getId() {
    return id;
  }

  public Date getData() {
    return data;
  }

  public String getIp() {
    return ip;
  }

  public int getPorta() {
    return porta;
  }

  public String getMensagem() {
    return mensagem;
  }

  public String getHash() {
    return this.getHashCode();
  }

  public String getHashAnt() {
    return hashAnt;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public void setPorta(int porta) {
    this.porta = porta;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  //public void setHash(String hash) {
  //  this.hash = hash;
  //}
  
  public void setHashAnt(String hashAnt) {
    this.hashAnt = hashAnt;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  private String getHashCode() {
    String block = this.id + this.ip + this.porta + this.data + this.mensagem + this.hashAnt + this.publicKey;
    try {
      /*
       * MessageDigest algorithm = MessageDigest.getInstance("SHA-256"); byte
       * messageDigest[] = algorithm.digest(block.getBytes("UTF-8")); return
       * messageDigest.toString();
       */

      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] byteOfTextToHash = block.getBytes(StandardCharsets.UTF_8);
      byte[] hashedByetArray = digest.digest(byteOfTextToHash);
      String encoded = Base64.getEncoder().encodeToString(hashedByetArray);
      return encoded;

    } catch (Exception e) {
      return null;
    }
  }


}
