package com.melhorCaminho.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.melhorCaminho.model.Mapa;
import com.melhorCaminho.resources.MapaResource;

public class MapaResourceTest {
	MapaResource mapaResource = new MapaResource();

	@Test
	public void testInserir() {
		String retorno = mapaResource.inserir("Mapa1");

		Assert.assertEquals("Mapa 'Mapa1' adicionado com sucesso!!", retorno);
	}

	@Test
	public void testListarmapas() {
		List<Mapa> retorno = mapaResource.listarmapas();

		Assert.assertTrue(retorno != null && !retorno.isEmpty());
	}

	@Test
	public void testCarregarById() {
		List<Mapa> retorno = mapaResource.listarmapas();
		Mapa mapa = mapaResource.carregarById(retorno.get(0).getId());
		Assert.assertNotNull(mapa);
	}

	@Test
	public void testDeletar() {
		String retorno = mapaResource.deletar("Mapa1");
		Assert.assertEquals("Mapa 'Mapa1' deletado com sucesso!!", retorno);
	}

	@Test
	public void testMelhorCaminho() {
		
	}

}
