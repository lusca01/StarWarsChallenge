package Model;

import java.util.Arrays;

public class Character {
    String name;
    String [] films;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getFilms() {
        return films;
    }

    public void setFilms(String[] films) {
        this.films = films;
    }

    public int getLenght(){
        return this.getFilms().length;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", films=" + Arrays.toString(films) +
                '}';
    }
}
