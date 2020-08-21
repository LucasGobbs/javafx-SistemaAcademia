# javafx-SistemaAcademia
Trabalho da matéria de Programação orientada a Objetos 2.

Grupo: 
 - Lucas Costa Gobbi
 - Elian Lima Batista 
# Primeira Parte

## Objetivo
Criar um sistema com as tecnologias JavaFx, Postgresql
## Contexto
O trabalho é um fragmento de um sistema para uma academia em formato delivery, e seu funcionamento seria basicamente o cliente solicitando um treino com um treinador em determinada data/hora, e no dia agendado o treinador iria para sua casa com equipamentos minimos para o treino.
O trabalho implementa os casos de uso de CRUD de clientes e agendamento de treinos, alem de geração de gráfico sobre a quantidade de agendamentos de determinado treinador e relatórios sobre os agendamentos.

## Anatomia do Sistema
O banco de dados conta com 3 simples tabelas (Cliente, Treinador, Agendamento)


### Tabelas
|Cliente  |Treinador  | Agendamento  |
|--|--|--|
| Id  | Id  | Id  |
| Nome  | Nome  | Data de inicio  |
| Cpf   | Cpf  | Horário  |
| Data de nascimento  | Data de nascimento | id do Treinador Responsável |
|   | Carga Horaria Máxima | id do Cliente  |
|   | Valor por mês | Valor  |

## Regras de negocio

 1. Cada treinador só pode ter X agendamentos por mês sendo este x sua Carga horaria Máxima
 
Esta regra existe para que todos os treinadores possam ter uma media quantidade de agendamentos por mês, evitando que um treinador tenha o monopólio de agendamentos

 2. O valor do agendamento é calculado pela forma a + b * 10
 - a -> Valor por hora do treinador
 - b -> Quantidade de agendamentos do treinador na data

## Cadastro de Clientes

<img src="https://github.com/LucasGobbs/javafx-SistemaAcademia/blob/master/git_images/tela_cadastramento_clientes.png"  width="400" height="350">

## Processo de Agendamento
<img src="https://github.com/LucasGobbs/javafx-SistemaAcademia/blob/master/git_images/tela_agendamento.PNG"  width="400" height="350">

#### Transações com o banco

 1. Listagem de Clientes
 2. Listagem de Treinadores
 3. Solicitar a carga horária disponível do treinador durante a data
 4. Solicitar a disponibilidade do treinador na data e hora
 5. Inserção do agendamento
 
## Graficos
<img src="https://github.com/LucasGobbs/javafx-SistemaAcademia/blob/master/git_images/tela_graficos.PNG"  width="400" height="350">

## Relatórios
<img src="https://github.com/LucasGobbs/javafx-SistemaAcademia/blob/master/git_images/tela_relatorios.PNG"  width="400" height="350">

## Para compilar
 - Criar um banco de dados, e rodar o arquivo database_query.sql
 - Mudar conteudo do arquivo DataBasePostgresql.java para:
```java
this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1/dbSistemaAcademia", "postgres", "123");
// mudar dbSistemaAcademia para o nome do banco
// mudar postgres para o usuario do banco 
// mudar 123 para a senha do usuario
```
# Segunda parte (Threads e Sockets)
## Objetivo
Criar uma tela, que após utilizar sockets para pegar dados de um servidor as mostra em intervalos regulares de 3 segundos (Utilizando threads)
## Detalhes
Foram criadas duas classes
- NoticiasController
- NoticiasRunnable
A classe NoticiassController, comunica com o servidor e recebe a lista de noticias List<String> e cria uma thread para a execução do NoticiasRunnable
Já a classe NoticiasRunnable recebe a lista de noticias e a mostra na tela utilizando o Thread.Sleep(3000) para os intervalos
 
## Imagem
<img src="https://github.com/LucasGobbs/javafx-SistemaAcademia/blob/master/git_images/tela_noticias.PNG"  width="400" height="350">

# Terceira parte (WebServices)
## Objetivo
Criar uma tela, que faz um request em um webservice para receber um List<String> de noticias, e os mostra simultaneamente em um Label

## Imagem
<img src="https://github.com/LucasGobbs/javafx-SistemaAcademia/blob/master/git_images/tela_webservices.PNG"  width="400" height="350">

# Organização do Projeto
Dividi o projeto em duas releases representando as duas fases do projeto
