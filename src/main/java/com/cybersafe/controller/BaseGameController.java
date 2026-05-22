package com.cybersafe.controller;
public abstract class BaseGameController {
    protected MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}