package game.things.items;

import game.things.Item;

/**
 * Initiates a connection between a colony and a player from anywhere
 */
public class QuantumCommunicator extends Item {
    /**
     * Instantiates a new Quantum communicator.
     */
    public QuantumCommunicator() {
        super("Quantum Communicator", "Instant communication with any colony or base.", 2000, "textures/things/items/communicator.png");
    }
}