package br.com.wgsdev.resfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.wgsdev.resfood.domain.exception.EntidadeEmUsoException;
import br.com.wgsdev.resfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.wgsdev.resfood.domain.model.Cidade;
import br.com.wgsdev.resfood.domain.model.Estado;
import br.com.wgsdev.resfood.domain.repository.CidadeRepository;
import br.com.wgsdev.resfood.domain.service.CadastroEstadoService;

@Service
public class CadastroCidadeService {
	
	private static final String MSG_CIDADE_EM_USO 
	    = "Cidade de código %d não pode ser removida, pois está em uso";

	private static final String MSG_CIDADE_NAO_ENCONTRADA 
		= "Não existe um cadastro de cidade com código %d";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
		
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long cidadeId) {
		try {
			buscarOuFalhar(cidadeId);
			cidadeRepository.deleteById(cidadeId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}
	
	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
		    .orElseThrow(() -> new EntidadeNaoEncontradaException(
		        String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
	}
	
}
