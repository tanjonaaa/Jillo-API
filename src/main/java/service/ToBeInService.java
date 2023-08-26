package service;

import model.ToBeIn;

import java.util.List;

public interface ToBeInService {
    List<ToBeIn> getAllToBeIns();
    ToBeIn getToBeInById(int id);
    ToBeIn addToBeIn(ToBeIn toBeIn);
    ToBeIn updateToBeIn(ToBeIn toBeIn);
    int deleteToBeIn(int id);
}
