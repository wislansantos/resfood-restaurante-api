package br.com.wgsdev.resfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.wgsdev.resfood.domain.exception.EntidadeEmUsoException;
import br.com.wgsdev.resfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.wgsdev.resfood.domain.model.Cozinha;
import br.com.wgsdev.resfood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código %d", cozinhaId)));
			cozinhaRepository.deleteById(cozinhaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
		}
	}
	
}
