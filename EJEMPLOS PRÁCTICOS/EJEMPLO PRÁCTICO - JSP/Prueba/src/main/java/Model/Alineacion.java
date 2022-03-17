package Model;

import Util.Repositorio;

import java.util.ArrayList;
import java.util.Arrays;

public class Alineacion {

    String formacion;
    Jugador[] titulares;
    Jugador[] suplentes;
    Entrenador entrenador;

    public Alineacion(Jugador[] jugadores, int alineacion) {
        this.titulares = new Jugador[11];
        this.suplentes = new Jugador[7];
        this.formacion = Repositorio.formaciones[alineacion];

        String alineacionPartido = Repositorio.formaciones[alineacion];
        ArrayList<Jugador> porteros = new ArrayList<>();
        ArrayList<Jugador> defensas = new ArrayList<>();
        ArrayList<Jugador> centroCampistas = new ArrayList<>();
        ArrayList<Jugador> delanteros = new ArrayList<>();

        // Alimentador de Arraylist observando el arraylist que llega por argumento y comprobando los LESIONADOS
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i].getDuracionLesion() == 0) {
                switch (jugadores[i].getPosicion()) {
                    case "Portero" -> porteros.add(jugadores[i]); //ArrayList de porteros
                    case "Defensa" -> defensas.add(jugadores[i]); //ArrayList de defensas
                    case "Centro Campista" -> centroCampistas.add(jugadores[i]); //ArrayList de centro campistas
                    case "Delantero" -> delanteros.add(jugadores[i]); //ArrayList de delanteros
                }
            } else {
                // Le quitamos una semana
                jugadores[i].reducirLesion();
            }
        }

        int portero = porteros.size();
        int defensa = Character.getNumericValue(alineacionPartido.charAt(0));
        int centro = Character.getNumericValue(alineacionPartido.charAt(1));
        int delantero = Character.getNumericValue(alineacionPartido.charAt(2));


        // CREACIÓN DE ARRAYLIST TITULARES
        for (int i = 0; i < titulares.length; i++) {
            if (i == 0) {
                if (!porteros.isEmpty()) {//Esto es lo mismo que porteros.size()!=0; (isEmpty = vacio)
                    this.titulares[i] = porteros.get(0);
                    porteros.remove(0);
                } else if (!defensas.isEmpty()) {
                    this.titulares[i] = defensas.get(0);
                    defensas.remove(0);
                } else if (!centroCampistas.isEmpty()) {
                    this.titulares[i] = centroCampistas.get(0);
                    centroCampistas.remove(0);
                } else {
                    this.titulares[i] = delanteros.get(0);
                    delanteros.remove(0);
                }
                portero--;
            } else if (defensa > 0) {
                if (!defensas.isEmpty()) {
                    this.titulares[i] = defensas.get(0);
                    defensas.remove(0);
                } else if (!centroCampistas.isEmpty()) {
                    this.titulares[i] = centroCampistas.get(0);
                    centroCampistas.remove(0);
                } else if (!delanteros.isEmpty()) {
                    this.titulares[i] = delanteros.get(0);
                    delanteros.remove(0);
                } else {
                    this.titulares[i] = porteros.get(0);
                    porteros.remove(0);
                }
                defensa--;
            } else if (centro > 0) {
                if (!centroCampistas.isEmpty()) {
                    this.titulares[i] = centroCampistas.get(0);
                    centroCampistas.remove(0);
                } else if (!defensas.isEmpty()) {
                    this.titulares[i] = defensas.get(0);
                    defensas.remove(0);
                } else if (!delanteros.isEmpty()) {
                    this.titulares[i] = delanteros.get(0);
                    delanteros.remove(0);
                } else {
                    this.titulares[0] = porteros.get(0);
                    porteros.remove(0);
                }
                centro--;
            } else if (delantero > 0) {
                if (!delanteros.isEmpty()) {
                    this.titulares[i] = delanteros.get(0);
                    delanteros.remove(0);
                } else if (!centroCampistas.isEmpty()) {
                    this.titulares[i] = centroCampistas.get(0);
                    centroCampistas.remove(0);
                } else if (!defensas.isEmpty()) {
                    this.titulares[i] = defensas.get(0);
                    defensas.remove(0);
                }
                delantero--;
            }
        }

        // CREACIÓN ARRAYLIST DE SUPLENTES
        for (int i = 0; i < suplentes.length; i++) {
            switch (i) {
                case 0 -> {
                    if (porteros.size() != 0) {
                        this.suplentes[i] = porteros.get(0);
                        porteros.remove(0);
                    } else if (centroCampistas.size() != 0) {
                        this.suplentes[i] = centroCampistas.get(0);
                        centroCampistas.remove(0);
                    } else if (defensas.size() != 0) {
                        this.suplentes[i] = defensas.get(0);
                        defensas.remove(0);
                    } else if (delanteros.size() != 0) {
                        this.suplentes[i] = delanteros.get(0);
                        delanteros.remove(0);
                    }
                }
                case 1, 2 -> {
                    // ¿Qué pasa si al tirar de la lista de defensas no hubiese nadie?
                    if (defensas.size() != 0) {
                        this.suplentes[i] = defensas.get(0);
                        defensas.remove(0);
                    } else if (centroCampistas.size() != 0) {
                        this.suplentes[i] = centroCampistas.get(0);
                        centroCampistas.remove(0);
                    } else if (delanteros.size() != 0) {
                        this.suplentes[i] = delanteros.get(0);
                        delanteros.remove(0);
                    } else if (porteros.size() != 0) {
                        this.suplentes[i] = porteros.get(0);
                        porteros.remove(0);
                    } else {
                        this.suplentes[i] = null;
                    }
                    break;
                }
                case 3, 4 -> {
                    if (centroCampistas.size() != 0) {
                        this.suplentes[i] = centroCampistas.get(0);
                        centroCampistas.remove(0);
                    } else if (defensas.size() != 0) {
                        this.suplentes[i] = defensas.get(0);
                        defensas.remove(0);
                    } else if (delanteros.size() != 0) {
                        this.suplentes[i] = delanteros.get(0);
                        delanteros.remove(0);
                    } else if (porteros.size() != 0) {
                        this.suplentes[i] = porteros.get(0);
                        porteros.remove(0);
                    } else {
                        this.suplentes[i] = null;
                    }
                    break;
                }
                case 5, 6 -> {
                    if (delanteros.size() != 0) {
                        this.suplentes[i] = delanteros.get(0);
                        delanteros.remove(0);
                    } else if (defensas.size() != 0) {
                        this.suplentes[i] = defensas.get(0);
                        defensas.remove(0);
                    } else if (centroCampistas.size() != 0) {
                        this.suplentes[i] = centroCampistas.get(0);
                        centroCampistas.remove(0);
                    } else if (porteros.size() != 0) {
                        this.suplentes[i] = porteros.get(0);
                        porteros.remove(0);
                    } else {
                        this.suplentes[i] = null;
                    }
                    break;
                }
            }
        }
    }

    public Alineacion(Jugador[] jugadores, int numeroDefensas, int numeroCentroCampistas, int numeroDelanteros) {
        this.titulares = new Jugador[11];
        this.suplentes = new Jugador[7];

        ArrayList<Jugador> porteros = new ArrayList<>();
        ArrayList<Jugador> defensas = new ArrayList<>();
        ArrayList<Jugador> centroCampistas = new ArrayList<>();
        ArrayList<Jugador> delanteros = new ArrayList<>();

        // Alimentador de Arraylist observando el arraylist que llega por argumento
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i].getDuracionLesion() == 0) {
                switch (jugadores[i].getPosicion()) {
                    case "Portero" -> porteros.add(jugadores[i]); //ArrayList de porteros
                    case "Defensa" -> defensas.add(jugadores[i]); //ArrayList de defensas
                    case "Centro Campista" -> centroCampistas.add(jugadores[i]); //ArrayList de centro campistas
                    case "Delantero" -> delanteros.add(jugadores[i]); //ArrayList de delanteros
                }
            } else {
                jugadores[i].reducirLesion();
            }
        }

        int portero = porteros.size();
        int defensa = numeroDefensas;
        int centro = numeroCentroCampistas;
        int delantero = numeroDelanteros;


        // CREACIÓN DE ARRAYLIST TITULARES
        for (int i = 0; i < titulares.length; i++) {
            if (i == 0) {
                if (!porteros.isEmpty()) {
                    this.titulares[i] = porteros.get(0);
                    porteros.remove(0);
                } else if (!defensas.isEmpty()) {
                    this.titulares[i] = defensas.get(0);
                    defensas.remove(0);
                } else if (!centroCampistas.isEmpty()) {
                    this.titulares[i] = centroCampistas.get(0);
                    centroCampistas.remove(0);
                } else {
                    this.titulares[i] = delanteros.get(0);
                    delanteros.remove(0);
                }
                portero--;
            } else if (defensa > 0) {
                if (!defensas.isEmpty()) {
                    this.titulares[i] = defensas.get(0);
                    defensas.remove(0);
                } else if (!centroCampistas.isEmpty()) {
                    this.titulares[i] = centroCampistas.get(0);
                    centroCampistas.remove(0);
                } else if (!delanteros.isEmpty()) {
                    this.titulares[i] = delanteros.get(0);
                    delanteros.remove(0);
                } else {
                    this.titulares[i] = porteros.get(0);
                    porteros.remove(0);
                }
                defensa--;
            } else if (centro > 0) {
                if (!centroCampistas.isEmpty()) {
                    this.titulares[i] = centroCampistas.get(0);
                    centroCampistas.remove(0);
                } else if (!defensas.isEmpty()) {
                    this.titulares[i] = defensas.get(0);
                    defensas.remove(0);
                } else if (!delanteros.isEmpty()) {
                    this.titulares[i] = delanteros.get(0);
                    delanteros.remove(0);
                } else {
                    this.titulares[0] = porteros.get(0);
                    porteros.remove(0);
                }
                centro--;
            } else if (delantero > 0) {
                if (!delanteros.isEmpty()) {
                    this.titulares[i] = delanteros.get(0);
                    delanteros.remove(0);
                } else if (!centroCampistas.isEmpty()) {
                    this.titulares[i] = centroCampistas.get(0);
                    centroCampistas.remove(0);
                } else if (!defensas.isEmpty()) {
                    this.titulares[i] = defensas.get(0);
                    defensas.remove(0);
                } else if (!porteros.isEmpty()) {
                    this.titulares[i] = porteros.get(0);
                    porteros.remove(0);
                }
                delantero--;
            }
        }

        // CREACIÓN ARRAYLIST DE SUPLENTES
        for (int i = 0; i < suplentes.length; i++) {
            switch (i) {
                case 0 -> {
                    if (!porteros.isEmpty()) {
                        this.suplentes[i] = porteros.get(0);
                        porteros.remove(0);
                    } else if (!defensas.isEmpty()) {
                        this.suplentes[i] = defensas.get(0);
                        defensas.remove(0);
                    } else if (!centroCampistas.isEmpty()) {
                        this.suplentes[i] = centroCampistas.get(0);
                        centroCampistas.remove(0);
                    } else if (!delanteros.isEmpty()) {
                        this.suplentes[i] = delanteros.get(0);
                        delanteros.remove(0);
                    }
                    break;
                }
                case 1, 2 -> {
                    // ¿Qué pasa si al tirar de la lista de defensas no hubiese nadie?
                    if (!defensas.isEmpty()) {
                        this.suplentes[i] = defensas.get(0);
                        defensas.remove(0);
                    } else if (centroCampistas.size() != 0) {
                        this.suplentes[i] = centroCampistas.get(0);
                        centroCampistas.remove(0);
                    } else if (delanteros.size() != 0) {
                        this.suplentes[i] = delanteros.get(0);
                        delanteros.remove(0);
                    } else if (porteros.size() != 0) {
                        this.suplentes[i] = porteros.get(0);
                        porteros.remove(0);
                    } else {
                        this.suplentes[i] = null;
                    }
                    break;
                }
                case 3, 4 -> {
                    if (centroCampistas.size() != 0) {
                        this.suplentes[i] = centroCampistas.get(0);
                        centroCampistas.remove(0);
                    } else if (defensas.size() != 0) {
                        this.suplentes[i] = defensas.get(0);
                        defensas.remove(0);
                    } else if (delanteros.size() != 0) {
                        this.suplentes[i] = delanteros.get(0);
                        delanteros.remove(0);
                    } else if (porteros.size() != 0) {
                        this.suplentes[i] = porteros.get(0);
                        porteros.remove(0);
                    } else {
                        this.suplentes[i] = null;
                    }
                    break;
                }
                case 5, 6 -> {
                    if (delanteros.size() != 0) {
                        this.suplentes[i] = delanteros.get(0);
                        delanteros.remove(0);
                    } else if (defensas.size() != 0) {
                        this.suplentes[i] = defensas.get(0);
                        defensas.remove(0);
                    } else if (centroCampistas.size() != 0) {
                        this.suplentes[i] = centroCampistas.get(0);
                        centroCampistas.remove(0);
                    } else if (porteros.size() != 0) {
                        this.suplentes[i] = porteros.get(0);
                        porteros.remove(0);
                    } else {
                        this.suplentes[i] = null;
                    }
                    break;
                }
            }
        }
    }

    public String getFormacion() {
        return formacion;
    }

    public void setFormacion(int alineacion) {
        String[] formaciones = new String[]{"442", "352", "433", "451"};
        this.formacion = formaciones[alineacion];
    }

    public Jugador[] getTitulares() {
        return titulares;
    }

    public void setTitulares(Jugador[] titulares) {
        this.titulares = titulares;
    }

    public Jugador[] getSuplentes() {
        return suplentes;
    }

    public void setSuplentes(Jugador[] suplentes) {
        this.suplentes = suplentes;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    @Override
    public String toString() {
        return " Alineacion{" +
                " formacion='" + formacion + '\'' +
                ", titulares=" + Arrays.toString(titulares) +
                ", suplentes=" + Arrays.toString(suplentes) +
                '}';
    }
}