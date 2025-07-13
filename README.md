# Simulador de Alquimia com Grafos

## Descrição

Projeto que calcula a quantidade mínima de hidrogênio necessária para produzir ouro a partir de receitas alquímicas representadas como grafos direcionados ponderados. Baseado no Trabalho 2 da disciplina de Algoritmos e Estruturas de Dados II.

## Funcionalidades

* Cálculo de hidrogênios via algoritmo DFS adaptado.
* Leitura de receitas em arquivos `.txt`.
* API REST com endpoint `/api/graph` para processar receitas.
* Suíte de testes automatizados: unitários, de integração e de propriedades (JUnit, JQWik, Mockito).

## Tecnologias Utilizadas

* Java 21
* Maven 3.x
* JUnit, JQWik e Mockito para testes
* BigInteger para manipulação de grandes quantidades

## Pré-requisitos

* Java 21 instalado
* Maven 3.x instalado

## Instalação e Execução

```bash
# Clonar o repositório
git clone https://github.com/gabrielteiga/Grafos-Alest2.git
cd Grafos-Alest2

# Compilar e instalar dependências
mvn clean compile

# Executar todos os testes automatizados
mvn clean verify

# (Opcional) Iniciar aplicação
mvn spring-boot:run
```

## Estrutura do Projeto

* `src/main/java` : Implementação do grafo, algoritmos e API;
* `src/main/resources` : Arquivos de receita (`.txt`);
* `src/test/java` : Suíte de testes unitários, de integração e de propriedades.

## Estrutura de Dados e Algoritmo

O grafo é representado por um `HashMap<String, Vertice>`, onde cada vértice mantém uma lista de adjacências em `ArrayList<Aresta>`. A montagem do grafo e a execução do algoritmo adaptado de DFS garantem complexidade O(n) para calcular a quantidade de hidrogênios até o vértice “ouro”.

## Resultados

Os testes confirmaram resultados corretos para casos de até 360 elementos e evidenciaram comportamento linear do tempo de execução.

## Como Contribuir

1. Fork este repositório
2. Crie sua branch com a feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas alterações (`git commit -m 'Adiciona nova funcionalidade'`)
4. Faça push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request
