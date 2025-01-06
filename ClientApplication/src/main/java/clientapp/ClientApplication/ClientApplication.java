package clientapp.ClientApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {
	@Autowired
	RestTemplate restTemplate;
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		// Create a saving account
		SavingAccountDTO savingAccountDTO = new SavingAccountDTO(123, "Test1", "test@gmail.com");
		restTemplate.postForLocation("http://localhost:8082/saving",savingAccountDTO);

		// Deposit into account
		restTemplate.postForLocation("http://localhost:8082/deposit?accountNumber=123&amount=500", null);

		// Create a checking account
		CheckingAccountDTO checkingAccountDTO = new CheckingAccountDTO(533, "Test2", "testagain@gmail.com");
		restTemplate.postForLocation("http://localhost:8081/checking",checkingAccountDTO);

		// Deposit into account
		restTemplate.postForLocation("http://localhost:8081/deposit?accountNumber=533&amount=1500", null);

		//print accounts
		SavingAccountDTO savingAccount = restTemplate.getForObject("http://localhost:8082/saving/123" , SavingAccountDTO.class);
		CheckingAccountDTO checkingAccount = restTemplate.getForObject("http://localhost:8081/checking/533" , CheckingAccountDTO.class);
		System.out.println("Saving Acc: "+ savingAccount);
		System.out.println("Checking Acc: "+ checkingAccount);

		System.out.println("Transfer ==>");
		// Deposit into account
		restTemplate.postForLocation("http://localhost:8081/deposit?accountNumber=533&amount=700", null);
		String result= restTemplate.postForObject("http://localhost:8082/withdraw?accountNumber=123&amount=700",null, String.class);
		if(result.equals("not enough balance")){
			System.out.println("Not Enough");
			restTemplate.postForLocation("http://localhost:8081/withdraw?accountNumber=533&amount=700", null); //compensate
		}

		//print accounts
		savingAccount = restTemplate.getForObject("http://localhost:8082/saving/123" , SavingAccountDTO.class);
		checkingAccount = restTemplate.getForObject("http://localhost:8081/checking/533" , CheckingAccountDTO.class);
		System.out.println("Saving Acc:"+ savingAccount);
		System.out.println("Checking Acc:"+ checkingAccount);
	}
}
