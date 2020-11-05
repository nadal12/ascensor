import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class Elevator {
    private static final String DOWN = "cap avall";
    private static final String UP = "cap adalt";
    private final Random rand = new Random();
    private int floor = rand.nextInt(10) + 1;
    ArrayList <Integer> plantes = new ArrayList<>();
    //De objecte planta
    ArrayList <Planta> Plantes = new ArrayList<>();

    public void simulateElevator() throws IOException {
        //Iniciar arraylist amb plantes
        System.out.printf("Quantes plantes te es puta edifici: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String  line = br.readLine();
        iniciarPlantes(line);
        System.out.println(Plantes);

        while (true){
            System.out.println("Ascensor a planta " + floor);
            System.out.print("Quina planta vols anar? ");

            //Llegim entrada
            String  lines = br.readLine();

            String[] strs = lines.trim().split("\\s+");

            for (int i = 0; i < strs.length; i++) {
                plantes.add( Integer.parseInt(strs[i]));
            }

            int destinationFloor = plantes.get(0);


            int movement = getMovementDirection(destinationFloor);
            if (movement == 0) {
                System.out.println("Obrint portes");
            } else {
                moveElevator(destinationFloor, movement);
            }
        }
    }
    void iniciarPlantes (String numPlantesString){
        int numplantes = Integer.parseInt(numPlantesString);
       Planta plantaMomentania ;
        for (int i = 0; i < numplantes+1; i++) {
            plantaMomentania = new Planta(i,false,false,false);
            Plantes.add(i,plantaMomentania);

        }


    }



    /* determine if movement needed is to up or to down */
    private int getMovementDirection(int destinationFloor) {
        int dif = destinationFloor - floor;
        if (dif == 0) {
            return 0;
        }
        return dif / Math.abs(dif);
    }


    private String getMovementName(int movement) {
        return movement < 0 ? DOWN : UP;
    }

    private void moveElevator(int destination, int moveDirection) {
        String movementName = getMovementName(moveDirection);
        System.out.println("Ascensor l'ascensor esta anant ".concat(movementName).concat("..."));
        comprovarDisPonibles(moveDirection);
        while (floor != destination) {
            floor += moveDirection;



            System.out.println(floor);
        }
        System.out.println("Ascensor ha arribat");
        plantes.remove(0);
        System.out.println(plantes);
    }

    private void comprovarDisPonibles( int moveDirection){
        if (moveDirection > 0) {
            for (int i = 0; i < plantes.size(); i++) {
                if (floor < plantes.get(i)) {
                    System.out.println("Puc anar a planta: "
                            +plantes.get(i));
                }

            }

        }else{
            for (int i = 0; i < plantes.size(); i++) {
                if (floor > plantes.get(i)) {
                    System.out.println("Puc anar a planta: "
                            +plantes.get(i));
                }

            }

        }



    }

    public static void main(String[] args) throws IOException {
        new Elevator().simulateElevator();
    }
}