# Operação Quasar - Teste MeLi

### Executando com maven:
#### Requisitos:
* Java 11 instalado e configurado
* Maven instalado e configurado
  
#### Passo a passo para executar:
* Clonar o repositório
* Executar o comando: mvn clean install
* Executar o comando mvn spring-boot:run
* Utilizar a collection na pasta /postman: Quasar Meli

### Executando com docker:
#### Requisitos:
* Docker instalado

#### Passo a passo:
* Na pasta do projeto, abra o terminal
* Rode o comando docker build -t teste-meli-operacao-quasar .
* Aguarde o build da imagem do projeto
* Rode o comando docker run -dp 8080:8080 teste-meli-operacao-quasar
* Execute os testes com a collection da pasta /postman: Quasar Meli

### Observações para descoberta da mensagem do emissor:
* O algoritmo irá percorrer a mensagem recebida em cada satélite e, caso seja diferente de um espaço em branco, preenche em um vetor de strings. Caso no final da iteração ainda exista espaços em branco no vetor, será disparada uma exceção de que não foi possível decifrar a mensagem.
* O serviço ainda valida caso algum satélite tenha recebido uma mensagem diferente dos demais em alguma posição. Retornando erro 404 nesse caso.

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

* Caso ocorra alguma exceção, por falta de informações dos satélites ou falha no cálculo, será disparada uma exceção de que não foi possível calcular a posição do emissor.

### Outras observações:
* Além dos endpoints solicitados na documentação do teste, foi adicionado um endpoint /topscret_split/ com o método PATCH, para limpar as informações enviadas sobre os satélites.
* Qualquer cenário que não seja possível calcular a localização ou a mensagem, irá retornar um erro 404.

### URL para testes na AWS:
* http://ec2-15-228-49-205.sa-east-1.compute.amazonaws.com:8080
* Collection na pasta /postman: Quasar Meli - AWS
* A aplicação está rodando em uma instância EC2 na AWS.

### TODO:
* Testes Unitários [x] 
* Criação da imagem docker [x] 
* Criação da infraestrutura como código (terraform) [] - Não obrigatório
