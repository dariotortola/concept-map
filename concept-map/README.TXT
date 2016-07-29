Mind maps
https://en.wikipedia.org/wiki/Mind_map
Básicamente se pone un concepto central y se van encadenando conceptos relacionados
En nuestro caso no es suficiente porque no tenemos un tema central

http://www.grotto-networking.com/JUNG/JUNG2-Tutorial.pdf

JUNG está dándome algunos fallos con visualizaciones básicas, como ver el nombre de los edges (VertexLabelTransformer)
Voy a ponerme con otras cosas mientras para ver si lo arreglan en algún momento, pero si no, tendré que ponerme yo mismo
Por otro lado, puede que tampoco fuera mala idea, considerando las cosas extrañas que me parecen con lo de sus tagging interfaces, etc

To edit information of MapItems we need first something to edit the common fields (name and known) and then several things to edit its information's content

Para visualizar necesitamos un layout: a partir de un grafo da unas coordenadas para sus elementos

Requisitos:
zoom sobre grafo o sobre grafo y componentes (podemos hacerlo con Graphics2D, create y scale) y dibujar con ello

Tenemos que considerar distintos sistemas de coordenadas:
- graph layout
- espacio de imagen (la vista sobre "el universo")
- espacio visible (la "ventana" al espacio de imagen)

De graph layout tenemos que traducir a espacio de imagen
De espacio de imagen tenemos que traducir a espacio visible
Pintamos sobre el espacio visible

Para mostrarlo necesitamos:
- Calculamos las coordenadas de cada vértice con el graph layout
- Transformamos al espacio imagen
- Transformamos al espacio visible

Queremos admitir dos modos de zoom:
El primer modo sólo modifica la separación entre los puntos
El segundo modo modifica el tamaño de los elementos

