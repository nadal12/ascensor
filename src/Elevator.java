import javax.xml.transform.Source;
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
    private int movement;
    ArrayList <Integer> entrada = new ArrayList<>();
    //De objecte planta
    ArrayList <Planta> Plantes = new ArrayList<>();

    public void simulateElevator() throws IOException {
        //Iniciar arraylist amb plantes
        System.out.printf("Quantes plantes te es puta edifici: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String  line = br.readLine();
        iniciarPlantes(line);
        //System.out.println(Plantes);

        while (true){
            System.out.println("Ascensor a planta " + floor);
            System.out.print("Quina planta vols anar? ");

            //Llegim entrada
            String  lines = br.readLine();

            String[] strs = lines.trim().split("\\s+");

            //Aqui tenim sa llista de ses plantes pendents
            for (int i = 0; i < strs.length; i++) {
                entrada.add( Integer.parseInt(strs[i]));
            }
            //Posar plantes a pendets de visitar
            for (int i = 0; i < entrada.size(); i++){
                for (int j = 0; j < Plantes.size(); j ++){
                    if (entrada.get(i) == Plantes.get(j).getNumPlanta()){

                        //System.out.println(entrada.get(i) +"  Es igual a "+Plantes.get(j).getNumPlanta());
                        Plantes.get(j).setPendent(true);
                    }
                }
            }
            //Agafam desti es primer da sa llista d'entrades.
            int destinationFloor = entrada.get(0);

             movement = getMovementDirection(destinationFloor);

            if (movement == 0) {
                System.out.println("Obrint portes");
            } else {
                moveElevator(destinationFloor, movement);
            }
        }
    }
    void imprimirPendents (){
        for (int i = 0; i < Plantes.size(); i++){
            System.out.println("Planta: "+Plantes.get(i).getNumPlanta()+ " , pendent = "+Plantes.get(i).isPendent());
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
        //comprovarDisPonibles(moveDirection);
        while (floor != destination) {
            floor += moveDirection;
            plantaPendent();
            System.out.println(floor);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ascensor ha arribat");
        System.out.println(entrada);
    }

    /*
    * En aquest metode es comprova durant el moviment de lascensor si les plantes per les
    * que passa estan a pendents. Si es aixi, s'atura.
    * FALTA PER COMPROVAR si es boto es per pujar o baixar. Se pot emplear sa variable movement
    * */
    void plantaPendent(){
        for (int j = 0; j < Plantes.size(); j ++){
            if (floor == Plantes.get(j).getNumPlanta() &&  Plantes.get(j).isPendent() ){
               //Llevam pendent perque ja lhem visitada
                Plantes.get(j).setPendent(false);
                //Levar planta de arraylist
                for (int i = 0; i < entrada.size();i++){
                    if (entrada.get(i) == floor){
                        entrada.remove(i);}
                }
                System.out.println("Obrint portes de la planta: "+Plantes.get(j).getNumPlanta());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Tancant portes");
            }
        }
    }

    private void comprovarDisPonibles( int moveDirection){
        if (moveDirection > 0) {
            for (int i = 0; i < entrada.size(); i++) {
                if (floor < entrada.get(i)) {
                    System.out.println("Puc anar a planta: "
                            +entrada.get(i));
                }
            }
        }else{
            for (int i = 0; i < entrada.size(); i++) {
                if (floor > entrada.get(i)) {
                    System.out.println("Puc anar a planta: "
                            +entrada.get(i));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Elevator().simulateElevator();
    }
}