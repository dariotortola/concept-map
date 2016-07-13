Idea:
1. El mundo de juego, en plan investigativo sandbox, puede verse como un grafo. Los vértices del grafo son las personas, lugares, objetos, eventos, etc que existen en el mundo
2. Las aristas del grafo son las relaciones entre los elementos: las personas que participaron en un evento, que viven en un lugar, que crean un objeto, etc
3. Tanto las aristas como los vértices tienen distinta información: un lugar puede tener planos, una persona tiene un pasado, unos objetivos, etc
4. Cada fragmento de información puede ser conocido por un grupo de personas

Modelo:
1. Los vértices del grafo son MapObject y sus subclases
2. Las aristas del grafo son Relationship
3. La información son instancias de Information
4. El hecho de que un fragmento sea conocido o no por una persona se implementa con Knowledge
5. El mundo de esa información se guarda en un elemento KnowledgeMap

VERSION 1
Hacemos un mapa con MapObject (PersonObject, PcObject), Relationship, Knowledge
Lo mostramos como grafo

VERSION 2
Añadimos información a cada objeto y relación

DESARROLLO FUTURO
Información basada en archivos (imágenes, sonidos, archivos adjuntos como pdf, word, etc)

Jung utiliza google.guava, pero podríamos usar Java 8 directamente y ahorrarnos esa dependencia