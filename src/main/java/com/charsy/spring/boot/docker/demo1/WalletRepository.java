package com.charsy.spring.boot.docker.demo1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface WalletRepository extends CrudRepository<Wallet, Long> {

}
