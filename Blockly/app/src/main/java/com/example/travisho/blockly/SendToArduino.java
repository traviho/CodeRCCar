package com.example.travisho.blockly;

import java.util.ArrayList;

/**
 * Created by kodacticecto on 1/8/17.
 */

public class SendToArduino implements Runnable {
    public Bluetooth bluetooth;
    public ArrayList<Integer> bluetoothCommands;
    public static boolean runState = false;
    public SendToArduino(ArrayList<Integer> bluetoothCommands, Bluetooth bluetooth) {
        this.bluetooth = bluetooth;
        this.bluetoothCommands = bluetoothCommands;

    }


    @Override
    public void run() {
        System.out.println("RUNNING THROUGH BLUETOOTH");
        for (int i = 0; i < bluetoothCommands.size(); i++) {
            System.out.println("" + bluetoothCommands.get(i));
            try {
            bluetooth.sendMessage(bluetoothCommands.get(i) + "");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        runState = false;
    }
}
