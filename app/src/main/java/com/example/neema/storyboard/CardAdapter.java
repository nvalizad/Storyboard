package com.example.neema.storyboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    public List<Card> cards;
    public class CardViewHolder extends RecyclerView.ViewHolder {
        private TextView title, text, type;
        private ImageView privacy;

        public CardViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            text = (TextView) view.findViewById(R.id.description);
            privacy = (ImageView) view.findViewById(R.id.privacy);
            type = (TextView) view.findViewById(R.id.type);

        }
    }
    public CardAdapter(List<Card> cards) {
        this.cards = cards;
    }
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.freewrite_card,parent,false);
        return new CardViewHolder(itemView);
    }
    @Override
    public int getItemCount() {
        return cards.size();
    }
    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Card card = cards.get(position);
        if (card.cardType == CardType.FREEWRITE) {
            holder.title.setText(card.getTitle());
            holder.text.setText(card.getText());
        }
        else if (card.cardType == CardType.PROMPT) {
            holder.title.setText(card.getText());
            holder.text.setText("");
        }
        else if (card.cardType == CardType.WEEKLY) {
            holder.title.setText(R.string.challengeOne_string);
            holder.text.setText(card.getWeeklyText());
        }
//        holder.privacy.setText(boolToString(card.isPublic()));
        holder.privacy.setImageResource(boolToImage(holder, card.isPublic()));
        holder.type.setText(typeToString(card.getCardType()));
    }

    public  int boolToImage(CardViewHolder holder, boolean isPublic) {
        if(isPublic)
            return R.drawable.outline_lock_open_black_18dp;
        return R.drawable.twotone_lock_black_18dp;
    }
    public String boolToString(boolean isPublic) {
        if (isPublic)
            return "Public";
        return "Private";
    }

    public String typeToString (CardType cardType) {
        switch (cardType) {
            case FREEWRITE:
                return "Freewrite";
            case PROMPT:
                return "Prompt";
            case WEEKLY:
                return "Weekly";
        }
        return "Error";
    }
}
