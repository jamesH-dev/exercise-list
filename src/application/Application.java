package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Fazer um programa para ler um número inteiro N e depois os dados (id, nome e salario) de
 * N funcionários. Não deve haver repetição de id.
 * Em seguida, efetuar o aumento de X por cento no salário de um determinado funcionário.
 * Para isso, o programa deve ler um id e o valor X. Se o id informado não existir, mostrar uma
 * mensagem e abortar a operação. Ao final, mostrar a listagem atualizada dos funcionários,
 * conforme exemplos.
 * Lembre-se de aplicar a técnica de encapsulamento para não permitir que o salário possa
 * ser mudado livremente. Um salário só pode ser aumentado com base em uma operação de
 * aumento por porcentagem dada.
 */

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Quantos funcionários serão cadastrados? ");
        int employeeQuantity = sc.nextInt();
        List<Employee> employeeList = new ArrayList<>();

        for (int i = 0; i < employeeQuantity; i++){
            System.out.println("\nFuncionário #" + (i+1));
            System.out.print("ID: ");
            int id = sc.nextInt();
            while (hasId(employeeList, id)){
                System.out.print("Id já existe! Tente novamente: ");
                id = sc.nextInt();
            }
            System.out.print("Nome: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Salário: ");
            Double salary = sc.nextDouble();
            employeeList.add(new Employee(id, name, salary));
        }

        System.out.print("\nInforme o ID do funcionário que terá o salário acrescido: ");
        Integer id = sc.nextInt();
        Employee employee = employeeList.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        if (employee == null){
            System.out.println("ID informado não encontrado!");
        } else {
            System.out.print("\nInforme a %: ");
            double percentage = sc.nextDouble();
            employee.increaseSalary(percentage);
        }

        System.out.println("\nLISTA DE FUNCIONÁRIOS");
        for (Employee e : employeeList){
            System.out.println(e.toString());
        }

        sc.close();
    }
    public static boolean hasId(List<Employee> list, int id){
        Employee employee = list.stream().filter(l -> l.getId().equals(id)).findFirst().orElse(null);
        return employee != null;
    }
}
