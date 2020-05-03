package com.srbarrios.mybackend.entities;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -305726463442998985L;

    public String mailAddress;

    public int lives;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
