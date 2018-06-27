# ejercicio

Para ejecutar el programa se deben descargar las dependencias, correr MutantApp.java y acceder a través de localhost:8080

Lista de recursos:

/mutant
métodos: post
/stats
métodos: get, delete

Especificaciones:

/mutant:
- post:application/json

ejemplo:
{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

respuestas: 
200-OK: si es mutante, 
403-Forbidden: si es humano, 
400-Bad Request: si no se ingresa una matriz NxN con carácteres validos A,T,G,C


/stats
- get:
respuesta:
200-OK:
body:application/json

ejemplo:
{
"count_mutant_dna":40,
"count_human_dna":100,
"ratio":0.4
}

detalle: ratio retorna nulo si no hay adn humano registrado.

- delete:
respuesta:
200-OK: elimina los registros de adn para reiniciar.
