# Operação Quasar - Teste MeLi

### Requisitos:
- Java 11 instalado e configurado
  
### Passo a passo para executar:
* Clonar o repositório
* Executar o comando: mvn clean install
* Executar o comando mvn spring-boot:run
* Utilizar a collection na pasta /postman: Quasar Meli

### Observações para descoberta da mensagem do emissor:
* O algoritmo irá percorrer a mensagem recebida em cada satélite e, caso seja diferente de um espaço em branco, preenche em um vetor de strings. Caso no final da iteração ainda exista espaços em branco no vetor, será disparada uma exceção de que não foi possível decifrar a mensagem.

### Observações para cálculo da posição do emissor:
* Para obter a localização do emissor, foi utilizada a fórmula da distância entre dois pontos, entre cada um dos satélites e o emissor da mensagem.<br>
    * <sub> dk² = x² + 1000x + y² +400y + 290000 (kenobi) </sub><br>
    * <sub>  dw² = x² - 200x  + y² +200y + 20000 (skywalker) </sub><br>
    * <sub>  ds² = x² - 1000x + y² -200y + 260000 (sato) </sub>
    
* Após isso foram feitas substituções para resolução de sistemas até obter a fórmula para os valores de x e y:
    * <sub> x = (2*dk² - 3*dw² + ds² - 780000)/1600 </sub><br>
    * <sub> y = -6x + dk²/200 - dw²/200 -1350 </sub><br>
    
* dk = distância do emissor em relação ao satélite kenobi
* dw = distância do emissor em relação ao satélite skywalker
* ds = distância do emissor em relação ao satélite sato

* Caso ocorra alguma exceção, por falta de informações ou outro motivo, será disparada uma exceção de que não foi possível calcular a posição do emissor.

### URL para testes na AWS:
* http://ec2-15-228-49-205.sa-east-1.compute.amazonaws.com:8080
* Collection na pasta /postman: Quasar Meli - AWS
* A aplicação está rodando em uma instância EC2 na AWS.