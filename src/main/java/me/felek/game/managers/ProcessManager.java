package me.felek.game.managers;

import me.felek.game.processes.Process;

import java.util.ArrayList;
import java.util.List;

public class ProcessManager {
    private List<Process> processes;

    public ProcessManager() {
        processes = new ArrayList<>();
    }

    public void registerProcess(Process proc) {
        processes.add(proc);
    }

    public void updateAll() {
        for (Process proc : processes) {
            proc.process();
        }
    }
}
