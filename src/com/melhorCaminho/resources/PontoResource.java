package com.melhorCaminho.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.melhorCaminho.dao.PontoDao;
import com.melhorCaminho.model.Ponto;

@Path("/ponto")
public class PontoResource {

	@GET
	@Path("/lista")
	@Produces("application/xml")
	public List<Ponto> listarPontos() {
		return new PontoDao().listarPontos();
	}

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Ponto carregarById(@PathParam("id") Integer id) {
		return new PontoDao().listarById(id);
	}

	@GET
	@Path("salvar/{nome}")
	@Produces("text/plain")
	public String inserir(@PathParam("nome") String nome) {
		Ponto ponto = new Ponto();
		ponto.setNome(nome);
		new PontoDao().inserir(ponto);

		return "Ponto '" + nome + "' inserido com sucesso!!";
	}

	@GET
	@Path("deletar/{nome}")
	@Produces("text/plain")
	public String deletar(@PathParam("nome") String nome) {
		PontoDao dao = new PontoDao();

		Ponto ponto = dao.listarByNome(nome);

		if (ponto == null) {
			return "Ponto '" + nome + "' nao encontrado!!";
		} else {
			dao.deletar(ponto.getId());
		}

		return "Ponto '" + nome + "' deletado com sucesso!!";
	}

}
