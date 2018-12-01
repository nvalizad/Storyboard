package com.example.neema.storyboard;

enum CardType {
    WEEKLY, FREEWRITE, PROMPT
}

public class Card {
    CardType cardType;
    private String uid;
    private String username;
    private String cardId;
    private String title;
    private String text;
    private boolean isPublic;
    private String weeklyText = "";


    public Card(CardType cardType, String uid, String username, String cardId, String title, String text, boolean isPublic) {
        this.cardType = cardType;
        this.uid = uid;
        this.username = username;
        this.cardId = cardId;
        this.title = title;
        this.text = text;
        this.isPublic = isPublic;
    }

    // Overloaded Constructor for Weekly cards only!

    public Card(CardType cardType, String uid, String username, String cardId, String title, String text, boolean isPublic, String weeklyText) {
        this.cardType = cardType;
        this.uid = uid;
        this.username = username;
        this.cardId = cardId;
        this.title = title;
        this.text = text;
        this.isPublic = isPublic;
        this.weeklyText = weeklyText;
    }

    public String getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getWeeklyText() {
        return weeklyText;
    }

    public String getCardId() {
        return cardId;
    }

    public String getUsername() {
        return username;
    }
}
