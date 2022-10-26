public class ckala {
    import java.util.Scanner;

    public class rockClimber {

        public static void main(String[] args) {
            int maxHeight;
            int dropHeight;
            int lenghtOfRoute;

            Scanner in = new Scanner(System.in);

            System.out.print("Введите максимальную высоту на которую может залезть скалолаз: ");
            maxHeight = in.nextInt();
            System.out.print("Введите максимальный перепад высоты: ");
            dropHeight = in.nextInt();
            System.out.print("Введите длину маршрута: ");
            lenghtOfRoute = in.nextInt();


            int[] rocks = new int[lenghtOfRoute];

            for (int i = 0; i < lenghtOfRoute; i++){
                rocks[i] = (int) (Math.random() * (maxHeight + 1));
            }

            for (int i = 0; i <= (lenghtOfRoute - 1); i++)
                System.out.println(rocks[i]);


            for (int i = 0; i < lenghtOfRoute; i++){
                if (i == (lenghtOfRoute - 1)){
                    break;
                }
                if (rocks[i] > rocks[i+1]){
                    if ((rocks[i]-rocks[i+1]) > dropHeight){
                        int n = rocks[i] - dropHeight;
                        rocks[i+1] = n;
                    }
                }
            }

            for (int j = lenghtOfRoute; j != 0; j--) {
                for (int i = 0; i < lenghtOfRoute; i++) {
                    if (i == (lenghtOfRoute - 1)) {
                        break;
                    }
                    if (rocks[i] < rocks[i + 1]) {
                        if ((rocks[i + 1] - rocks[i]) > dropHeight) {
                            int n = rocks[i + 1] - dropHeight;
                            rocks[i] = n;
                        }
                    }
                }
            }


            //Визуализация гор
            String[] rocksPic = new String[lenghtOfRoute];

            System.out.println("---Начало гор---");

            for (int i = 0; i < lenghtOfRoute; i++)
                rocksPic[i] = "*".repeat(rocks[i]);

            for (int i = 0; i < lenghtOfRoute; i++)
                System.out.println(rocksPic[i]);

            System.out.println("---Конец гор---");

        }

    }
}