/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.ads.tragamonedas.services;

import cl.ucn.disc.ads.tragamonedas.model.Rueda;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of the Tragamonedas Game.
 *
 * @author Josemanuel Espinoza.
 */
public final class TragamonedasImpl implements Tragamonedas {

    /**
     * The number of Ruedas.
     */
    private final int NUMERO_RUEDAS = 3;

    /**
     * The List of Rueda.
     */
    private final List<Rueda> ruedas;

    /**
     * The saldo of user.
     */
    private int saldo;

    /**
     * The Constructor.
     *
     * @param saldo del Usuario.
     */
    public TragamonedasImpl(final int saldo) {
        if (saldo <= 0) {
            throw new IllegalArgumentException("No se puede tener saldo inicial cero o negativo");
        }

        this.saldo = saldo;

        // construction
        this.ruedas = Stream
                .generate(Rueda::new)
                .limit(NUMERO_RUEDAS)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int realizarApuesta(final int apuesta) {

        // protection
        if (apuesta <= 0) {
            throw new IllegalArgumentException("El valor de la apuesta no puede ser cero o negativo");
        }

        // tengo saldo
        if (apuesta > this.saldo) {
            throw new IllegalArgumentException("No se puede apostar mas del saldo disponible");
        }

        // descuento la apuesta
        this.saldo -= apuesta;

        // giro las ruedas
        this.girarRuedas();

        // realizo el calculo del premio
        int premio = this.getPremio(apuesta);

        // sumo el saldo
        this.saldo += premio;

        // retorno el premio
        return premio;

    }

    /**
     * Spin the wheel!
     */
    private void girarRuedas() {
        // random throw.
        ruedas.forEach(Rueda::girarAlAzar);
    }

    /**
     * calculates the value of the prize to be obtained from the value of the bet.
     *
     * @param apuesta to use.
     * @return the prize value.
     */
    private int getPremio(int apuesta) {

        // rule 1: all the values are equals
        if (this.isValorRuedasIgualesDistintoDeCero()) {
            return apuesta * this.ruedas.get(0).getValor();
        }

        // rule 2: count the ceros
        int zeros = Math.toIntExact(this.ruedas.stream()
                .filter(rueda -> rueda.getValor() == 0)
                .count());

        return switch (zeros) {
            // '*'
            case 1 -> 50;
            // '**'
            case 2 -> 300;
            // '***'
            case 3 -> 500;
            // nothing
            default -> 0;
        };

    }

    /**
     * @return true if the values of all wheels are equal but non-zero
     */
    private boolean isValorRuedasIgualesDistintoDeCero() {
        return this.ruedas
                .stream()
                .allMatch(rueda -> rueda.getValor() == this.ruedas.get(0).getValor() && rueda.getValor() != 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Character> getRuedasValues() {
        return this.ruedas.stream()
                .map(Rueda::getChar)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSaldo() {
        return this.saldo;
    }
}