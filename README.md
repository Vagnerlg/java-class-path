
# Projeto de Estudo sobre classPath do Java

Este projeto tem como objetivo demonstrar o funcionamento do `classPath` e `jar` no **Java**.

> É necessário ter o Java instalado.

> O estudo foi feito com Java na versão 21.

---

### O que é o classPath?

O **classPath** é o caminho onde o Java procura os arquivos `.class` e `.jar` necessários para compilar ou executar um programa.

---

## Exemplos de uso do classPath

### Ex. 1 - HelloWorld

O código mais simples do **Java**, com um único arquivo e sem dependências externas.

Para este exemplo, temos o arquivo `src/helloWorld/Main.java`.

Primeiro, vamos compilá-lo com o comando:

```sh
javac -d out src/helloWorld/Main.java
```

Para executar, precisamos passar a opção `-cp out` para informar ao Java a localização da raiz do projeto:

```sh
java -cp out helloWorld.Main
```

A saída será:

```sh
Hello, World!
```

---

### Ex. 2 - Book (vários arquivos `.java`)

Neste exemplo, executamos um projeto Java com múltiplos arquivos — no caso, dois.

Primeiro, vamos compilar com o comando:

```sh
javac -d out src/book/Main.java
```

Nesse caso, teremos o seguinte **erro**:

```sh
src/book/Main.java:6: error: package book.entity does not exist
        var book = new book.entity.Book(
```

Isso ocorre porque é necessário compilar **todos** os arquivos pertencentes ao projeto `book`:

```sh
javac -d out src/book/Main.java src/book/entity/Book.java
```

Agora sim, tudo certo! Podemos executar novamente:

```sh
java -cp out book.Main
```

A saída será:

```sh
Book{title='Memórias Póstumas de Brás Cubas', author='Machado de Assis', yearPublished=1881}
```

---

### Ex. 3 - Projeto Developer (dependência `.jar`)

Vamos compilar da mesma forma que o projeto `book` e observar o erro:

```sh
javac -d out src/developer/Main.java src/developer/entity/Developer.java
```

Esse comando gera um erro porque o Java não reconhece o pacote `org.json.JSONObject`.

Para resolver, precisamos informar ao `javac` a localização das bibliotecas externas com a opção `-cp`, como no comando abaixo:

```sh
javac -cp lib/json.jar -d out src/developer/Main.java src/developer/entity/Developer.java
```

Nesse caso, o `-cp` define o classPath, permitindo que o Java localize a biblioteca `json`.

Para executar, também usamos `-cp`, agora incluindo tanto a pasta `out` quanto o arquivo `json.jar`:

```sh
java -cp lib/json.jar:out developer.Main
```

A saída será:

```json
{
    "skills": [
        "Java",
        "Python",
        "JavaScript"
    ],
    "city": "New York",
    "name": "John Doe",
    "age": 30
}
```

---

## Pacotes `.jar`

#### O que é Jar?

Um JAR (Java ARchive) é um arquivo compactado que agrupa múltiplos arquivos .class e recursos (como imagens e bibliotecas) em um único pacote para facilitar a distribuição e execução de aplicações Java.

### Gerar `.jar` do projeto Developer

Vamos transformar nosso projeto, que atualmente está em arquivos `.class`, em um pacote `.jar`.

Para isso, usamos um arquivo de configuração chamado `MANIFEST.MF`, que contém informações úteis para o comando `jar`.

```sh
jar cfm out/app.jar MANIFEST.MF -C out developer
```

Explicando o comando:

- `c` → cria o `.jar`
- `f` → define o nome do arquivo `.jar`
- `m` → inclui o `MANIFEST.MF`
- `-C out developer` → muda para o diretório `out/` e inclui tudo a partir da pasta `developer`

Para executar este jar.

```sh
java -cp lib/json.jar:out/app.jar developer.Main
````

> Observação: esse `.jar` **ainda depende** do `json.jar`. A seguir, veremos como criar um `.jar` independente.

---

### Fat Jar

No **Fat Jar**, incluímos em um único `.jar` tanto as classes do projeto quanto as de bibliotecas externas.

#### Extraindo os `.class` do `json.jar`:

```sh
cd out
jar xf ../lib/json.jar
cd ..
```

Se você observar, os arquivos `.class` do pacote `org.json` estarão dentro da pasta `out/`.

Agora, crie o novo `.jar` com:

```sh
jar cfm out/app-fat.jar MANIFEST.MF -C out .
```

Para executar:

```sh
java -jar out/app-fat.jar
```

Nesse estudo trabalhamos um pouco dos recursos do java em relação ao classPath e Jar.