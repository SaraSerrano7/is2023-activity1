# Activitat 1

## Importació del projecte

Per carregar el projecte al vscode, primer executar la següent comanda dins del directori arrel del projecte:

```bash
scala-cli setup-ide .
```

Una vegada executada, ja podrem obrir el projecte al vscode fent, per exemple:

```bash
code .
```

## Consells de realització

L'ordre suggerit de resolució és el següent:

  1. Exercicis de `List` que fan servir les llistes que hem presentat als apunts. He replicat els mètodes que podeu utilitzar per a que no els hagueu de copiar dels apunts, a més d'incloure informació que pot ser útil de cara a les vostres solucions.

  2. Exercicis de `StdLib`, que fan servir les llistes estàndar de Scala i que us servirà per explorar part de l'API que fareu servir pels problemes següents

  3. Exercicis sobre arbres binaris de cerca `BST`

  4. Exercici del sistema de repartició d'escons seguint la regla d'Hondt (aquesta funció val el triple que les altres)

     A l'enllaç [Procediment Electoral](https://www.parlament.cat/pcat/parlament/que-es-el-parlament/procediment-electoral/), concretament a la secció **El resultat electoral** en trobareu informació.

     Una de les funcions que usareu és `sortBy`, que usa paràmetres *implícits*. Per entendre millor la idea dels implícits, llegiu la següent documentació:

        * [Contextual Abstractions](https://docs.scala-lang.org/scala3/book/ca-contextual-abstractions-intro.html), en particular la secció sobre *Given Instances and Using Clauses*
        * [Documentació del trait Ordering](https://www.scala-lang.org/api/2.13.8/scala/math/Ordering.html)

No cal que us preocupeu inicialment d'aspectes d'eficiència o de si la solució es *stack-safe* o no, però es valorarà que resoleu un mateix problema de diferents maneres amb diferents característiques.

En alguns casos indico que la solució que busquem es usant p.e. un `foldRight`. Si no trobeu aquesta solució, podeu fer-ne una diferent.

Com hem fet a classe, el procediment a seguir pot ser:

* solució evident/intuïtiva (possiblement via pattern-matching i/o recursivitat explícita)
* solució usant altres combinadors
* anàlisi de la/les solució/ns anteriors en termes d'eficiència, stack-safeness, etc.
* proposta d'altres solucions

Per cada fitxer he creat una petita classe de proves per a que podeu afegir tests que comprovin el funcionament del vostre codi.

També podeu usar worksheets per a fer-ho interactivament (teniu un exemple de worksheet al directori `worksheets`)

Si voleu saber una mica més les possibilitats de la biblioteca de tests, podeu consultar la seva documentació a [munit homepage](https://scalameta.org/munit/)

## Què heu d'entregar

* Projecte amb la resolució dels exercicis, amb els tests o workflows que heu afegit.

  * Per poder tenir vàries solucions del mateix problema podeu donar-lis noms numerats (p.e. `partition`, `partition2`, `partition3`, etc.)

* Petit informe, en PDF, explicant el procés de resolució de cada problema
