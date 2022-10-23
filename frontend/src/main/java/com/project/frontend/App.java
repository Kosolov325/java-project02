package com.project.frontend;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class App {
	private static final String api = "http://localhost:8000/api/";
	
    public static String getApi() {
		return api;
	}
    
  
	public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException, InterruptedException
	{
		Boolean stop = false;
		String cont;
		int option;

		int pk;
		String username;
		String password;
		String confirmation;
		
		Get get;
		while(stop == false) {
		Scanner scanner = new Scanner(System.in);
		

									   								
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|                                 _____ _____  _    _ _____                     | ");
		System.out.println("|                               / ____|  __  | |  | |  __ |                     |");
		System.out.println("|                               |  |    | |__) | |  | | | |                     |");
		System.out.println("|                               | |    |  _  /| |  | | |  |                     |");
		System.out.println("|                               | |____| | | || |__| | |__|                     |");
		System.out.println("|                               |_____|_|  |_||____/|_____/                     |");
		System.err.println("|                                                                               |");
		System.out.println("|                              1 - Cadastrar Usuarios                           |");
		System.out.println("|                              2 - Listar Usuarios                              |");
		System.out.println("|                              3 - Editar Usuarios                              |");
		System.out.println("|                              4 - Deletar Usuarios                             |");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.print("Digite uma opção: ");
		option = scanner.nextInt(); 
		
		switch (option) {
		case 1:
			scanner.nextLine();
			System.out.print("Digite o nome: ");
			username = scanner.nextLine();
	
			System.out.print("Digite a senha: ");
			password = scanner.nextLine();

			System.out.print("Você tem certeza ?(S/N): ");
			confirmation = scanner.nextLine();
			System.out.print("\n");

			if (confirmation.equalsIgnoreCase("S")){
				
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				Post post = new Post(api, "users/", user);
				
			}
			break;
		case 2:
			System.out.print("\n");
			System.out.print("Digite 0 para listar todos ou digite o id do usuário: ");
			option = scanner.nextInt();
			scanner.nextLine();
			System.out.print("\n");
			if (option == 0) { 
				 get = new Get(api, "users", true);
			} else {
				 get = new Get(api, "users/" + option, false);
			}
			break;
		case 3:
			System.out.print("\n");
			System.out.print("Digite um id válido: ");
			pk = scanner.nextInt();
			System.out.print("\n");
			get = new Get(api, "users/" + pk, false);
			System.out.print("\n");

			scanner.nextLine();
			System.out.print("Digite o novo nome: ");
			username = scanner.nextLine();

			System.out.print("Digite a nova senha: ");
			password = scanner.nextLine();

			System.out.print("Você tem certeza ?(S/N): ");
			confirmation = scanner.nextLine();

			if (confirmation.equalsIgnoreCase("S")){
			
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				Put put = new Put(api, "users/" + pk + '/', user);
			}
			break;
		case 4:
			System.out.print("Digite um id válido: ");
			pk = scanner.nextInt();

			get = new Get(api, "users/" + pk, false);
			System.out.print("\n");
			scanner.nextLine();

			System.out.print("Você tem certeza ?(S/N): ");
			confirmation = scanner.nextLine();

			if (confirmation.equalsIgnoreCase("S")){
				
				Delete delete = new Delete(api, "users/" + pk + "/");
			}
			break;
		}

		System.out.print("\n");
		System.out.print("Fazer mais uma consulta?(S, N): ");
		cont = scanner.nextLine();
		stop = (cont.equalsIgnoreCase("S") ? false :  true);
		System.out.print("\n");
    }
  }
}
