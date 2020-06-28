# javafx-SistemaAcademia
Trabalho da matéria de Programação orientada a Objetos 2.

Grupo: 
 - Lucas Costa Gobbi
 - Elian Lima Batista 
## Objetivo
Criar um sistema com as tecnologias JavaFx, Postgresql
## Contexto
O trabalho é um fragmento de um sistema para uma academia delivery hipotetica, e seu funcionamento seria basimamente o cliente solicitando um treino com um treinador em determinada data/hora, e o treinador iria para sua casa com equipamentos minimos para o treino.
O trabalho implementa os casos de uso de CRUD de clientes e agendamento de treinos, alem de geração de gráfico sobre a quantidade de agendamentos de determinador treinador e relatórios sobre os agendamentos.

## Anatomia do Sistema
O banco de dados conta com 3 simples tabelas (Cliente, Treinador, Agendamento)


### Tabelas
|Cliente  |Treinador  | Agendamento  |
|--|--|--|
| Id  | Id  | Id  |
| Nome  | Nome  | Data de inicio  |
| Cpf   | Cpf  | Horário  |
| Data de nascimento  | Data de nascimento | Treinador Responsável |
|   | Carga Horaria Máxima | Cliente  |
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

