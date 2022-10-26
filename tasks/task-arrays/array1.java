public class pocer {
    импорт java.util.Scanner;

    публичный класс Покер {

        public static void main(String[] args) {
            //Колода карт
            String[] suits_list = {
                    "Пик", "Бубен", "Черв", "Треф"
            };

            String[] rank_list = {
                    "2", "3", "4", "5", "6", "7", "8", "9", "10",
                    "Валет", "Королева", "Король", "Туз"
            };

            int cardPerPlayer = 5;

            int n = suits_list.length * rank_list.length;


            //Количество игроков
            System.out.print("Введите кол-во игроков: ");

            Сканер в = новый сканер(System.in);
            int countPlayers = in.nextInt();

            if (countPlayers < 2) {
                System.out.println("Вы не можете играть один.");
            } else if (n < countPlayers * cardPerPlayer) {
                System.out.println("На всех не хватит карт.");
                System.exit(0);
            }


            
            String[] deck = новая строка[n];
            for (int i = 0; i < rank_list.length; i++) {
                for (int j = 0; j < suits_list.length; j++) {
                    deck[suits_list.length*i + j] = rank_list[i] + " " + suits_list[j];
                }
            }


            //Перетасовка колоды
            for (int i = 0; i < n; i++) {
                int r = i + (int) (Math.random() * (n-i));
                String temp = deck[r];
                deck[r] = deck[i];
                deck[i] = temp;
            }


            //Вывод карт, для каждого игрока.
            for (int i = 0; i < countPlayers * cardPerPlayer; i++) {
                if (i == 0)
                    System.out.println("Карты игрока под номером: " + 1);
                System.out.println(deck[i]);
                if (i % cardPerPlayer == cardPerPlayer - 1) {
                    if (i != (countPlayers * cardPerPlayer - 1)) {
                        System.out.println("Карты игрока под номером: " + ((i / cardPerPlayer) + 2));
                    }
                }
            }
        }
    }
}