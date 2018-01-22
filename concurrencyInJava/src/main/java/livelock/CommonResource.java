package livelock;

/*
* Created by Egor on 24.12.2017.
*/

public class CommonResource {
    private Worker owner;

    public CommonResource (Worker d) {
        owner = d;
    }

    public Worker getOwner () {
        return owner;
    }

    public synchronized void setOwner (Worker d) {
        owner = d;
    }
}