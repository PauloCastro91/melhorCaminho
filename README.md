# melhorCaminho
Configuração: 

- O projeto foi criado utilizando MAVEN, por isso não é necessário configurar os jars para a execução.

- Criar um banco de dados nomeado "melhor_caminho" com o usuário "root" e senha "linux" (ou alterar a configuração no arquivo /src/hibernate.cfg.xml).


Utilização:
	Para a criação do WebService foi utilizado o "Jersey", todos os métodos utilizam de métodos GET para receber informações, pontanto, para executar qualquer comando será somente necessário passar os parametros através dos navegador.

Mapa:
	
		Listar todos os mapas cadastrados:
			http://localhost:8080/MelhorCaminho/mapa/lista
		
		Carregar o mapa por id
			http://localhost:8080/MelhorCaminho/mapa/{id}

		Salvar novo mapa:
			http://localhost:8080/MelhorCaminho/mapa/salvar/{nome}

		Deletar mapa:
			http://localhost:8080/MelhorCaminho/mapa/deletar/{nome}


Ponto:
		Listar todos os pontos cadastrados:
			http://localhost:8080/MelhorCaminho/ponto/lista
		
		Carregar o ponto por id
			http://localhost:8080/MelhorCaminho/ponto/{id}

		Salvar novo ponto:
			http://localhost:8080/MelhorCaminho/ponto/salvar/{nome}

		Deletar ponto:
			http://localhost:8080/MelhorCaminho/ponto/deletar/{nome}

	

Rota: 
		Listar todos os rotas cadastrados:
			http://localhost:8080/MelhorCaminho/rota/lista
		
		Carregar o rota por id
			http://localhost:8080/MelhorCaminho/rota/{id}

		Salvar novo rota:
			http://localhost:8080/MelhorCaminho/rota/salvar/{mapa}/{origem}/{destino}/{distancia}

		Deletar rota:
			http://localhost:8080/MelhorCaminho/rota/deletar/{mapa}/{origem}/{destino}/

*{mapa}: mapa cadastrado no banco de dados.
*{origem}: nome do ponto de origem
*{destino}: nome do ponto de destino
*{distancia}: valor numérico inteiro ou decimal.


Teste:

	Para testes foi utilizado classes JUnit, que podem ser encontradas no caminho: src/com/melhorCaminho/test/
