package com.melhorCaminho.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.melhorCaminho.model.Rota;
import com.melhorCaminho.resources.RotaResource;

public class RotaResourceTest {
	RotaResource rotaResource = new RotaResource();

	@Test
	public void testListarrotas() {
		List<Rota> retorno = rotaResource.listarrotas();

		Assert.assertTrue(retorno != null && !retorno.isEmpty());
	}

	@Test
	public void testCarregarById() {
		List<Rota> lista = rotaResource.listarrotas();
		Rota retorno = rotaResource.carregarById(lista.get(0).getId());

		Assert.assertNotNull(retorno);
	}

}
