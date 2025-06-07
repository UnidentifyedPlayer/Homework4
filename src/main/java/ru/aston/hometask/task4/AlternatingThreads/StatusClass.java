package ru.aston.hometask.task4.AlternatingThreads;

public class StatusClass {
    private boolean flagRaised = false;

    public StatusClass() {
    }

    public void setStatus(boolean status)
    {
        flagRaised = status;
    }

    public boolean getStatus()
    {
        return flagRaised;
    }
}
