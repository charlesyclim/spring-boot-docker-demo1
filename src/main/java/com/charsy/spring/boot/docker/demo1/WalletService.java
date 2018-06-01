package com.charsy.spring.boot.docker.demo1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.charsy.spring.boot.docker.demo1.Wallet;
import com.charsy.spring.boot.docker.demo1.WalletRepository;

@Service
@Transactional(readOnly=true)
public class WalletService {

	@Autowired
	private WalletRepository repo;
	
	public Iterable<Wallet> getEntities() {
		// TODO Auto-generated method stub
		Iterable<Wallet> wallets = repo.findAll();
		return wallets;
	}

	public Wallet getEntity(Long id) {
		// TODO Auto-generated method stub
		Optional<Wallet> wallet = repo.findById(id);
		if (wallet.isPresent()) return wallet.get();
		return null;
	}

	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public Wallet create(Wallet entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public Wallet update(Long id) {
		// TODO Auto-generated method stub
		Optional<Wallet> wallet = repo.findById(id);
		if (wallet.isPresent()) return repo.save(wallet.get());
		return null;
	}

	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		Optional<Wallet> wallet = repo.findById(id);
		if (wallet.isPresent()) {
			repo.delete(wallet.get());
			return true;
		}
		return false;
	}

}
