package src;

import java.io.FileWriter;
import java.util.ArrayList;

class Tower {
    private ArrayList<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);

        StringBuilder sb = new StringBuilder();
        sb.append("Tower says: ").append(((Aircraft)flyable).getClass().getSimpleName()).append('#')
          .append(((Aircraft)flyable).name).append('(').append(((Aircraft)flyable).id).append(')')
          .append(" registered to weather tower.\n");

        try {
            FileWriter fw = new FileWriter("simulation.txt", true);
            fw.append(sb.toString());
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void unregister(Flyable flyable) {
        observers.removeIf(fl -> ((Aircraft) fl).id == ((Aircraft) flyable).id);

        StringBuilder sb = new StringBuilder();
        sb.append("Tower says: ").append(((Aircraft)flyable).getClass().getSimpleName()).append('#')
                .append(((Aircraft)flyable).name).append('(').append(((Aircraft)flyable).id).append(')')
                .append(" unregistered from weather tower.\n");

        try {
            FileWriter fw = new FileWriter("simulation.txt", true);
            fw.append(sb.toString());
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }

        if (observers.size() == 0)
            try {
                FileWriter fw = new FileWriter("simulation.txt", true);
                fw.append("There is no aircrafts, registered in tower\n");
                fw.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
}