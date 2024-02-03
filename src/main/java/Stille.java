import java.util.Scanner;
import java.util.ArrayList;
import tasks.*;

public class Stille {
    public static void main(String[] args) {
        String openingMessage = "Hello! I'm Stille\n" + "What can I do for you?\n";
        String closingMessage = "\nBye. Hope to see you again soon!";
        String input;
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>(100);
        boolean exit = false;

        System.out.println(openingMessage);

        while (!exit) {
            System.out.println("\n");
            input = sc.nextLine();
            String[] command = input.split(" ", 2);
            Task t;
            switch (command[0]) {
            case "bye":
                exit = true;
                break;
            case "":
                break;
            case "list":
                System.out.println("\nHere are the tasks in your list:");
                for (int i=0; i < list.size(); i++) {
                    t = list.get(i);
                    System.out.println((i + 1) + "." + t.toString());
                }
                break;
            case "mark" :
                t = list.get(Integer.parseInt(command[1]) - 1);
                t.markDone();
                System.out.println("\nNice! I've marked this task as done:");
                System.out.println("  " + t);
                break;
            case "unmark" :
                t = list.get(Integer.parseInt(command[1]) - 1);
                t.markNotDone();
                System.out.println("\nOK, I've marked this task as not done yet:");
                System.out.println("  " + t);
                break;
            case "delete" :
                t = list.get(Integer.parseInt(command[1]) - 1);
                list.remove(Integer.parseInt(command[1]) - 1);
                System.out.println("\nNoted. I've removed this task:\n " + t);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                break;
            case "todo" :
                if (command.length == 1 || command[1].trim() == "") {
                    System.out.println("\nOOPS!!! The description of a todo cannot be empty.\n ");
                    break;
                }
                t = new ToDo(command[1].trim());
                list.add(t);
                System.out.println("\nGot it. I've added this task:\n " + t);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                break;
            case "deadline" :
                command = command[1].split("\\/by", 2);
                t = new Deadline(command[0].trim(), command[1].trim());
                list.add(t);
                System.out.println("\nGot it. I've added this task:\n " + t);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                break;
            case "event" :
                command = command[1].split("\\/from|\\/to", 3);
                t = new Event(command[0].trim(), command[1].trim(), command[2].trim());
                list.add(t);
                System.out.println("\nGot it. I've added this task:\n " + t);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                break;
            default:
                //list.add(new Task(input.trim()));
                System.out.println("\nOOPS!!! I'm sorry, but I don't know what that means");
                break;
            }
        }

        System.out.println(closingMessage);
    }
}
