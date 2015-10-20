package com.melhorCaminho.resources;

import java.util.LinkedHashSet;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.melhorCaminho.dao.MapaDao;
import com.melhorCaminho.dao.PontoDao;
import com.melhorCaminho.model.Mapa;
import com.melhorCaminho.model.Ponto;
import com.melhorCaminho.model.Rota;
import com.melhorCaminho.util.CalculaCaminho;

@Path("/mapa")
public class MapaResource {

	@GET
	@Path("/lista")
	@Produces("application/xml")
	public List<Mapa> listarmapas() {
		return new MapaDao().listarTodos();
	}

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Mapa carregarById(@PathParam("id") Integer id) {
		return new MapaDao().listarById(id);
	}

	@GET
	@Path("salvar/{nome}")
	@Produces("text/plain")
	public String inserir(@PathParam("nome") String nome) {
		Mapa mapa = new Mapa();
		mapa.setNome(nome);
		new MapaDao().inserir(mapa);

		return "Mapa '" + mapa.getNome() + "' adicionado com sucesso!!";
	}

	@GET
	@Path("deletar/{nome}")
	@Produces("text/plain")
	public String deletar(@PathParam("nome") String nome) {
		MapaDao dao = new MapaDao();

		Mapa mapa = dao.listarByNome(nome);

		if (mapa == null) {
			return "Mapa '" + nome + "' nao encontrado!!";
		} else {
			dao.deletar(mapa.getId());
		}

		return "Mapa '" + nome + "' deletado com sucesso!!";
	}

	@GET
	@Path("melhor_caminho/{mapa}/{origem}/{destino}/{autonomia}/{valor}")
	@Produces("text/plain")
	public String melhorCaminho(@PathParam("mapa") String mapa, @PathParam("origem") String origem,
			@PathParam("destino") String destino, @PathParam("autonomia") String autonomia,
			@PathParam("valor") String valor) {
		Mapa mapaAtual = new MapaDao().listarByNome(mapa);
		Ponto origemAtual = new PontoDao().listarByNome(origem);
		Ponto destinoAtual = new PontoDao().listarByNome(destino);

		CalculaCaminho calcula = new CalculaCaminho();
		calcula.rota(mapaAtual, new LinkedHashSet<Rota>(), origemAtual, destinoAtual, 0.0);

		if (calcula.getMelhorCaminho() == null || calcula.getMelhorCaminho().isEmpty()) {
			return "Caminho nao encontrado!";
		}else if (autonomia == null || autonomia.trim().isEmpty()){
			return "Preencher autonomia!";
		}else if (valor == null || valor.trim().isEmpty()){
			return "Preencher valor!";
		}

		StringBuilder retorno = new StringBuilder(origem + " ");

		for (Rota r : calcula.getMelhorCaminho()) {
			retorno.append(r.getDestino().getNome() + " ");
		}
		
		Double autonomia_real 	= Double.parseDouble(autonomia.replace(",","."));
		Double valor_real 		= Double.parseDouble(valor.replace(",","."));
		
		Double total = (calcula.getMenorDist() / autonomia_real ) * valor_real;

		retorno.append(" com custo de " + total  + ".");
		
		return retorno.toString();
	}
}
