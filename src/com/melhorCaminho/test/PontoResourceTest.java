package com.melhorCaminho.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.melhorCaminho.model.Ponto;
import com.melhorCaminho.resources.PontoResource;



public class PontoResourceTest {
	PontoResource pontoResource = new PontoResource();
	private static final String nome = "Teste";

	@Test
	public void testInserir() {
		String retorno = pontoResource.inserir(nome);
		Assert.assertEquals("Ponto '" + nome + "' inserido com sucesso!!", retorno);
	}

	@Test
	public void testListarPontos() {
		List<Ponto> lista = pontoResource.listarPontos();
		Assert.assertTrue(lista != null && !lista.isEmpty());
	}

	@Test
	public void testCarregarById() {
		List<Ponto> lista = pontoResource.listarPontos();
		Ponto ponto = pontoResource.carregarById(lista.get(0).getId());
		Assert.assertNotNull(ponto);
	}

	@Test
	public void testDeletar() {
		String retorno = pontoResource.deletar(nome);
		Assert.assertEquals("Ponto '" + nome + "' deletado com sucesso!!", retorno);
	}

}
