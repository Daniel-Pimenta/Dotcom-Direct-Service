package com.dotcom.direct.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dotcom.direct.service.model.Block;

public interface BlockRepository extends MongoRepository<Block, String> {
  
  
  Block findByIdAndMensagem(String Id, String Mensagem);

}
