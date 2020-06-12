# Calculo de Latencia
Tarea Materia Modelo de Redes. Programa que calcula la latencia entre varios nodos (routers) y define cuál es el mejor camino entre un nodo A a un nodo B. 
La información con respecto a los nodos (número de nodos, conexiones entre los nodos, tiempo de cola, velocidad de enlace, distancia entre nodos, tamaño de paquete, nodo inicio, nodo destino) se define desde un archivo de texto con la siguiente sintaxis:

## Archivo de texto
numeroNodos, numeroEnlaces
numeroNodo0,tiempo de cola(s)
  .
  . Para cada nodo
  .
nodoOrigen, nodoDestino, velocidadEnlace(Mbps), distancia(m), datosControl, datosUsuario
  . 
  . Para cada enlace
  .
tamañoPaquete(Gb)
NodoInicio, NodoDestino
