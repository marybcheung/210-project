package model;

import model.Evaluation;

public interface Observer {
    void update(String name, Integer outOf);
}
