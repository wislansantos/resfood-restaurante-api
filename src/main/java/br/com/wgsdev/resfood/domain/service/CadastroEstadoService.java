package br.com.wgsdev.resfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.wgsdev.resfood.domain.exception.EntidadeEmUsoException;
import br.com.wgsdev.resfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.wgsdev.resfood.domain.model.Estado;
import br.com.wgsdev.resfood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	public static final String MSG_ESTADO_EM_USO
	    = "Estado de código %d não pode ser removido, pois está em uso";
	
	public static final String MSG_ESTADO_NAO_ENCONTRADO
	    = "Não existe um cadastro de estado com código %d";

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void excluir(Long estadoId) {
		try {
			buscarOuFalhar(estadoId);
			estadoRepository.deleteById(estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}
	
	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId)
		    .orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId)));
	}
	
}
