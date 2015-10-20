package com.melhorCaminho.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.melhorCaminho.dao.MapaDao;
import com.melhorCaminho.dao.PontoDao;
import com.melhorCaminho.dao.RotaDao;
import com.melhorCaminho.model.Rota;

@Path("/rota")
public class RotaResource {

	@GET
	@Path("/lista")
	@Produces("application/xml")
	public List<Rota> listarrotas() {
		return new RotaDao().listarRotas();
	}

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Rota carregarById(@PathParam("id") Integer id) {
		return new RotaDao().listarById(id);
	}

	@GET
	@Path("salvar/{mapa}/{origem}/{destino}/{distancia}")
	@Produces("text/plain")
	public String inserir(@PathParam("mapa") String mapa, @PathParam("origem") String origem,
			@PathParam("destino") String destino, @PathParam("distancia") String distancia) {
		Rota rota = new Rota();
		rota.setMapa(new MapaDao().listarByNome(mapa));
		rota.setOrigem(new PontoDao().listarByNome(origem));
		rota.setDestino(new PontoDao().listarByNome(destino));

		if (distancia != null && !distancia.trim().isEmpty()) {
			rota.setDistancia(Double.parseDouble(distancia.replace(",", ".")));
		}

		if (rota.getMapa() == null || rota.getOrigem() == null || rota.getDestino() == null
				|| rota.getDistancia() == null) {
			return "Favor informar o nome do Mapa, o nome do Ponto de origem, nome do Ponto de destino e distancia (Km)!)";
		}
		new RotaDao().inserir(rota);

		return "Rota adicionada com sucesso!!";
	}

	@GET
	@Path("deletar/{mapa}/{origem}/{destino}")
	@Produces("text/plain")
	public String deletar(@PathParam("mapa") String mapa, @PathParam("origem") String origem,
			@PathParam("destino") String destino) {
		Rota rota = new Rota();
		rota.setMapa(new MapaDao().listarByNome(mapa));
		rota.setOrigem(new PontoDao().listarByNome(origem));
		rota.setDestino(new PontoDao().listarByNome(destino));

		if (rota.getMapa() == null || rota.getOrigem() == null || rota.getDestino() == null) {
			return "Favor informar o nome do Mapa, o nome do Ponto de origem, nome do Ponto de destino e distancia!)";
		}
		rota = new RotaDao().listarByMapaOrigemDestino(rota.getMapa(), rota.getOrigem(), rota.getDestino());
		new RotaDao().deletar(rota.getId());

		return "Rota deletada com sucesso!!";
	}

}
