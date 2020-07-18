package com.example.demo.model;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
    @Query
    ("SELECT cidade FROM Cidade cidade WHERE cidade.nome LIKE ?1%")
	public List<Cidade> listarPorLetraEspecifica(String nome);
    
    @Query
    ("SELECT cidade FROM Cidade cidade WHERE cidade.latitude = ?1 AND cidade.longitude = ?2")
	public List<Cidade> listarPorLatitudeLongitude(String latitude, String longitude);

}
