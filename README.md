# AND-PERSISTENCIA-FICHEROS
Persistencia de datos en ficheros de recurso raw y ficheros externos.

Se implementan dos actividades. Descomenar en Manifiest.xml aquella que se quiera ejecutar y comentar la otra.
Las actividades son:

* ResourceFileActivity: Realiza persistencia en fichero de recurso raw. Este fichero forma parte de la aplicación, se puede referenciar como un recurso más, y se empaqueta con la app cuando se genera su fichero apk. 
La actividad implementa la lectura de un fichero de texto plano y otro ejemplo de lectura de un fichero xml.

* FicheroExternoActivity: Realiza persistencia en un fichero externo (no fiechero de recurso raw).
