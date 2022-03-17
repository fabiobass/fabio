package br.com.fabio.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fabio.curso.domain.Categoria;
import br.com.fabio.curso.repositories.CategoriaRepository;
import br.com.fabio.curso.services.exceptions.DataIntegrityException;
import br.com.fabio.curso.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	// find TRADUÇÂO encontrar
	public Categoria find(Integer id) {
		 Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		} 
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		obj.setId(null);
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		try {
			find(id);
			repo.deleteById(id);	
		}
		catch(DataIntegrityViolationException e){
			
			throw new DataIntegrityException("Não é possível excluir "
					+ "categorias que possui produtos");
		}
	}
}
